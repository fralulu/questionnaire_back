package com.infore.common.redisclient;

import redis.clients.jedis.JedisShardInfo;
import redis.clients.jedis.ShardedJedis;
import redis.clients.util.Hashing;

import java.util.List;
import java.util.regex.Pattern;

public class SentinelShardedJedis extends ShardedJedis {

    protected SentinelShardedJedisPool dataSource = null;

    public SentinelShardedJedis(List<JedisShardInfo> shards) {
        super(shards);
    }

    public SentinelShardedJedis(List<JedisShardInfo> shards, Hashing algo) {
        super(shards, algo);
    }

    public SentinelShardedJedis(List<JedisShardInfo> shards, Pattern keyTagPattern) {
        super(shards, keyTagPattern);
    }

    public SentinelShardedJedis(List<JedisShardInfo> shards, Hashing algo, Pattern keyTagPattern) {
        super(shards, algo, keyTagPattern);
    }

    public void setDataSource(SentinelShardedJedisPool sentinelShardedJedisPool) {
        this.dataSource = sentinelShardedJedisPool;
    }

}