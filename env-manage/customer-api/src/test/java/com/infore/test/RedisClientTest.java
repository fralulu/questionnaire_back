package com.infore.test;

import com.infore.common.redisclient.SentinelShardedJedis;
import com.infore.common.redisclient.SentinelShardedJedisPool;
import java.util.HashSet;
import java.util.Set;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.util.Hashing;

/**
 * Created by xuyao on 2017/11/14.
 */
public class RedisClientTest {

    public static void main(String[] args) {
        JedisPoolConfig poolConfig = new JedisPoolConfig();
        poolConfig.setMinIdle(4);
        poolConfig.setMaxTotal(100);
        poolConfig.setMaxWaitMillis(30000);

        Set<String> sentinels = new HashSet<>();
        sentinels.add("192.168.37.16:26379");
        sentinels.add("192.168.37.17:26379");
        sentinels.add("192.168.37.18:26379");
        Set<SentinelShardedJedisPool.SentinelInfo> sentinelInfos = new HashSet<>();
        sentinelInfos.add(new SentinelShardedJedisPool.SentinelInfo("master001", "m1", sentinels));
        sentinelInfos.add(new SentinelShardedJedisPool.SentinelInfo("master002", "m2", sentinels));
        sentinelInfos.add(new SentinelShardedJedisPool.SentinelInfo("master003", "m3", sentinels));
        SentinelShardedJedisPool pool = new SentinelShardedJedisPool(sentinelInfos, poolConfig,
            Hashing.MURMUR_HASH, null);

        SentinelShardedJedis jedis = pool.getResource();
        for (int i=1;i<10;i++) {
            String key = "keys" + i;
            String value = "valueOK" + i;
//            jedis.set(key, value);
            System.out.println(jedis.get(key));
        }
        jedis.close();
        pool.close();
    }

}
