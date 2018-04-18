package com.infore.common.redis;

import redis.clients.jedis.BinaryClient.LIST_POSITION;
import redis.clients.jedis.*;
import redis.clients.jedis.params.geo.GeoRadiusParam;
import redis.clients.jedis.params.sortedset.ZAddParams;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Redis client.
 */
public class Redis {

    /**
     * Pooled redis cluster.
     */
    private JedisPool jedisPool;

    /**
     * Construct a redis cluster client.
     *
     * @param host redis server host.
     */
    public Redis(String host) {
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxIdle(20);
        config.setMaxTotal(50);
        config.setTestOnBorrow(true);
        this.jedisPool = new JedisPool(config, host);
    }


    /**
     * Construct a redis cluster client.
     *
     * @param host redis server host.
     * @param port redis server port
     */
    public Redis(String host, int port) {
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxIdle(20);
        config.setMaxTotal(50);
        config.setTestOnBorrow(true);
        this.jedisPool = new JedisPool(config, host, port);
    }

    /**
     * Construct a redis cluster client.
     *
     * @param host     redis server host.
     * @param password redis server connection password.
     */
    public Redis(String host, String password) {
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxIdle(20);
        config.setMaxTotal(50);
        config.setTestOnBorrow(true);
        this.jedisPool = new JedisPool(config, host, 6379, 3000, password);
    }

    /* ********************************************************* */
    /* ---------- ---------- Commands: Geo ---------- ---------- */
    /* ********************************************************* */
    public Long geoadd(String key, Map<String, GeoCoordinate> memberCoordinateMap) {
        try (Jedis jedis = this.jedisPool.getResource()) {
            return jedis.geoadd(key, memberCoordinateMap);
        }
    }

    public Double geodist(String key, String member1, String member2, GeoUnit unit) {
        try (Jedis jedis = this.jedisPool.getResource()) {
            return jedis.geodist(key, member1, member2, unit);
        }
    }

    public List<GeoCoordinate> geopos(String key, String... members) {
        try (Jedis jedis = this.jedisPool.getResource()) {
            return jedis.geopos(key, members);
        }
    }

    public List<GeoRadiusResponse> georadius(String key, double longitude, double latitude, double radius, GeoUnit unit) {
        try (Jedis jedis = this.jedisPool.getResource()) {
            return jedis.georadius(key, longitude, latitude, radius, unit);
        }
    }

    public List<GeoRadiusResponse> georadius(String key, double longitude, double latitude, double radius, GeoUnit unit, GeoRadiusParam param) {
        try (Jedis jedis = this.jedisPool.getResource()) {
            return jedis.georadius(key, longitude, latitude, radius, unit, param);
        }
    }

    public List<GeoRadiusResponse> georadiusByMember(String key, String member, double radius, GeoUnit unit) {
        try (Jedis jedis = this.jedisPool.getResource()) {
            return jedis.georadiusByMember(key, member, radius, unit);
        }
    }

    public List<GeoRadiusResponse> georadiusByMember(String key, String member, double radius, GeoUnit unit, GeoRadiusParam param) {
        try (Jedis jedis = this.jedisPool.getResource()) {
            return jedis.georadiusByMember(key, member, radius, unit, param);
        }
    }

    /* ************************************************************ */
    /* ---------- ---------- Commands: Hashes ---------- ---------- */
    /* ************************************************************ */
    public Long hdel(String key, String... fields) {
        try (Jedis jedis = this.jedisPool.getResource()) {
            return jedis.hdel(key, fields);
        }
    }

    public Boolean hexists(String key, String field) {
        try (Jedis jedis = this.jedisPool.getResource()) {
            return jedis.hexists(key, field);
        }
    }

    public String hget(String key, String field) {
        try (Jedis jedis = this.jedisPool.getResource()) {
            return jedis.hget(key, field);
        }
    }

    public Map<String, String> hgetAll(String key) {
        try (Jedis jedis = this.jedisPool.getResource()) {
            return jedis.hgetAll(key);
        }
    }

    public Long hincrBy(String key, String field, long value) {
        try (Jedis jedis = this.jedisPool.getResource()) {
            return jedis.hincrBy(key, field, value);
        }
    }

    public Set<String> hkeys(String key) {
        try (Jedis jedis = this.jedisPool.getResource()) {
            return jedis.hkeys(key);
        }
    }

    public Long hlen(String key) {
        try (Jedis jedis = this.jedisPool.getResource()) {
            return jedis.hlen(key);
        }
    }

    public List<String> hmget(String key, String... fields) {
        try (Jedis jedis = this.jedisPool.getResource()) {
            return jedis.hmget(key, fields);
        }
    }

    public List<String> hmget(String key, List<String> fields) {
        int len = fields.size();
        String[] arr = new String[len];
        arr = fields.toArray(arr);
        try (Jedis jedis = this.jedisPool.getResource()) {
            return jedis.hmget(key, arr);
        }
    }

    public String hmset(String key, Map<String, String> hash) {
        try (Jedis jedis = this.jedisPool.getResource()) {
            return jedis.hmset(key, hash);
        }
    }

    public String hmset(String key, String... fieldsAndValues) {
        int len = fieldsAndValues.length;
        Map<String, String> hash = new HashMap<>();
        for (int i = 0; i < len; i += 2) {
            hash.put(fieldsAndValues[i], fieldsAndValues[i + 1]);
        }

        try (Jedis jedis = this.jedisPool.getResource()) {
            return jedis.hmset(key, hash);
        }
    }

    public Long hset(String key, String field, String value) {
        try (Jedis jedis = this.jedisPool.getResource()) {
            return jedis.hset(key, field, value);
        }
    }

    public Long hsetnx(String key, String field, String value) {
        try (Jedis jedis = this.jedisPool.getResource()) {
            return jedis.hsetnx(key, field, value);
        }
    }

    public List<String> hvals(String key) {
        try (Jedis jedis = this.jedisPool.getResource()) {
            return jedis.hvals(key);
        }
    }

    /* ********************************************************** */
    /* ---------- ---------- Commands: Keys ---------- ---------- */
    /* ********************************************************** */

    public Long del(String key) {
        try (Jedis jedis = this.jedisPool.getResource()) {
            return jedis.del(key);
        }
    }

    public Boolean exists(String key) {
        try (Jedis jedis = this.jedisPool.getResource()) {
            return jedis.exists(key);
        }
    }

    public Long expire(String key, int seconds) {
        try (Jedis jedis = this.jedisPool.getResource()) {
            return jedis.expire(key, seconds);
        }
    }

    public Long expireAt(String key, long unixTime) {
        try (Jedis jedis = this.jedisPool.getResource()) {
            return jedis.expireAt(key, unixTime);
        }
    }

    public Long ttl(String key) {
        try (Jedis jedis = this.jedisPool.getResource()) {
            return jedis.ttl(key);
        }
    }

    public String type(String key) {
        try (Jedis jedis = this.jedisPool.getResource()) {
            return jedis.type(key);
        }
    }

    /* *********************************************************** */
    /* ---------- ---------- Commands: Lists ---------- ---------- */
    /* *********************************************************** */
    public String lindex(String key, long index) {
        try (Jedis jedis = this.jedisPool.getResource()) {
            return jedis.lindex(key, index);
        }
    }

    public Long linsert(String key, LIST_POSITION where, String pivot, String value) {
        try (Jedis jedis = this.jedisPool.getResource()) {
            return jedis.linsert(key, where, pivot, value);
        }
    }

    public Long llen(String key) {
        try (Jedis jedis = this.jedisPool.getResource()) {
            return jedis.llen(key);
        }
    }

    public String lpop(String key) {
        try (Jedis jedis = this.jedisPool.getResource()) {
            return jedis.lpop(key);
        }
    }

    public Long lpush(String key, String... values) {
        try (Jedis jedis = this.jedisPool.getResource()) {
            return jedis.lpush(key, values);
        }
    }

    public Long lpushx(String key, String... values) {
        try (Jedis jedis = this.jedisPool.getResource()) {
            return jedis.lpushx(key, values);
        }
    }

    public List<String> lrange(String key, long start, long stop) {
        try (Jedis jedis = this.jedisPool.getResource()) {
            return jedis.lrange(key, start, stop);
        }
    }

    public Long lrem(String key, long count, String value) {
        try (Jedis jedis = this.jedisPool.getResource()) {
            return jedis.lrem(key, count, value);
        }
    }

    public String lset(String key, long index, String value) {
        try (Jedis jedis = this.jedisPool.getResource()) {
            return jedis.lset(key, index, value);
        }
    }

    public String ltrim(String key, long start, long end) {
        try (Jedis jedis = this.jedisPool.getResource()) {
            return jedis.ltrim(key, start, end);
        }
    }

    public String rpop(String key) {
        try (Jedis jedis = this.jedisPool.getResource()) {
            return jedis.rpop(key);
        }
    }

    public Long rpush(String key, String... values) {
        try (Jedis jedis = this.jedisPool.getResource()) {
            return jedis.rpush(key, values);
        }
    }

    public Long rpushx(String key, String... values) {
        try (Jedis jedis = this.jedisPool.getResource()) {
            return jedis.rpushx(key, values);
        }
    }

    /* ********************************************************* */
    /* ---------- ---------- Commands: Sets ---------- ---------- */
    /* ********************************************************* */
    public Long sadd(String key, String... members) {
        try (Jedis jedis = this.jedisPool.getResource()) {
            return jedis.sadd(key, members);
        }
    }

    public Long sadd(String key, List<String> members) {
        int len = members.size();
        String[] arr = new String[len];
        arr = members.toArray(arr);
        try (Jedis jedis = this.jedisPool.getResource()) {
            return jedis.sadd(key, arr);
        }
    }

    public Long scard(String key) {
        try (Jedis jedis = this.jedisPool.getResource()) {
            return jedis.scard(key);
        }
    }

    public Boolean sismember(String key, String member) {
        try (Jedis jedis = this.jedisPool.getResource()) {
            return jedis.sismember(key, member);
        }
    }

    public Set<String> smembers(String key) {
        try (Jedis jedis = this.jedisPool.getResource()) {
            return jedis.smembers(key);
        }
    }

    public String spop(String key) {
        try (Jedis jedis = this.jedisPool.getResource()) {
            return jedis.spop(key);
        }
    }

    public String srandmember(String key) {
        try (Jedis jedis = this.jedisPool.getResource()) {
            return jedis.srandmember(key);
        }
    }

    public List<String> srandmember(String key, int count) {
        try (Jedis jedis = this.jedisPool.getResource()) {
            return jedis.srandmember(key, count);
        }
    }

    public Long srem(String key, String... members) {
        try (Jedis jedis = this.jedisPool.getResource()) {
            return jedis.srem(key, members);
        }
    }

    /* ***************************************************************** */
    /* ---------- ---------- Commands: Sorted Sets ---------- ---------- */
    /* ***************************************************************** */
    public Long zadd(String key, double score, String member) {
        try (Jedis jedis = this.jedisPool.getResource()) {
            return jedis.zadd(key, score, member);
        }
    }

    public Long zadd(String key, Map<String, Double> scoreMembers) {
        try (Jedis jedis = this.jedisPool.getResource()) {
            return jedis.zadd(key, scoreMembers);
        }
    }

    public Long zaddnx(String key, double score, String member) {
        try (Jedis jedis = this.jedisPool.getResource()) {
            return jedis.zadd(key, score, member, ZAddParams.zAddParams().nx());
        }
    }

    public Long zaddnx(String key, Map<String, Double> scoreMembers) {
        try (Jedis jedis = this.jedisPool.getResource()) {
            return jedis.zadd(key, scoreMembers, ZAddParams.zAddParams().nx());
        }
    }

    public Long zaddxx(String key, double score, String member) {
        try (Jedis jedis = this.jedisPool.getResource()) {
            return jedis.zadd(key, score, member, ZAddParams.zAddParams().xx());
        }
    }

    public Long zaddxx(String key, Map<String, Double> scoreMembers) {
        try (Jedis jedis = this.jedisPool.getResource()) {
            return jedis.zadd(key, scoreMembers, ZAddParams.zAddParams().xx());
        }
    }

    public Long zaddch(String key, double score, String member) {
        try (Jedis jedis = this.jedisPool.getResource()) {
            return jedis.zadd(key, score, member, ZAddParams.zAddParams().ch());
        }
    }

    public Long zaddch(String key, Map<String, Double> scoreMembers) {
        try (Jedis jedis = this.jedisPool.getResource()) {
            return jedis.zadd(key, scoreMembers, ZAddParams.zAddParams().ch());
        }
    }

    public Long zaddnxch(String key, double score, String member) {
        try (Jedis jedis = this.jedisPool.getResource()) {
            return jedis.zadd(key, score, member, ZAddParams.zAddParams().nx().ch());
        }
    }

    public Long zaddnxch(String key, Map<String, Double> scoreMembers) {
        try (Jedis jedis = this.jedisPool.getResource()) {
            return jedis.zadd(key, scoreMembers, ZAddParams.zAddParams().nx().ch());
        }
    }

    public Long zaddxxch(String key, double score, String member) {
        try (Jedis jedis = this.jedisPool.getResource()) {
            return jedis.zadd(key, score, member, ZAddParams.zAddParams().xx().ch());
        }
    }

    public Long zaddxxch(String key, Map<String, Double> scoreMembers) {
        try (Jedis jedis = this.jedisPool.getResource()) {
            return jedis.zadd(key, scoreMembers, ZAddParams.zAddParams().xx().ch());
        }
    }

    public Long zcard(String key) {
        try (Jedis jedis = this.jedisPool.getResource()) {
            return jedis.zcard(key);
        }
    }

    public Long zcount(String key, double min, double max) {
        try (Jedis jedis = this.jedisPool.getResource()) {
            return jedis.zcount(key, min, max);
        }
    }

    public Long zcount(String key, String min, String max) {
        try (Jedis jedis = this.jedisPool.getResource()) {
            return jedis.zcount(key, min, max);
        }
    }

    public Double zincrby(String key, double score, String member) {
        try (Jedis jedis = this.jedisPool.getResource()) {
            return jedis.zincrby(key, score, member);
        }
    }

    public Long zlexcount(String key, String min, String max) {
        try (Jedis jedis = this.jedisPool.getResource()) {
            return jedis.zlexcount(key, min, max);
        }
    }

    public Set<String> zrange(String key, long start, long end) {
        try (Jedis jedis = this.jedisPool.getResource()) {
            return jedis.zrange(key, start, end);
        }
    }

    public Set<Tuple> zrangeWithScores(String key, long start, long end) {
        try (Jedis jedis = this.jedisPool.getResource()) {
            return jedis.zrangeWithScores(key, start, end);
        }
    }

    public Set<String> zrangeByLex(String key, String min, String max) {
        try (Jedis jedis = this.jedisPool.getResource()) {
            return jedis.zrangeByLex(key, min, max);
        }
    }

    public Set<String> zrangeByLex(String key, String min, String max, int offset, int count) {
        try (Jedis jedis = this.jedisPool.getResource()) {
            return jedis.zrangeByLex(key, min, max, offset, count);
        }
    }

    public Set<String> zrangeByScore(String key, double min, double max) {
        try (Jedis jedis = this.jedisPool.getResource()) {
            return jedis.zrangeByScore(key, min, max);
        }
    }

    public Set<String> zrangeByScore(String key, String min, String max) {
        try (Jedis jedis = this.jedisPool.getResource()) {
            return jedis.zrangeByScore(key, min, max);
        }
    }

    public Set<String> zrangeByScore(String key, double min, double max, int offset, int count) {
        try (Jedis jedis = this.jedisPool.getResource()) {
            return jedis.zrangeByScore(key, min, max, offset, count);
        }
    }

    public Set<String> zrangeByScore(String key, String min, String max, int offset, int count) {
        try (Jedis jedis = this.jedisPool.getResource()) {
            return jedis.zrangeByScore(key, min, max, offset, count);
        }
    }

    public Set<Tuple> zrangeByScoreWithScores(String key, double min, double max) {
        try (Jedis jedis = this.jedisPool.getResource()) {
            return jedis.zrangeByScoreWithScores(key, min, max);
        }
    }

    public Set<Tuple> zrangeByScoreWithScores(String key, String min, String max) {
        try (Jedis jedis = this.jedisPool.getResource()) {
            return jedis.zrangeByScoreWithScores(key, min, max);
        }
    }

    public Set<Tuple> zrangeByScoreWithScores(String key, double min, double max, int offset, int count) {
        try (Jedis jedis = this.jedisPool.getResource()) {
            return jedis.zrangeByScoreWithScores(key, min, max, offset, count);
        }
    }

    public Set<Tuple> zrangeByScoreWithScores(String key, String min, String max, int offset, int count) {
        try (Jedis jedis = this.jedisPool.getResource()) {
            return jedis.zrangeByScoreWithScores(key, min, max, offset, count);
        }
    }

    public Long zrank(String key, String member) {
        try (Jedis jedis = this.jedisPool.getResource()) {
            return jedis.zrank(key, member);
        }
    }

    public Long zrem(String key, String... members) {
        try (Jedis jedis = this.jedisPool.getResource()) {
            return jedis.zrem(key, members);
        }
    }

    public Long zrem(String key, List<String> members) {
        int len = members.size();
        String[] arr = new String[len];
        arr = members.toArray(arr);
        try (Jedis jedis = this.jedisPool.getResource()) {
            return jedis.zrem(key, arr);
        }
    }

    public Long zremrangeByLex(String key, String min, String max) {
        try (Jedis jedis = this.jedisPool.getResource()) {
            return jedis.zremrangeByLex(key, min, max);
        }
    }

    public Long zremrangeByRank(String key, long start, long end) {
        try (Jedis jedis = this.jedisPool.getResource()) {
            return jedis.zremrangeByRank(key, start, end);
        }
    }

    public Long zremrangeByScore(String key, double start, double end) {
        try (Jedis jedis = this.jedisPool.getResource()) {
            return jedis.zremrangeByScore(key, start, end);
        }
    }

    public Long zremrangeByScore(String key, String start, String end) {
        try (Jedis jedis = this.jedisPool.getResource()) {
            return jedis.zremrangeByScore(key, start, end);
        }
    }

    public Set<String> zrevrange(String key, long start, long end) {
        try (Jedis jedis = this.jedisPool.getResource()) {
            return jedis.zrevrange(key, start, end);
        }
    }

    public Set<Tuple> zrevrangeWithScores(String key, long start, long end) {
        try (Jedis jedis = this.jedisPool.getResource()) {
            return jedis.zrevrangeWithScores(key, start, end);
        }
    }

    public Set<String> zrevrangeByLex(String key, String max, String min) {
        try (Jedis jedis = this.jedisPool.getResource()) {
            return jedis.zrevrangeByLex(key, max, min);
        }
    }

    public Set<String> zrevrangeByLex(String key, String max, String min, int offset, int count) {
        try (Jedis jedis = this.jedisPool.getResource()) {
            return jedis.zrevrangeByLex(key, max, min, offset, count);
        }
    }

    public Set<String> zrevrangeByScore(String key, double max, double min) {
        try (Jedis jedis = this.jedisPool.getResource()) {
            return jedis.zrevrangeByScore(key, max, min);
        }
    }

    public Set<String> zrevrangeByScore(String key, String max, String min) {
        try (Jedis jedis = this.jedisPool.getResource()) {
            return jedis.zrevrangeByScore(key, max, min);
        }
    }

    public Set<String> zrevrangeByScore(String key, double max, double min, int offset, int count) {
        try (Jedis jedis = this.jedisPool.getResource()) {
            return jedis.zrevrangeByScore(key, max, min, offset, count);
        }
    }

    public Set<String> zrevrangeByScore(String key, String max, String min, int offset, int count) {
        try (Jedis jedis = this.jedisPool.getResource()) {
            return jedis.zrevrangeByScore(key, max, min, offset, count);
        }
    }

    public Set<Tuple> zrevrangeByScoreWithScores(String key, double max, double min) {
        try (Jedis jedis = this.jedisPool.getResource()) {
            return jedis.zrevrangeByScoreWithScores(key, max, min);
        }
    }

    public Set<Tuple> zrevrangeByScoreWithScores(String key, String max, String min) {
        try (Jedis jedis = this.jedisPool.getResource()) {
            return jedis.zrevrangeByScoreWithScores(key, max, min);
        }
    }

    public Set<Tuple> zrevrangeByScoreWithScores(String key, double max, double min, int offset, int count) {
        try (Jedis jedis = this.jedisPool.getResource()) {
            return jedis.zrevrangeByScoreWithScores(key, max, min, offset, count);
        }
    }

    public Set<Tuple> zrevrangeByScoreWithScores(String key, String max, String min, int offset, int count) {
        try (Jedis jedis = this.jedisPool.getResource()) {
            return jedis.zrevrangeByScoreWithScores(key, max, min, offset, count);
        }
    }

    public Long zrevrank(String key, String member) {
        try (Jedis jedis = this.jedisPool.getResource()) {
            return jedis.zrevrank(key, member);
        }
    }

    public Double zscore(String key, String member) {
        try (Jedis jedis = this.jedisPool.getResource()) {
            return jedis.zscore(key, member);
        }
    }

    /* ************************************************************* */
    /* ---------- ---------- Commands: Strings ---------- ---------- */
    /* ************************************************************* */
    public Long append(String key, String value) {
        try (Jedis jedis = this.jedisPool.getResource()) {
            return jedis.append(key, value);
        }
    }

    public Long decr(String key) {
        try (Jedis jedis = this.jedisPool.getResource()) {
            return jedis.decr(key);
        }
    }

    public Long decrBy(String key, long decrement) {
        try (Jedis jedis = this.jedisPool.getResource()) {
            return jedis.decrBy(key, decrement);
        }
    }

    public String get(String key) {
        try (Jedis jedis = this.jedisPool.getResource()) {
            return jedis.get(key);
        }
    }

    public String getSet(String key, String value) {
        try (Jedis jedis = this.jedisPool.getResource()) {
            return jedis.getSet(key, value);
        }
    }

    public Long incr(String key) {
        try (Jedis jedis = this.jedisPool.getResource()) {
            return jedis.incr(key);
        }
    }

    public Long incrBy(String key, long increment) {
        try (Jedis jedis = this.jedisPool.getResource()) {
            return jedis.incrBy(key, increment);
        }
    }

    public String set(String key, String value) {
        try (Jedis jedis = this.jedisPool.getResource()) {
            return jedis.set(key, value);
        }
    }

    public String setex(String key, int seconds, String value) {
        try (Jedis jedis = this.jedisPool.getResource()) {
            return jedis.setex(key, seconds, value);
        }
    }

    public Long setnx(String key, String value) {
        try (Jedis jedis = this.jedisPool.getResource()) {
            return jedis.setnx(key, value);
        }
    }

    public Long strlen(String key) {
        try (Jedis jedis = this.jedisPool.getResource()) {
            return jedis.strlen(key);
        }
    }

    /**
     * Execute a series of commands listed above using redis pipeline.
     *
     * @param pipeline a series of commands in order.
     * @return return messages of these commands in order.
     */
    @SuppressWarnings("unchecked")
    public List<Object> pipeline(Pipeline pipeline) {
        if (pipeline == null) {
            throw new NullPointerException("Pipeline is null.");
        }

        int size = pipeline.size();
        try (Jedis jedis = this.jedisPool.getResource()) {
            redis.clients.jedis.Pipeline jedisPipeline = jedis.pipelined();
            for (int i = 0; i < size; i++) {
                PipelineMethod method = pipeline.getPipelineMethod(i);
                List<Object> params = pipeline.getPipelineParams(i);
                String[] strParams = new String[params.size() - 1];
                switch (method) {
                    case GEOADD:
                        if (params.size() == 4) {
                            jedisPipeline.geoadd((String) params.get(0), (Double) params.get(1), (Double) params.get(2), (String) params.get(3));
                        } else {
                            jedisPipeline.geoadd((String) params.get(0), (Map<String, GeoCoordinate>) params.get(0));
                        }
                        break;
                    case GEODIST:
                        jedisPipeline.geodist((String) params.get(0), (String) params.get(1), (String) params.get(2), (GeoUnit) params.get(3));
                        break;
                    case GEOPOS:
                        jedisPipeline.geopos((String) params.get(0), params.stream().skip(1).collect(Collectors.toList()).toArray(strParams));
                        break;
                    case GEORADIUS:
                        if (params.size() == 5) {
                            jedisPipeline.georadius((String) params.get(0), (Double) params.get(1), (Double) params.get(2), (Double) params.get(3),
                                    (GeoUnit) params.get(4));
                        } else {
                            jedisPipeline.georadius((String) params.get(0), (Double) params.get(1), (Double) params.get(2), (Double) params.get(3),
                                    (GeoUnit) params.get(4), (GeoRadiusParam) params.get(5));
                        }
                        break;
                    case GEORADIUSBYMEMBER:
                        if (params.size() == 4) {
                            jedisPipeline.georadiusByMember((String) params.get(0), (String) params.get(1), (Double) params.get(2),
                                    (GeoUnit) params.get(3));
                        } else {
                            jedisPipeline.georadiusByMember((String) params.get(0), (String) params.get(1), (Double) params.get(2),
                                    (GeoUnit) params.get(3), (GeoRadiusParam) params.get(4));
                        }
                        break;
                    case HDEL:
                        jedisPipeline.hdel((String) params.get(0), params.stream().skip(1).collect(Collectors.toList()).toArray(strParams));
                        break;
                    case HEXISTS:
                        jedisPipeline.hexists((String) params.get(0), (String) params.get(1));
                        break;
                    case HGET:
                        jedisPipeline.hget((String) params.get(0), (String) params.get(1));
                        break;
                    case HGETALL:
                        jedisPipeline.hgetAll((String) params.get(0));
                        break;
                    case HINCRBY:
                        jedisPipeline.hincrBy((String) params.get(0), (String) params.get(1), (Long) params.get(2));
                        break;
                    case HKEYS:
                        jedisPipeline.hkeys((String) params.get(0));
                        break;
                    case HLEN:
                        jedisPipeline.hlen((String) params.get(0));
                        break;
                    case HMGET:
                        jedisPipeline.hmget((String) params.get(0), params.stream().skip(1).collect(Collectors.toList()).toArray(strParams));
                        break;
                    case HMSET:
                        if (params.get(1) instanceof Map) {
                            Map<String, String> hash = (Map<String, String>) params.get(1);
                            jedisPipeline.hmset((String) params.get(0), hash);
                        } else {
                            List<String> hmsetParams = params.stream().skip(1).map(p -> (String) p).collect(Collectors.toList());
                            Map<String, String> hash = new HashMap<>();
                            for (int j = 0; j < hmsetParams.size(); j += 2) {
                                hash.put(hmsetParams.get(j), hmsetParams.get(j + 1));
                            }
                            jedisPipeline.hmset((String) params.get(0), hash);
                        }
                        break;
                    case HSET:
                        jedisPipeline.hset((String) params.get(0), (String) params.get(1), (String) params.get(2));
                        break;
                    case HSETNX:
                        jedisPipeline.hsetnx((String) params.get(0), (String) params.get(1), (String) params.get(2));
                        break;
                    case HVALS:
                        jedisPipeline.hvals((String) params.get(0));
                        break;
                    case DEL:
                        jedisPipeline.del((String) params.get(0));
                        break;
                    case EXISTS:
                        jedisPipeline.exists((String) params.get(0));
                        break;
                    case EXPIRE:
                        jedisPipeline.expire((String) params.get(0), (Integer) params.get(1));
                        break;
                    case EXPIREAT:
                        jedisPipeline.expireAt((String) params.get(0), (Long) params.get(1));
                        break;
                    case TTL:
                        jedisPipeline.ttl((String) params.get(0));
                        break;
                    case TYPE:
                        jedisPipeline.type((String) params.get(0));
                        break;
                    case LINDEX:
                        jedisPipeline.lindex((String) params.get(0), (Long) params.get(1));
                        break;
                    case LINSERT:
                        jedisPipeline.linsert((String) params.get(0), (LIST_POSITION) params.get(1), (String) params.get(2), (String) params.get(3));
                        break;
                    case LLEN:
                        jedisPipeline.llen((String) params.get(0));
                        break;
                    case LPOP:
                        jedisPipeline.lpop((String) params.get(0));
                        break;
                    case LPUSH:
                        jedisPipeline.lpush((String) params.get(0), params.stream().skip(1).collect(Collectors.toList()).toArray(strParams));
                        break;
                    case LPUSHX:
                        jedisPipeline.lpushx((String) params.get(0), params.stream().skip(1).collect(Collectors.toList()).toArray(strParams));
                        break;
                    case LRANGE:
                        jedisPipeline.lrange((String) params.get(0), (Long) params.get(1), (Long) params.get(2));
                        break;
                    case LREM:
                        jedisPipeline.lrem((String) params.get(0), (Long) params.get(1), (String) params.get(2));
                        break;
                    case LSET:
                        jedisPipeline.lset((String) params.get(0), (Long) params.get(1), (String) params.get(2));
                        break;
                    case LTRIM:
                        jedisPipeline.ltrim((String) params.get(0), (Long) params.get(1), (Long) params.get(2));
                        break;
                    case RPOP:
                        jedisPipeline.rpop((String) params.get(0));
                        break;
                    case RPUSH:
                        jedisPipeline.rpush((String) params.get(0), params.stream().skip(1).collect(Collectors.toList()).toArray(strParams));
                        break;
                    case RPUSHX:
                        jedisPipeline.rpushx((String) params.get(0), params.stream().skip(1).collect(Collectors.toList()).toArray(strParams));
                        break;
                    case SADD:
                        jedisPipeline.sadd((String) params.get(0), params.stream().skip(1).collect(Collectors.toList()).toArray(strParams));
                        break;
                    case SCARD:
                        jedisPipeline.scard((String) params.get(0));
                        break;
                    case SISMEMBER:
                        jedisPipeline.sismember((String) params.get(0), (String) params.get(1));
                        break;
                    case SMEMBERS:
                        jedisPipeline.smembers((String) params.get(0));
                        break;
                    case SPOP:
                        jedisPipeline.spop((String) params.get(0));
                        break;
                    case SRANDMEMBER:
                        if (params.size() == 1) {
                            jedisPipeline.srandmember((String) params.get(0));
                        } else {
                            jedisPipeline.srandmember((String) params.get(0), (Integer) params.get(1));
                        }
                        break;
                    case SREM:
                        jedisPipeline.srem((String) params.get(0), params.stream().skip(1).collect(Collectors.toList()).toArray(strParams));
                        break;
                    case ZADD:
                        if (params.get(1) instanceof Map) {
                            Map<String, Double> scoreMembers = (Map<String, Double>) params.get(1);
                            jedisPipeline.zadd((String) params.get(0), scoreMembers);
                        } else {
                            Map<String, Double> scoreMembers = new HashMap<>();
                            for (int j = 1; j < params.size(); j += 2) {
                                scoreMembers.put((String) params.get(j + 1), (Double) params.get(j));
                            }
                            jedisPipeline.zadd((String) params.get(0), scoreMembers);
                        }
                        break;
                    case ZADDNX:
                        if (params.get(1) instanceof Map) {
                            Map<String, Double> scoreMembers = (Map<String, Double>) params.get(1);
                            jedisPipeline.zadd((String) params.get(0), scoreMembers, ZAddParams.zAddParams().nx());
                        } else {
                            Map<String, Double> scoreMembers = new HashMap<>();
                            for (int j = 1; j < params.size(); j += 2) {
                                scoreMembers.put((String) params.get(j + 1), (Double) params.get(j));
                            }
                            jedisPipeline.zadd((String) params.get(0), scoreMembers, ZAddParams.zAddParams().nx());
                        }
                        break;
                    case ZADDXX:
                        if (params.get(1) instanceof Map) {
                            Map<String, Double> scoreMembers = (Map<String, Double>) params.get(1);
                            jedisPipeline.zadd((String) params.get(0), scoreMembers, ZAddParams.zAddParams().xx());
                        } else {
                            Map<String, Double> scoreMembers = new HashMap<>();
                            for (int j = 1; j < params.size(); j += 2) {
                                scoreMembers.put((String) params.get(j + 1), (Double) params.get(j));
                            }
                            jedisPipeline.zadd((String) params.get(0), scoreMembers, ZAddParams.zAddParams().xx());
                        }
                        break;
                    case ZADDCH:
                        if (params.get(1) instanceof Map) {
                            Map<String, Double> scoreMembers = (Map<String, Double>) params.get(1);
                            jedisPipeline.zadd((String) params.get(0), scoreMembers, ZAddParams.zAddParams().ch());
                        } else {
                            Map<String, Double> scoreMembers = new HashMap<>();
                            for (int j = 1; j < params.size(); j += 2) {
                                scoreMembers.put((String) params.get(j + 1), (Double) params.get(j));
                            }
                            jedisPipeline.zadd((String) params.get(0), scoreMembers, ZAddParams.zAddParams().ch());
                        }
                        break;
                    case ZADDNXCH:
                        if (params.get(1) instanceof Map) {
                            Map<String, Double> scoreMembers = (Map<String, Double>) params.get(1);
                            jedisPipeline.zadd((String) params.get(0), scoreMembers, ZAddParams.zAddParams().nx().ch());
                        } else {
                            Map<String, Double> scoreMembers = new HashMap<>();
                            for (int j = 1; j < params.size(); j += 2) {
                                scoreMembers.put((String) params.get(j + 1), (Double) params.get(j));
                            }
                            jedisPipeline.zadd((String) params.get(0), scoreMembers, ZAddParams.zAddParams().nx().ch());
                        }
                        break;
                    case ZADDXXCH:
                        if (params.get(1) instanceof Map) {
                            Map<String, Double> scoreMembers = (Map<String, Double>) params.get(1);
                            jedisPipeline.zadd((String) params.get(0), scoreMembers, ZAddParams.zAddParams().xx().ch());
                        } else {
                            Map<String, Double> scoreMembers = new HashMap<>();
                            for (int j = 1; j < params.size(); j += 2) {
                                scoreMembers.put((String) params.get(j + 1), (Double) params.get(j));
                            }
                            jedisPipeline.zadd((String) params.get(0), scoreMembers, ZAddParams.zAddParams().xx().ch());
                        }
                        break;
                    case ZCARD:
                        jedisPipeline.zcard((String) params.get(0));
                        break;
                    case ZCOUNT:
                        if (params.get(1) instanceof String) {
                            jedisPipeline.zcount((String) params.get(0), (String) params.get(1), (String) params.get(2));
                        } else {
                            jedisPipeline.zcount((String) params.get(0), (Double) params.get(1), (Double) params.get(2));
                        }
                        break;
                    case ZINCRBY:
                        jedisPipeline.zincrby((String) params.get(0), (Double) params.get(1), (String) params.get(2));
                        break;
                    case ZLEXCOUNT:
                        jedisPipeline.zlexcount((String) params.get(0), (String) params.get(1), (String) params.get(2));
                        break;
                    case ZRANGE:
                        jedisPipeline.zrange((String) params.get(0), (Long) params.get(1), (Long) params.get(2));
                        break;
                    case ZRANGEWITHSCORES:
                        jedisPipeline.zrangeWithScores((String) params.get(0), (Long) params.get(1), (Long) params.get(2));
                        break;
                    case ZRANGEBYLEX:
                        if (params.size() == 3) {
                            jedisPipeline.zrangeByLex((String) params.get(0), (String) params.get(1), (String) params.get(2));
                        } else {
                            jedisPipeline.zrangeByLex((String) params.get(0), (String) params.get(1), (String) params.get(2), (Integer) params.get(3),
                                    (Integer) params.get(4));
                        }
                        break;
                    case ZRANGEBYSCORE:
                        if (params.size() == 3) {
                            if (params.get(1) instanceof String) {
                                jedisPipeline.zrangeByScore((String) params.get(0), (String) params.get(1), (String) params.get(2));
                            } else {
                                jedisPipeline.zrangeByScore((String) params.get(0), (Double) params.get(1), (Double) params.get(2));
                            }
                        } else {
                            if (params.get(1) instanceof String) {
                                jedisPipeline.zrangeByScore((String) params.get(0), (String) params.get(1), (String) params.get(2),
                                        (Integer) params.get(3), (Integer) params.get(4));
                            } else {
                                jedisPipeline.zrangeByScore((String) params.get(0), (Double) params.get(1), (Double) params.get(2),
                                        (Integer) params.get(3), (Integer) params.get(4));
                            }
                        }
                        break;
                    case ZRANGEBYSCOREWITHSCORES:
                        if (params.size() == 3) {
                            if (params.get(1) instanceof String) {
                                jedisPipeline.zrangeByScoreWithScores((String) params.get(0), (String) params.get(1), (String) params.get(2));
                            } else {
                                jedisPipeline.zrangeByScoreWithScores((String) params.get(0), (Double) params.get(1), (Double) params.get(2));
                            }
                        } else {
                            if (params.get(1) instanceof String) {
                                jedisPipeline.zrangeByScoreWithScores((String) params.get(0), (String) params.get(1), (String) params.get(2),
                                        (Integer) params.get(3), (Integer) params.get(4));
                            } else {
                                jedisPipeline.zrangeByScoreWithScores((String) params.get(0), (Double) params.get(1), (Double) params.get(2),
                                        (Integer) params.get(3), (Integer) params.get(4));
                            }
                        }
                        break;
                    case ZRANK:
                        jedisPipeline.zrank((String) params.get(0), (String) params.get(1));
                        break;
                    case ZREM:
                        jedisPipeline.zrem((String) params.get(0), params.stream().skip(1).collect(Collectors.toList()).toArray(strParams));
                        break;
                    case ZREMRANGEBYLEX:
                        jedisPipeline.zremrangeByLex((String) params.get(0), (String) params.get(1), (String) params.get(2));
                        break;
                    case ZREMRANGEBYRANK:
                        jedisPipeline.zremrangeByRank((String) params.get(0), (Long) params.get(1), (Long) params.get(2));
                        break;
                    case ZREMRANGEBYSCORE:
                        if (params.get(1) instanceof String) {
                            jedisPipeline.zremrangeByScore((String) params.get(0), (String) params.get(1), (String) params.get(2));
                        } else {
                            jedisPipeline.zremrangeByScore((String) params.get(0), (Double) params.get(1), (Double) params.get(2));
                        }
                        break;
                    case ZREVRANGE:
                        jedisPipeline.zrevrange((String) params.get(0), (Long) params.get(1), (Long) params.get(2));
                        break;
                    case ZREVRANGEWITHSCORES:
                        jedisPipeline.zrevrangeWithScores((String) params.get(0), (Long) params.get(1), (Long) params.get(2));
                        break;
                    case ZREVRANGEBYLEX:
                        if (params.size() == 3) {
                            jedisPipeline.zrevrangeByLex((String) params.get(0), (String) params.get(1), (String) params.get(2));
                        } else {
                            jedisPipeline.zrevrangeByLex((String) params.get(0), (String) params.get(1), (String) params.get(2),
                                    (Integer) params.get(3), (Integer) params.get(4));
                        }
                        break;
                    case ZREVRANGEBYSCORE:
                        if (params.size() == 3) {
                            if (params.get(1) instanceof String) {
                                jedisPipeline.zrevrangeByScore((String) params.get(0), (String) params.get(1), (String) params.get(2));
                            } else {
                                jedisPipeline.zrevrangeByScore((String) params.get(0), (Double) params.get(1), (Double) params.get(2));
                            }
                        } else {
                            if (params.get(1) instanceof String) {
                                jedisPipeline.zrevrangeByScore((String) params.get(0), (String) params.get(1), (String) params.get(2),
                                        (Integer) params.get(3), (Integer) params.get(4));
                            } else {
                                jedisPipeline.zrevrangeByScore((String) params.get(0), (Double) params.get(1), (Double) params.get(2),
                                        (Integer) params.get(3), (Integer) params.get(4));
                            }
                        }
                        break;
                    case ZREVRANGEBYSCOREWITHSCORES:
                        if (params.size() == 3) {
                            if (params.get(1) instanceof String) {
                                jedisPipeline.zrevrangeByScoreWithScores((String) params.get(0), (String) params.get(1), (String) params.get(2));
                            } else {
                                jedisPipeline.zrevrangeByScoreWithScores((String) params.get(0), (Double) params.get(1), (Double) params.get(2));
                            }
                        } else {
                            if (params.get(1) instanceof String) {
                                jedisPipeline.zrevrangeByScoreWithScores((String) params.get(0), (String) params.get(1), (String) params.get(2),
                                        (Integer) params.get(3), (Integer) params.get(4));
                            } else {
                                jedisPipeline.zrevrangeByScoreWithScores((String) params.get(0), (Double) params.get(1), (Double) params.get(2),
                                        (Integer) params.get(3), (Integer) params.get(4));
                            }
                        }
                        break;
                    case ZREVRANK:
                        jedisPipeline.zrevrank((String) params.get(0), (String) params.get(1));
                        break;
                    case ZSCORE:
                        jedisPipeline.zscore((String) params.get(0), (String) params.get(1));
                        break;
                    case APPEND:
                        jedisPipeline.append((String) params.get(0), (String) params.get(1));
                        break;
                    case DECR:
                        jedisPipeline.decr((String) params.get(0));
                        break;
                    case DECRBY:
                        jedisPipeline.decrBy((String) params.get(0), (Long) params.get(1));
                        break;
                    case GET:
                        jedisPipeline.get((String) params.get(0));
                        break;
                    case GETSET:
                        jedisPipeline.getSet((String) params.get(0), (String) params.get(1));
                        break;
                    case INCR:
                        jedisPipeline.incr((String) params.get(0));
                        break;
                    case INCRBY:
                        jedisPipeline.incrBy((String) params.get(0), (Long) params.get(1));
                        break;
                    case SET:
                        jedisPipeline.set((String) params.get(0), (String) params.get(1));
                        break;
                    case SETEX:
                        jedisPipeline.setex((String) params.get(0), (Integer) params.get(1), (String) params.get(2));
                        break;
                    case SETNX:
                        jedisPipeline.setnx((String) params.get(0), (String) params.get(1));
                        break;
                    case STRLEN:
                        jedisPipeline.strlen((String) params.get(0));
                        break;
                }
            }

            List<Object> results = jedisPipeline.syncAndReturnAll();
            if (results == null || results.size() != size) {
                throw new IllegalStateException("Redis results is not correct.");
            }

            return results;
        }
    }

}
