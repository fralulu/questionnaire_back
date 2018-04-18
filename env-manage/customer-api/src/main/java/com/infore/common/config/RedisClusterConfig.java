package com.infore.common.config;

import com.infore.common.Properties;
import java.util.HashSet;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;

/**
 * Created by xuyao on 2017/11/13.
 */
//@Configuration
public class RedisClusterConfig {

    @Autowired
    private Properties properties;

//    @Bean
    public JedisCluster jedisCluster() {
        String[] serverArray = properties.REDIS_NODES.split(",");
        Set<HostAndPort> nodes = new HashSet<>();

        for (String ipPort : serverArray) {
            String[] ipPortPair = ipPort.split(":");
            nodes.add(new HostAndPort(ipPortPair[0].trim(), Integer.valueOf(ipPortPair[1].trim())));
        }
        return new JedisCluster(nodes, properties.REDIS_TIMEOUT,properties.REDIS_MAX_ATTEMPTS);
    }

}
