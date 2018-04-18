package com.infore.common.redisclient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by xuyao on 2017/11/20.
 * 工厂方法产生redis client，对调用方透明。
 * 可以是cluster集群方式client；主从模式sentinelRedisClient；等等
 *
 */
//@Component
public class RedisClientFactory {
    @Autowired
    private RedisClient sentinelRedisClient;

    @Autowired
    private RedisClient clusterRedisClient;

    public RedisClient getRedisClient() {
//        if (false) {
//            return clusterRedisClient;
//        }
        return sentinelRedisClient;
    }
}
