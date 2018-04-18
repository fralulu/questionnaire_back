package com.infore.common.config;

import com.google.common.cache.CacheBuilder;
import com.google.common.hash.Hashing;
import java.lang.reflect.Array;
import java.lang.reflect.Method;
import java.nio.charset.Charset;
import java.util.TreeSet;
import java.util.concurrent.TimeUnit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.guava.GuavaCacheManager;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.ClassUtils;

/**
 * 配置guava缓存
 * Created by xuyao on 2017/9/18.
 */
@Configuration
@EnableCaching
public class GuavaCacheConfig {

    private static final Logger log = LoggerFactory.getLogger(GuavaCacheConfig.class);

    @Bean
    public CacheManager cacheManager() {
        GuavaCacheManager cacheManager = new GuavaCacheManager();
        CacheBuilder<Object, Object> cacheBuilder = CacheBuilder.newBuilder()
            .maximumSize(100)
            .expireAfterWrite(30, TimeUnit.SECONDS);

        cacheManager.setCacheNames(new TreeSet<String>(){{add("menuCache");add("meteTypeCache");}});
        cacheManager.setCacheBuilder(cacheBuilder);
        return cacheManager;
    }

//    @Bean
    public KeyGenerator keyGenerator() {
        return new CacheKeyGenerator();
    }

    class CacheKeyGenerator implements KeyGenerator {

        // custom cache key
        public static final int NO_PARAM_KEY = 0;
        public static final int NULL_PARAM_KEY = 53;

        @Override
        public Object generate(Object target, Method method, Object... params) {

            StringBuilder key = new StringBuilder();
            key.append(target.getClass().getSimpleName()).append(".").append(method.getName())
                .append(":");
            if (params.length == 0) {
                return key.append(NO_PARAM_KEY).toString();
            }
            for (Object param : params) {
                if (param == null) {
                    log.warn("input null param for Spring cache, use default key={}",
                        NULL_PARAM_KEY);
                    key.append(NULL_PARAM_KEY);
                } else if (ClassUtils.isPrimitiveArray(param.getClass())) {
                    int length = Array.getLength(param);
                    for (int i = 0; i < length; i++) {
                        key.append(Array.get(param, i));
                        key.append(',');
                    }
                } else if (ClassUtils.isPrimitiveOrWrapper(param.getClass())
                    || param instanceof String) {
                    key.append(param);
                } else {
                    log.warn("Using an object as a cache key may lead to unexpected results. " +
                        "Either use @Cacheable(key=..) or implement CacheKey. Method is " + target
                        .getClass() + "#" + method.getName());
                    key.append(param.hashCode());
                }
                key.append('-');
            }

            String finalKey = key.toString();
            long cacheKeyHash = Hashing.murmur3_128().hashString(finalKey, Charset.defaultCharset())
                .asLong();
            log.debug("using cache key={} hashCode={}", finalKey, cacheKeyHash);
            return key.toString();
        }
    }

}
