package com.infore.common;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Created by xuyao on 2017/7/7. 配置文件
 */
@Component
public class Properties {

    @Value("${server.context-path}")
    public String CONTEXT_PATH;
    @Value("${jwt.security.key}")
    public String JWT_SECURITY_KEY;
    @Value("${jwt.app.expired}")
    public String JWT_APP_EXPIRED;

    @Value("${seaweedfs.host}")
    public String SEAWEEDFS_HOST;
    @Value("${seaweedfs.port}")
    public String SEAWEEDFS_PORT;
    @Value("${seaweedfs.assign}")
    public String SEAWEEDFS_ASSIGN;
    @Value("${seaweedfs.temp}")
    public String SEAWEEDFS_TEMP;

    @Value("${common.file.host}")
    public String COMMON_FILE_HOST;
    @Value("${common.file.root}")
    public String COMMON_FILE_ROOT;
    @Value("${word.tmpl}")
    public String WORD_TMPL;

    @Value("${hbase.zk.quorum}")
    public String HBASE_QUORUM;

    @Value("${redis.nodes}")
    public String REDIS_NODES;
    @Value("${redis.timeout}")
    public int REDIS_TIMEOUT;
    @Value("${redis.maxAttempts}")
    public int REDIS_MAX_ATTEMPTS;

    @Value("${redis.pool.maxTotal}")
    public int REDIS_POOL_MAXTOTAL;
    @Value("${redis.pool.maxIdle}")
    public int REDIS_POOL_MAXIDLE;
    @Value("${redis.pool.maxWait}")
    public int REDIS_POOL_MAXWAIT;
    @Value("${redis.pool.minIdle}")
    public int REDIS_POOL_MINIDLE;
    @Value("${redis.pool.testOnBorrow}")
    public boolean REDIS_POOL_TESTONBORROW;

    @Value("${redis.sentinel.node1}")
    public String REDIS_SENTINEL_NODE1;
    @Value("${redis.sentinel.node2}")
    public String REDIS_SENTINEL_NODE2;
    @Value("${redis.sentinel.node3}")
    public String REDIS_SENTINEL_NODE3;

    @Value("${redis.sentinel.masterName1}")
    public String REDIS_SENTINEL_MASTERNAME1;
    @Value("${redis.sentinel.masterName2}")
    public String REDIS_SENTINEL_MASTERNAME2;
    @Value("${redis.sentinel.masterName3}")
    public String REDIS_SENTINEL_MASTERNAME3;
    
    @Value("${stream.srvkey}")
    public String STREAM_SRVKEY;
    @Value("${stream.defaultUrl}")
    public String STREAM_DEFAULTURL;

    @Value("${giotp.customerCode}")
    public String GIOTP_CUSTOMERCODE;
    @Value("${giotp.zookeeper}")
    public String GIOTP_ZOOKEEPER_ADDRESS;
    @Value("${giotp.streamPath}")
    public String GIOTP_STREAMPATH;
}
