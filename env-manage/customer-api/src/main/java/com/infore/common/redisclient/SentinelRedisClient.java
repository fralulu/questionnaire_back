package com.infore.common.redisclient;

import java.util.List;
import java.util.Map;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by xuyao on 2017/11/14.
 */
//@Component
public class SentinelRedisClient implements RedisClient {

    @Autowired
    private SentinelShardedJedisPool sentinelShardedJedisPool;


    @Override
    public String set(String key, String value) {
        SentinelShardedJedis jedis = getRedisClient();
        String result = jedis.set(key, value);
        releaseResource(jedis);
        return result;
    }

    @Override
    public Long setnx(String key, String value) {
        SentinelShardedJedis jedis = getRedisClient();
        Long result = jedis.setnx(key, value);
        releaseResource(jedis);
        return result;
    }

    @Override
    public String setex(String key, int seconds, String value) {
        SentinelShardedJedis jedis = getRedisClient();
        String result = jedis.setex(key, seconds, value);
        releaseResource(jedis);
        return result;
    }

    @Override
    public String get(String key) {
        SentinelShardedJedis jedis = getRedisClient();
        String result = jedis.get(key);
        releaseResource(jedis);
        return result;
    }

    @Override
    public Long incr(String key) {
        SentinelShardedJedis jedis = getRedisClient();
        Long result = jedis.incr(key);
        releaseResource(jedis);
        return result;
    }

    @Override
    public Long decr(String key) {
        SentinelShardedJedis jedis = getRedisClient();
        Long result = jedis.decr(key);
        releaseResource(jedis);
        return result;
    }

    @Override
    public Long expire(String key, int second) {
        SentinelShardedJedis jedis = getRedisClient();
        Long result = jedis.expire(key, second);
        releaseResource(jedis);
        return result;
    }

    @Override
    public Long ttl(String key) {
        SentinelShardedJedis jedis = getRedisClient();
        Long result = jedis.ttl(key);
        releaseResource(jedis);
        return result;
    }

    @Override
    public Long hdel(String key, String item) {
        SentinelShardedJedis jedis = getRedisClient();
        Long result = jedis.hdel(key, item);
        releaseResource(jedis);
        return result;
    }

    @Override
    public Long hset(String key, String item, String value) {
        SentinelShardedJedis jedis = getRedisClient();
        Long result = jedis.hset(key, item, value);
        releaseResource(jedis);
        return result;
    }

    @Override
    public String hget(String key, String item) {
        SentinelShardedJedis jedis = getRedisClient();
        String result = jedis.hget(key, item);
        releaseResource(jedis);
        return result;
    }

    @Override
    public Map<String, String> hgetAll(String key) {
        SentinelShardedJedis jedis = getRedisClient();
        Map<String, String> value = jedis.hgetAll(key);
        releaseResource(jedis);
        return value;
    }

    @Override
    public String hmset(String key, Map<String, String> map) {
        SentinelShardedJedis jedis = getRedisClient();
        String result=jedis.hmset(key,map);
        releaseResource(jedis);
        return result;
    }

    @Override
    public List<String> hmget(String key, String... fileds) {
        SentinelShardedJedis jedis = getRedisClient();
        List<String> result = jedis.hmget(key, fileds);
        releaseResource(jedis);
        return result;
    }

    @Override
    public Long rpush(String key, String... strings) {
        SentinelShardedJedis jedis = getRedisClient();
        Long result = jedis.rpush(key, strings);
        releaseResource(jedis);
        return result;
    }

    @Override
    public List<String> lrange(String key, long start, long end) {
        SentinelShardedJedis jedis = getRedisClient();
        List<String> result = jedis.lrange(key, start, end);
        releaseResource(jedis);
        return result;
    }

    @Override
    public Long sadd(String key, String... members) {
        SentinelShardedJedis jedis = getRedisClient();
        Long result = jedis.sadd(key, members);
        releaseResource(jedis);
        return result;
    }

    @Override
    public Set<String> smembers(String key) {
        SentinelShardedJedis jedis = getRedisClient();
        Set<String> result = jedis.smembers(key);
        releaseResource(jedis);
        return result;
    }

    @Override
    public Long srem(String key, String... members) {
        SentinelShardedJedis jedis = getRedisClient();
        Long result = jedis.srem(key, members);
        releaseResource(jedis);
        return result;
    }

    public SentinelShardedJedis getRedisClient() {
        return sentinelShardedJedisPool.getResource();
    }

    /**
     * 释放连接资源
     * @param jedis
     */
    public void releaseResource(final SentinelShardedJedis jedis) {
        if (jedis != null){
            jedis.close();
        }
    }
}
