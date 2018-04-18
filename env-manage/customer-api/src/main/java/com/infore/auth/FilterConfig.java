package com.infore.auth;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by xuyao on 2017/7/7.
 */
public class FilterConfig implements EnvironmentAware {
    protected Logger log = LoggerFactory.getLogger(this.getClass());
    private Environment environment;
    @Value("${server.context-path}")
    private String contextPath;
//    private List<String> noAuthurls = new ArrayList<>();
    private List<String> noAuthurls = new CopyOnWriteArrayList<>();

    /**
     * 返回不用验证权限的url list
     * @return
     */
    public List<String> getNoAuthurls() {
        return noAuthurls;
    }


    public void regiestNoAuthUrl(String[] controllerpaths, RequestMapping requestMapping) {
        String[] path = requestMapping.path();
        String method = getMethodStr(requestMapping);
        if (controllerpaths.length == 0) {
            for (int j = 0; j < path.length; j++) {
                regiestNoAuthUrl(contextPath + path[j] + ":" + method);
            }
        } else {
            for (int i = 0; i < controllerpaths.length; i++) {
                for (int j = 0; j < path.length; j++) {
                    regiestNoAuthUrl(contextPath + controllerpaths[i] + path[j] + ":" + method);
                }
            }
        }

    }

    public void regiestNoAuthUrl(String uri) {
        noAuthurls.add(uri);
        log.info("Add NoAuth Url :{}", uri);
    }

    private String getMethodStr(RequestMapping requestMapping) {
        RequestMethod[] method = requestMapping.method();
        if (method.length == 1) {
            switch (method[0]) {
                case GET:
                    return "GET";
                case HEAD:
                    return "HEAD";
                case POST:
                    return "POST";
                case PUT:
                    return "PUT";
                case PATCH:
                    return "PATCH";
                case DELETE:
                    return "DELETE";
                case OPTIONS:
                    return "OPTIONS";
                case TRACE:
                    return "TRACE";
                default:
                    throw new IllegalArgumentException("Invalid HTTP method");
            }
        }
        return "*";
    }

    public String getContextPath() {
        return contextPath;
    }

    @Override
    public void setEnvironment(Environment environment) {
        this.environment = environment;
        log.info("JAVA_HOME:{}",environment.getProperty("JAVA_HOME"));
    }

    public Environment getEnvironment() {
        return environment;
    }
}
