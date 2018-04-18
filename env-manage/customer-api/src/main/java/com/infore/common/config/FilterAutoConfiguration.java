package com.infore.common.config;

import com.infore.auth.FilterAnnotationBeanPostProcessor;
import com.infore.auth.FilterConfig;
import com.infore.auth.JwtFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterAutoConfiguration {

  @Bean
  FilterAnnotationBeanPostProcessor filterAnnotationBeanPostProcessor() {
    return new FilterAnnotationBeanPostProcessor();
  }

  @Bean
  public FilterConfig filterConfig() {
    return new FilterConfig();
  }

  @Bean
  JwtFilter jwtFilter() {
    return new JwtFilter();
  }

}
