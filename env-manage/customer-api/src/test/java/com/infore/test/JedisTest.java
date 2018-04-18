package com.infore.test;

//import com.infore.common.redisclient.RedisClient;
//import com.infore.common.redisclient.RedisClientFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;

/**
 * Created by xuyao on 2017/11/13.
 */
//@RunWith(SpringJUnit4ClassRunner.class)
//@SpringBootTest
public class JedisTest {

//    @Autowired
//    private JedisCluster jedisCluster;

   /* @Autowired
    private RedisClient sentinelRedisClient;

    @Autowired
    private RedisClient clusterRedisClient;

    @Autowired
    private RedisClientFactory redisClientFactory;

    @Test
    public void test() {
//        jedisCluster.set("oktest", "come on ");
        System.out.println("-------");
    }

    @Test
    public void testredisClient() {
        System.out.println(sentinelRedisClient.get("key11")+sentinelRedisClient.toString());
        System.out.println("======="+clusterRedisClient.toString());
        Jedis jedis = new Jedis();
        jedis.set("", "","NX");
    }
    @Test
    public void testClientFactory() {
        System.out.println(redisClientFactory.getRedisClient().get("key11")+"--"+redisClientFactory.getRedisClient().toString());
    }*/

}
