package com.infore.common.aspect;

import com.infore.auth.FilterConfig;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;
import javax.ws.rs.FormParam;
import javax.ws.rs.QueryParam;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

//@Aspect
//@Component
public class ControllerChecker {

    private static final Logger logger = LoggerFactory.getLogger(ControllerChecker.class);

    @Autowired
    private FilterConfig filterConfig;

    @Before("execution(* com.infore.controller.*.*(javax.servlet.http.HttpServletRequest, ..)) || " +
            "(execution(* com.infore.controller.*.*.*(javax.servlet.http.HttpServletRequest, ..)) && " +
            "!execution(* com.infore.controller.pretreatment.*.*(javax.servlet.http.HttpServletRequest, ..)))")
    public void checkToken(JoinPoint point) {
        Object[] args = point.getArgs();
       // HttpServletRequest request = (HttpServletRequest) args[0];
       /* String type = request.getHeader("type");
        String accessToken = request.getHeader("accessToken");*/
        // .....
        List<String> noAuthurls=filterConfig.getNoAuthurls();
        logger.info("--aop---noAuthurls----:{}",noAuthurls.get(0));

//        getParameters(point.getSignature(), args);
    }

    private void getParameters(Signature sig, Object[] values) {
        if (sig == null) {
            throw new NullPointerException("Sig is null.");
        }

        String methodName = sig.getName();
        Class<?> classType = sig.getDeclaringType();
        String[] splits = sig.toLongString().split("[\\(\\),]");
        if (splits.length != values.length + 1) {
            throw new IllegalStateException("Size of method param type is not match.");
        }

        Method method;
        try {
            if (splits.length == 1) {
                method = classType.getMethod(methodName);
            } else {
                Class<?>[] classes = new Class<?>[splits.length - 1];
                for (int i = 1; i < splits.length; i++) {
                    Class<?> c = Class.forName(splits[i]);
                    classes[i - 1] = c;
                }
                method = classType.getMethod(methodName, classes);
            }
        } catch (NoSuchMethodException | ClassNotFoundException e) {
            throw new IllegalStateException("Fail to reflect class or method.");
        }

        Annotation[][] annotations = method.getParameterAnnotations();
        TreeMap<String, String> tm = new TreeMap<>();
        Map<String, Object> parameterMap = new LinkedHashMap<>();
        for (int i = 1; i < annotations.length; i++) {
            if (values[i] == null) {
                continue;
            }

            Annotation[] paramAnnotations = annotations[i];
            if (paramAnnotations == null || paramAnnotations.length <= 0) {
                continue;
            }

            for (Annotation annotation : paramAnnotations) {
                String key = null;
                if (annotation.annotationType() == QueryParam.class) {
                    key = ((QueryParam) annotation).value();
                } else if (annotation.annotationType() == FormParam.class) {
                    key = ((FormParam) annotation).value();
                }

                if (key != null) {
                    tm.put(key, values[i].toString());
                    parameterMap.put(key, values[i]);
                    break;
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        while (!tm.isEmpty()) {
            Entry<String, String> entry = tm.pollFirstEntry();
            if (sb.length() == 0) {
                sb.append(entry.getKey()).append("=").append(entry.getValue());
            } else {
                sb.append("&").append(entry.getKey()).append("=").append(entry.getValue());
            }
        }

        logger.info("Class: {}, Method: {}, Params: {}.", sig.getDeclaringTypeName(), methodName, parameterMap);
    }
}
