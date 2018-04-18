package com.infore.common.redisclient;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by xuyao on 2017/11/16.
 */
public interface RedisClient {

    //------string opt-------
    String set(String key, String value);

    Long setnx(String key, String value);

    String setex(String key, int seconds, String value);

    String get(String key);

    Long incr(String key);

    Long decr(String key);

    Long expire(String key, int second);

    Long ttl(String key);


    //------map opt--------
    Long hset(String key, String item, String value);

    String hget(String key, String item);

    Map<String, String> hgetAll(String key);

    Long hdel(String key, String item);

    String hmset(String key, Map<String, String> map);

    List<String> hmget(String key, String... fileds);


    //------list opt--------
    Long rpush(final String key, final String... strings);

    List<String> lrange(final String key, final long start, final long end);


    //------set opt---------
    Long sadd(final String key, final String... members);

    Set<String> smembers(final String key);

    Long srem(final String key, final String... members);
}
