package com.infore.common.hbase;

import com.infore.common.Properties;
import java.io.IOException;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.ConnectionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 *<p> hbase 连接 随容器全局启动初始化
 *<br/>
 *
 *<p> 连接(connection)创建是一个重量级的操作。连接实现是线程安全的，因此客户端可以创建一次连接，并与不同的线程共享。
 *<br/>
 *
 * <p> 表(table)和管理(admin)实例的创建是轻量级且非线程安全的操作<br/>
 * 通常:每个客户端应用程序的单个连接被实例化，每个线程将获得自己的表实例。不推荐使用表和管理员的缓存或池。
 *<br/>
 *
 * Created by xuyao on 2017/9/5.
 */
//@Component
public class HbaseConnect {

  private static final Logger log = LoggerFactory.getLogger(HbaseConnect.class);
  @Autowired
  private Properties properties;

  private Connection connection;

  @PostConstruct
  public void initHbaseConnect() {
    Configuration conf = HBaseConfiguration.create();
    conf.set("hbase.zookeeper.quorum", properties.HBASE_QUORUM);
    try {
      connection = ConnectionFactory.createConnection(conf);
    } catch (IOException e) {
      log.error("Hbase creat connect error:{}", e);
    }
  }

  @PreDestroy
  public void destroy(){
    HbaseOpt.releaseConnect(connection);
  }

  public Connection getConnection() {
    return connection;
  }
}
