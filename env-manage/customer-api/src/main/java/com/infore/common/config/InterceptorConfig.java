package com.infore.common.config;

import com.infore.auth.JwtInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Created by xuyao on 2017/7/21.
 * 拦截器配置
 */
//@EnableWebMvc
//@Configuration
public class InterceptorConfig extends WebMvcConfigurerAdapter {
//    @Bean
    JwtInterceptor jwtInterceptor(){
        return new JwtInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
//        super.addInterceptors(registry);
        registry.addInterceptor(jwtInterceptor());
    }
}
