#修改：
> 代码修改 
 >>     屏蔽：RedisClientShardingConfig类的注解//@Configuration 
 >>     屏蔽com.infore.common.config.GIotpConfig类
 >>     屏蔽：com.infore.common.thread.VideoStreamZKWatchProcess类
 >>     屏蔽：com.infore.common.util.FileClient类
 >>     屏蔽：com.infore.common.GIotpActionUtils类
 >>     屏蔽：com.infore.controller.file.FileOptController类
 >>     屏蔽：com.infore.common.thread.VideoAutoPushRunnable类
 >>     屏蔽：com.infore.common.redisclient.ClusterRedisClient类注解@Component
 >>     屏蔽：com.infore.common.redisclient.RedisClientFactory类的注解@Component
 >>     屏蔽：com.infore.common.redisclient.SentinelRedisClient类的注解@Component
  
  
 ```
 customer-api中pom.xml添加：
  <dependency>
            <groupId>jdk.tools</groupId>
            <artifactId>jdk.tools</artifactId>
            <version>1.8</version>
            <scope>system</scope>
            <systemPath>${JAVA_HOME}/lib/tools.jar</systemPath>
  </dependency>
  
 ```
 ```
  manage项目中的pom.xml，屏蔽
        <!--  <gbap.rpc.version>1.0.1</gbap.rpc.version>
        <giot.sdk.version>1.0.1</giot.sdk.version>
        <giot.server.version>1.0.1</giot.server.version> -->
        
  customer-api中pom.xml,屏蔽
  <!-- <dependency>
            <groupId>com.inforeenviro</groupId>
            <artifactId>GIotP-Sdk</artifactId>
            <version>${giot.sdk.version}</version>
          <exclusions>
            <exclusion>
              <artifactId>rocketmq-srvutil</artifactId>
              <groupId>com.alibaba.rocketmq</groupId>
            </exclusion>
          </exclusions>
        </dependency> -->
 ```
 #qh项目变更
 ```
>  mybatis insert后要求返还id:
     需要添加：
       keyProperty="id" useGeneratedKeys="true"  
       例如：
       <insert id="insertSelective" parameterType="com.infore.model.Site" keyProperty="siteId" useGeneratedKeys="true">  
       
> 
 ```
 
 
 
 
 
 