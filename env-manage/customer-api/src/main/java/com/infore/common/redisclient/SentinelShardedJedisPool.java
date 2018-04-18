package com.infore.common.redisclient;

import org.apache.commons.pool2.PooledObject;
import org.apache.commons.pool2.PooledObjectFactory;
import org.apache.commons.pool2.impl.DefaultPooledObject;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPubSub;
import redis.clients.jedis.JedisShardInfo;
import redis.clients.jedis.exceptions.JedisConnectionException;
import redis.clients.jedis.exceptions.JedisException;
import redis.clients.util.Hashing;
import redis.clients.util.Pool;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;

public class SentinelShardedJedisPool extends Pool<SentinelShardedJedis> {

    protected Logger log = Logger.getLogger(getClass().getName());

    private final Map<String, JedisShardInfo> shardInfoMap = new ConcurrentHashMap<>(); // key=masterName
    private final Map<String, String> sentinels = new ConcurrentHashMap<>(); // key=host+port value=masterName

    private volatile SentinelShardedJedisFactory factory;
    private Set<MasterListener> masterListeners = new HashSet<>();

    public SentinelShardedJedisPool(Set<SentinelInfo> sentinelInfos, final GenericObjectPoolConfig poolConfig, Hashing algo, Pattern keyTagPattern) {
        initShards(sentinelInfos);
        factory = new SentinelShardedJedisFactory(shardInfoMap.values(), algo, keyTagPattern);
        super.initPool(poolConfig, factory);

    }

    private void initShards(Set<SentinelInfo> sentinelInfos) {
        for (SentinelInfo info : sentinelInfos) {
            JedisShardInfo shardInfo = getJedisSharedInfoFromSentinelInfo(info);
            sentinels.put(shardInfo.getHost() + ":" + shardInfo.getPort(), info.getMasterName());
            shardInfoMap.put(info.getMasterName(), shardInfo);

            for (String sentinel : info.getSentinels()) {
                HostAndPort hap = HostAndPort.parseString(sentinel);
                MasterListener masterListener = new MasterListener(info.getMasterName(), hap.getHost(), hap.getPort());
                masterListeners.add(masterListener);
                masterListener.setDaemon(true);
                masterListener.start();
            }
        }
    }

    private JedisShardInfo getJedisSharedInfoFromSentinelInfo(SentinelInfo info) {
        log.info("Trying to find master from available Sentinels...");
        for (String sentinel : info.getSentinels()) {
            final HostAndPort hap = HostAndPort.parseString(sentinel);
            log.fine("Connecting to Sentinel " + hap);
            Jedis jedis = null;
            try {
                jedis = new Jedis(hap.getHost(), hap.getPort());
                List<String> masterAddr = jedis.sentinelGetMasterAddrByName(info.getMasterName());
                if (masterAddr == null || masterAddr.size() != 2) {
                    log.warning("Can not get master addr, master name: " + info.getMasterName() + ". Sentinel: " + hap + ".");
                    continue;
                }

                String host = masterAddr.get(0);
                int port = Integer.parseInt(masterAddr.get(1));
                JedisShardInfo jedisShardInfo = new JedisShardInfo(host, port);
                log.fine("Found Redis master at " + masterAddr);
                return jedisShardInfo;
            } catch (JedisException e) {
                log.warning("Cannot get master address from sentinel running @ " + hap + ". Reason: " + e + ". Trying next one.");
            } finally {
                if (jedis != null) {
                    jedis.close();
                }
            }
        }
        throw new JedisConnectionException("All sentinels down, cannot determine where is " + info.getMasterName() + " master is running...");
    }

    private void reInitPool() {
        internalPool.clear();
    }

    @Override
    public void destroy() {
        for (MasterListener m : masterListeners) {
            m.shutdown();
        }
        super.destroy();
    }

    @Override
    public SentinelShardedJedis getResource() {
        while (true) {
            SentinelShardedJedis jedis = super.getResource();
            for (Jedis shard : jedis.getAllShards()) {
                String host = shard.getClient().getHost();
                int port = shard.getClient().getPort();
                if (sentinels.containsKey(host + ":" + port)) {
                    jedis.setDataSource(this);
                    return jedis;
                }
            }
            returnBrokenResource(jedis);
        }
    }

    private class MasterListener extends Thread {
        private String masterName;
        private String sentinelHost;
        private int sentinelPort;
        private volatile Jedis j;
        private AtomicBoolean running = new AtomicBoolean(false);

        public MasterListener(String masterName, String host, int port) {
            this.masterName = masterName;
            this.sentinelHost = host;
            this.sentinelPort = port;
            this.setName(String.format("MasterListener-%s-[%s:%d]", masterName, host, port));
        }

        @Override
        public void run() {
            running.set(true);
            while (running.get()) {
                j = new Jedis(sentinelHost, sentinelPort);
                try {
                    if (!running.get()) {
                        break;
                    }
                    j.subscribe(new JedisPubSub() {
                        @Override
                        public void onMessage(String channel, String message) {
                            log.fine("Sentinel " + sentinelHost + ":" + sentinelPort + " published: " + message + ".");
                            String[] switchMasterMsg = message.split(" ");
                            if (switchMasterMsg.length > 3) {
                                if (masterName.equals(switchMasterMsg[0])) {
                                    JedisShardInfo oldInfo = shardInfoMap.get(masterName);
                                    sentinels.remove(oldInfo.getHost() + ":" + oldInfo.getPort());

                                    JedisShardInfo newInfo = new JedisShardInfo(switchMasterMsg[3], switchMasterMsg[4]);
                                    newInfo.setConnectionTimeout(oldInfo.getConnectionTimeout());
                                    newInfo.setPassword(oldInfo.getPassword());
                                    newInfo.setSoTimeout(oldInfo.getSoTimeout());
                                    shardInfoMap.put(masterName, newInfo);
                                    sentinels.put(newInfo.getHost() + ":" + newInfo.getPort(), masterName);
                                    reInitPool();
                                } else {
                                    log.fine("Ignoring message on +switch-master for master name " + switchMasterMsg[0] + ", our master name is " + masterName);
                                }

                            } else {
                                log.severe("Invalid message received on Sentinel " + sentinelHost + ":" + sentinelPort + " on channel +switch-master: " + message);
                            }
                        }
                    }, "+switch-master");
                } catch (JedisConnectionException e) {
                    if (running.get()) {
                        log.log(Level.SEVERE, "Lost connection to Sentinel at " + sentinelHost + ":" + sentinelPort + ". Sleeping 5000ms and retrying.", e);
                        try {
                            Thread.sleep(5000);
                        } catch (InterruptedException e1) {
                            log.log(Level.SEVERE, "Sleep interrupted: ", e1);
                        }
                    } else {
                        log.fine("Unsubscribing from Sentinel at " + sentinelHost + ":" + sentinelPort);
                    }
                } finally {
                    j.close();
                }
            }
        }

        public void shutdown() {
            try {
                log.fine("Shutting down listener on " + sentinelHost + ":" + sentinelPort);
                running.set(false);
                if (j != null) {
                    j.disconnect();
                }
            } catch (Exception e) {
                log.log(Level.SEVERE, "Caught exception while shutting down: ", e);
            }
        }
    }

    /**
     * PoolableObjectFactory custom impl.
     */
    private class SentinelShardedJedisFactory implements PooledObjectFactory<SentinelShardedJedis> {

        private Collection<JedisShardInfo> shards;
        private Hashing algo;
        private Pattern keyTagPattern;

        public SentinelShardedJedisFactory(Collection<JedisShardInfo> shards, Hashing algo, Pattern keyTagPattern) {
            this.shards = shards;
            this.algo = algo;
            this.keyTagPattern = keyTagPattern;
        }

        @Override
        public PooledObject<SentinelShardedJedis> makeObject() throws Exception {
            SentinelShardedJedis jedis = new SentinelShardedJedis(new ArrayList<>(shards), algo, keyTagPattern);
            return new DefaultPooledObject<SentinelShardedJedis>(jedis);
        }

        @Override
        public void destroyObject(PooledObject<SentinelShardedJedis> pooledShardedJedis) throws Exception {
            final SentinelShardedJedis shardedJedis = pooledShardedJedis.getObject();
            for (Jedis jedis : shardedJedis.getAllShards()) {
                try {
                    try {
                        jedis.quit();
                    } catch (Exception e) {

                    }
                    jedis.disconnect();
                } catch (Exception e) {

                }
            }
        }

        @Override
        public boolean validateObject(PooledObject<SentinelShardedJedis> pooledShardedJedis) {
            try {
                SentinelShardedJedis jedis = pooledShardedJedis.getObject();
                for (Jedis shard : jedis.getAllShards()) {
                    String host = shard.getClient().getHost();
                    int port = shard.getClient().getPort();
                    if (!shardInfoMap.containsKey(host + port)) {
                        return false;
                    }

                    if (!shard.ping().equals("PONG")) {
                        return false;
                    }
                }
                return true;
            } catch (Exception ex) {
                return false;
            }
        }

        @Override
        public void activateObject(PooledObject<SentinelShardedJedis> p) throws Exception {

        }

        @Override
        public void passivateObject(PooledObject<SentinelShardedJedis> p) throws Exception {

        }
    }

    public static class SentinelInfo {
        private String masterName;
        private String clientName;
        private Set<String> sentinels;

        public SentinelInfo(String masterName, String clientName, Set<String> sentinels) {
            this.masterName = masterName;
            this.clientName = clientName;
            this.sentinels = sentinels;
        }

        public String getMasterName() {
            return masterName;
        }

        public String getClientName() {
            return clientName;
        }

        public Set<String> getSentinels() {
            return sentinels;
        }
    }
}
