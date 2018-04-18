package com.infore.common.redisclient;

import java.util.List;
import java.util.Map;
import java.util.Set;
import org.springframework.stereotype.Component;

/**
 * Created by xuyao on 2017/11/16.
 */
//@Component
public class ClusterRedisClient implements RedisClient {

    @Override
    public String set(String key, String value) {
        return null;
    }

    @Override
    public Long setnx(String key, String value) {
        return null;
    }

    @Override
    public String setex(String key, int seconds, String value) {
        return null;
    }

    @Override
    public String get(String key) {
        return null;
    }

    @Override
    public Long incr(String key) {
        return null;
    }

    @Override
    public Long decr(String key) {
        return null;
    }

    @Override
    public Long expire(String key, int second) {
        return null;
    }

    @Override
    public Long ttl(String key) {
        return null;
    }

    @Override
    public Long hset(String key, String item, String value) {
        return null;
    }

    @Override
    public String hget(String key, String item) {
        return null;
    }

    @Override
    public Map<String, String> hgetAll(String key) {
        return null;
    }

    @Override
    public Long hdel(String key, String item) {
        return null;
    }

    @Override
    public String hmset(String key, Map<String, String> map) {
        return null;
    }

    @Override
    public List<String> hmget(String key, String... fileds) {
        return null;
    }

    @Override
    public Long rpush(String key, String... strings) {
        return null;
    }

    @Override
    public List<String> lrange(String key, long start, long end) {
        return null;
    }

    @Override
    public Long sadd(String key, String... members) {
        return null;
    }

    @Override
    public Set<String> smembers(String key) {
        return null;
    }

    @Override
    public Long srem(String key, String... members) {
        return null;
    }

//    @Autowired
//    private JedisCluster jedisCluster;
}
