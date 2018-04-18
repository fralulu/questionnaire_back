package com.infore.auth;

import com.infore.auth.annotation.NoNeedAuth;
import java.lang.reflect.Method;
import java.util.Map;
import javax.ws.rs.Path;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.core.MethodIntrospector;
import org.springframework.core.annotation.AnnotatedElementUtils;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by xuyao on 2017/7/7.
 */
public class FilterAnnotationBeanPostProcessor implements BeanPostProcessor {
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private FilterConfig filterConfig;

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        final Class<?> beanType = bean.getClass();

        if (isHandler(bean.getClass())) {
            String[] controllerpaths = new String[]{};
            RequestMapping annotation = AnnotationUtils.findAnnotation(beanType, RequestMapping.class);
            if (annotation != null) {
                controllerpaths = annotation.path();
            }
            Map<Method, NoNeedAuth> methods = MethodIntrospector.selectMethods(beanType,
                    new MethodIntrospector.MetadataLookup<NoNeedAuth>() {
                        @Override
                        public NoNeedAuth inspect(Method method) {
                            return getNoAuthForMethod(method);
                        }
                    });

            for (Map.Entry<Method, NoNeedAuth> entry : methods.entrySet()) {
                if (entry.getValue() != null) {
                    RequestMapping requestMapping = getMappingForMethod(entry.getKey());
                    filterConfig.regiestNoAuthUrl(controllerpaths, requestMapping);

                }
            }

        }
        return bean;
    }


    protected boolean isHandler(Class<?> beanType) {
        return ((AnnotationUtils.findAnnotation(beanType, Controller.class) != null) ||
                (AnnotationUtils.findAnnotation(beanType, Path.class) != null));
    }

    private RequestMapping getMappingForMethod(Method method) {
        return AnnotatedElementUtils.findMergedAnnotation(method, RequestMapping.class);
    }

    private NoNeedAuth getNoAuthForMethod(Method method) {
        return AnnotatedElementUtils.findMergedAnnotation(method, NoNeedAuth.class);
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }

}
