package com.infore.common.config;

import com.infore.common.Properties;
//import com.inforeenviro.iot.sdk.ClientConfig;
//import com.inforeenviro.iot.sdk.GIotPClient;
//import com.inforeenviro.iot.sdk.session.MyClientSessionListener;
//import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by xuyao on 2017/12/5.
 */
@Configuration
public class GIotpConfig {

   /* private static final Logger log = LoggerFactory.getLogger(GIotpConfig.class);

    @Autowired
    private Properties properties;

    @Bean
    public GIotPClient GIotPClient() {
        ClientConfig clientConfig = new ClientConfig();
        // 客户端编码
        clientConfig.setSystemCode(properties.GIOTP_CUSTOMERCODE);
        // 注册中心服务器地址
        clientConfig.setServiceRegistry(properties.GIOTP_ZOOKEEPER_ADDRESS);
        // sdk日志文件地址
//        clientConfig.setLogFilePath("d:/sdk.log");
        gbap.log.LoggerFactory.setLevel("INFO");

        // 1、 创建客户端对象，设置通讯参数（必须）
        GIotPClient gIotPClient = new GIotPClient(clientConfig);

        // 2、设置推送消息回调（视客户端情况而定，可选，可以是RPC服务器推送，可以是消息队列推送）
//		 gIotPClient.addNotifyHandler("消息类型", "实现NotifyProcessor接口的回调");

        // 3、设置异步调用接口回调（视客户端情况而定，可选）
//		 EventManager.addEventListener("返回消息类型", "用于在sdk实现异步调用rpc的回调处理暴漏，需要实现EventListener接口");

        // 4、设置连接监听回调（视客户端情况而定，可选）
        gIotPClient.setMyClientSessionListener(new MyClientSessionListener() {
            @Override
            public void sessionDisconnected() {
                log.info("interrupt  GIOTP_server connection");
            }

            @Override
            public void sessionCreated() {
                log.info("connect GIOTP_server success");
            }
        });

        // 5、 客户端订阅的消息列表，扩展的其他消息，按需订阅（可选）
//		gIotPClient.addSubscribeTopic("订阅的消息类型");

        try {
            // 6、启动客户端（必须）
            gIotPClient.startClient();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return gIotPClient;
    }*/
}
