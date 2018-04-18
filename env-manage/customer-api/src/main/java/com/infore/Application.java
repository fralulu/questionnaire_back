package com.infore;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.context.annotation.ImportResource;

/**
 * ComponentScan 默认扫描application 所在包下面的所有类
 */
@SpringBootApplication(exclude = MongoAutoConfiguration.class)
@ImportResource("classpath:spring-context.xml")
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
