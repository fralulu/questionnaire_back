package com.infore.common.redis;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import redis.clients.jedis.BinaryClient.LIST_POSITION;

public enum PipelineMethod {

    /* ******************************************************** */
    /* ---------- ---------- Geo Commands ---------- ---------- */
    /* ******************************************************** */
    /**
     * GEOADD key longitude latitude member [longitude latitude member ...].
     */
    GEOADD(String.class),

    /**
     * GEODIST key member1 member2 [unit].
     */
    GEODIST(String.class, String.class, String.class),

    /**
     * GEOPOS key member [member ...].
     */
    GEOPOS(String.class, String.class),

    /**
     * GEORADIUS key longitude latitude radius m|km|ft|mi [WITHCOORD] [WITHDIST] [WITHHASH] [COUNT count] [ASC|DESC] [STORE key] [STOREDIST key].
     */
    GEORADIUS(String.class, Double.class, Double.class, Double.class),

    /**
     * GEORADIUSBYMEMBER key member radius m|km|ft|mi [WITHCOORD] [WITHDIST] [WITHHASH] [COUNT count] [ASC|DESC] [STORE key] [STOREDIST key].
     */
    GEORADIUSBYMEMBER(String.class, String.class, Double.class),

    /* *********************************************************** */
    /* ---------- ---------- Hashes Commands ---------- ---------- */
    /* *********************************************************** */
    /**
     * HDEL key field [field ...].
     */
    HDEL(String.class, String.class),

    /**
     * HEXISTS key field.
     */
    HEXISTS(String.class, String.class),

    /**
     * HGET key field.
     */
    HGET(String.class, String.class),

    /**
     * HGETALL key.
     */
    HGETALL(String.class),

    /**
     * HINCRBY key field increment.
     */
    HINCRBY(String.class, String.class, Long.class),

    /**
     * HKEYS key.
     */
    HKEYS(String.class),

    /**
     * HLEN key.
     */
    HLEN(String.class),

    /**
     * HMGET key field [field ...].
     */
    HMGET(String.class, String.class),

    /**
     * HMSET key field value [field value ...].
     * HMSET key Map.
     */
    HMSET(String.class),

    /**
     * HSET key field value.
     */
    HSET(String.class, String.class, String.class),

    /**
     * HSETNX key field value.
     */
    HSETNX(String.class, String.class, String.class),

    /**
     * HVALS key.
     */
    HVALS(String.class),

    /* ********************************************************* */
    /* ---------- ---------- Keys Commands ---------- ---------- */
    /* ********************************************************* */
    /**
     * DEL key.
     */
    DEL(String.class),

    /**
     * EXISTS key.
     */
    EXISTS(String.class),

    /**
     * EXPIRE key seconds.
     */
    EXPIRE(String.class, Integer.class),

    /**
     * EXPIREAT key timestamp.
     */
    EXPIREAT(String.class, Long.class),

    /**
     * TTL key.
     */
    TTL(String.class),

    /**
     * TYPE key.
     */
    TYPE(String.class),

    /* ********************************************************** */
    /* ---------- ---------- Lists commands ---------- ---------- */
    /* ********************************************************** */
    /**
     * LINDEX key index.
     */
    LINDEX(String.class, Long.class),

    /**
     * LINSERT key BEFORE|AFTER pivot value.
     */
    LINSERT(String.class, LIST_POSITION.class, String.class, String.class),

    /**
     * LLEN key.
     */
    LLEN(String.class),

    /**
     * LPOP key.
     */
    LPOP(String.class),

    /**
     * LPUSH key value [value ...].
     */
    LPUSH(String.class, String.class),

    /**
     * LPUSHX key value.
     */
    LPUSHX(String.class, String.class),

    /**
     * LRANGE key start stop.
     */
    LRANGE(String.class, Long.class, Long.class),

    /**
     * LREM key count value.
     */
    LREM(String.class, Long.class, String.class),

    /**
     * LSET key index value.
     */
    LSET(String.class, Long.class, String.class),

    /**
     * LTRIM key start stop.
     */
    LTRIM(String.class, Long.class, Long.class),

    /**
     * RPOP key.
     */
    RPOP(String.class),

    /**
     * RPUSH key value [value ...].
     */
    RPUSH(String.class, String.class),

    /**
     * RPUSHX key value.
     */
    RPUSHX(String.class, String.class),

    /* ********************************************************* */
    /* ---------- ---------- Sets commands ---------- ---------- */
    /* ********************************************************* */
    /**
     * SADD key member [member ...].
     */
    SADD(String.class, String.class),

    /**
     * SCARD key.
     */
    SCARD(String.class),

    /**
     * SISMEMBER key member.
     */
    SISMEMBER(String.class, String.class),

    /**
     * SMEMBERS key.
     */
    SMEMBERS(String.class),

    /**
     * SPOP key.
     */
    SPOP(String.class),

    /**
     * SRANDMEMBER key.
     */
    SRANDMEMBER(String.class),

    /**
     * SREM key member [member ...].
     */
    SREM(String.class, String.class),

    /* **************************************************************** */
    /* ---------- ---------- Sorted Sets commands ---------- ---------- */
    /* **************************************************************** */
    /**
     * ZADD key [NX|XX] [CH] [INCR] score member [score member ...].
     */
    ZADD(String.class),

    /**
     * ZADD key [NX|XX] [CH] [INCR] score member [score member ...].
     */
    ZADDNX(String.class),

    /**
     * ZADD key [NX|XX] [CH] [INCR] score member [score member ...].
     */
    ZADDXX(String.class),

    /**
     * ZADD key [NX|XX] [CH] [INCR] score member [score member ...].
     */
    ZADDCH(String.class),

    /**
     * ZADD key [NX|XX] [CH] [INCR] score member [score member ...].
     */
    ZADDNXCH(String.class),

    /**
     * ZADD key [NX|XX] [CH] [INCR] score member [score member ...].
     */
    ZADDXXCH(String.class),

    /**
     * ZCARD key.
     */
    ZCARD(String.class),

    /**
     * ZCOUNT key min max.
     */
    ZCOUNT(String.class),

    /**
     * ZINCRBY key increment member.
     */
    ZINCRBY(String.class),

    /**
     * ZLEXCOUNT key min max.
     */
    ZLEXCOUNT(String.class, String.class, String.class),

    /**
     * ZRANGE key start stop [WITHSCORES].
     */
    ZRANGE(String.class),

    /**
     * ZRANGE key start stop [WITHSCORES].
     */
    ZRANGEWITHSCORES(String.class),

    /**
     * ZRANGEBYLEX key min max [LIMIT offset count].
     */
    ZRANGEBYLEX(String.class, String.class, String.class),

    /**
     * ZRANGEBYSCORE key min max [WITHSCORES] [LIMIT offset count].
     */
    ZRANGEBYSCORE(String.class),

    /**
     * ZRANGEBYSCORE key min max [WITHSCORES] [LIMIT offset count].
     */
    ZRANGEBYSCOREWITHSCORES(String.class),

    /**
     * ZRANK key member.
     */
    ZRANK(String.class, String.class),

    /**
     * ZREM key member [member ...].
     */
    ZREM(String.class),

    /**
     * ZREMRANGEBYLEX key min max.
     */
    ZREMRANGEBYLEX(String.class),

    /**
     * ZREMRANGEBYRANK key start stop.
     */
    ZREMRANGEBYRANK(String.class),

    /**
     * ZREMRANGEBYSCORE key min max.
     */
    ZREMRANGEBYSCORE(String.class),

    /**
     * ZREVRANGE key start stop [WITHSCORES].
     */
    ZREVRANGE(String.class),

    /**
     * ZREVRANGE key start stop [WITHSCORES].
     */
    ZREVRANGEWITHSCORES(String.class),

    /**
     * ZREVRANGEBYLEX key max min [LIMIT offset count].
     */
    ZREVRANGEBYLEX(String.class),

    /**
     * ZREVRANGEBYSCORE key max min [WITHSCORES] [LIMIT offset count].
     */
    ZREVRANGEBYSCORE(String.class),

    /**
     * ZREVRANGEBYSCORE key max min [WITHSCORES] [LIMIT offset count].
     */
    ZREVRANGEBYSCOREWITHSCORES(String.class),

    /**
     * ZREVRANK key member.
     */
    ZREVRANK(String.class, String.class),

    /**
     * ZSCORE key member.
     */
    ZSCORE(String.class, String.class),

    /* ************************************************************ */
    /* ---------- ---------- Strings commands ---------- ---------- */
    /* ************************************************************ */
    /**
     * APPEND key value.
     */
    APPEND(String.class, String.class),

    /**
     * DECR key.
     */
    DECR(String.class),

    /**
     * DECRBY key decrement.
     */
    DECRBY(String.class, Long.class),

    /**
     * GET key.
     */
    GET(String.class),

    /**
     * GETSET key value.
     */
    GETSET(String.class, String.class),

    /**
     * INCR key.
     */
    INCR(String.class),

    /**
     * INCRBY key increment.
     */
    INCRBY(String.class, Long.class),

    /**
     * SET key value.
     */
    SET(String.class, String.class),

    /**
     * SETEX key seconds value.
     */
    SETEX(String.class, Integer.class, String.class),

    /**
     * SETNX key value.
     */
    SETNX(String.class, String.class),

    /**
     * STRLEN key.
     */
    STRLEN(String.class);

    private final List<Class<?>> paramTypes;

    PipelineMethod(Class<?>... paramTypes) {
        this.paramTypes = Stream.of(paramTypes).collect(Collectors.toList());
    }

    List<Class<?>> getParamTypes() {
        return this.paramTypes;
    }

}
