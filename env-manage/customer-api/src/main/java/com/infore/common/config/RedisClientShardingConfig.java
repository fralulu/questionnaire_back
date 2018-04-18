package com.infore.common.config;

import com.infore.common.Properties;
import com.infore.common.redisclient.SentinelShardedJedisPool;
import java.util.HashSet;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.util.Hashing;

/**
 * Created by xuyao on 2017/11/14.
 */
//@Configuration
public class RedisClientShardingConfig {

    @Autowired
    private Properties properties;

    @Bean
    public SentinelShardedJedisPool sentinelShardedJedisPool(){
        JedisPoolConfig poolConfig = new JedisPoolConfig();
        poolConfig.setMinIdle(properties.REDIS_POOL_MINIDLE);
        poolConfig.setMaxIdle(properties.REDIS_POOL_MAXIDLE);
        poolConfig.setMaxTotal(properties.REDIS_POOL_MAXTOTAL);
        poolConfig.setMaxWaitMillis(properties.REDIS_POOL_MAXWAIT);
        poolConfig.setTestOnBorrow(properties.REDIS_POOL_TESTONBORROW);
        Set<String> sentinels = new HashSet<String>(){{
            add(properties.REDIS_SENTINEL_NODE1);
            add(properties.REDIS_SENTINEL_NODE2);
            add(properties.REDIS_SENTINEL_NODE3);
        }};
        Set<SentinelShardedJedisPool.SentinelInfo> sentinelInfos = new HashSet<SentinelShardedJedisPool.SentinelInfo>() {{
            add(new SentinelShardedJedisPool.SentinelInfo(properties.REDIS_SENTINEL_MASTERNAME1,
                properties.REDIS_SENTINEL_MASTERNAME1, sentinels));
            add(new SentinelShardedJedisPool.SentinelInfo(properties.REDIS_SENTINEL_MASTERNAME2,
                properties.REDIS_SENTINEL_MASTERNAME2, sentinels));
            add(new SentinelShardedJedisPool.SentinelInfo(properties.REDIS_SENTINEL_MASTERNAME3,
                properties.REDIS_SENTINEL_MASTERNAME3, sentinels));
        }};

        SentinelShardedJedisPool pool = new SentinelShardedJedisPool(sentinelInfos, poolConfig,
            Hashing.MURMUR_HASH, null);
        return pool;
    }

}
