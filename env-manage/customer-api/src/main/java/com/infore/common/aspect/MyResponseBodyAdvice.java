package com.infore.common.aspect;

import com.infore.auth.JwtUtils;
import com.infore.auth.annotation.NoNeedAuth;
import com.infore.common.Properties;
import com.infore.common.exception.ServerRuntimeException;
import com.infore.common.constant.Const;
import com.infore.model.ResponseDto;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.AnnotatedElement;
import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/**
 * Created by xuyao on 2017/7/21.
 * aop思想:在controller方法执行完毕后 @responsebody生效前修改返回值
 */
//@ControllerAdvice
public class MyResponseBodyAdvice implements ResponseBodyAdvice {
    private final Logger log = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private Properties properties;

    @Override
    public boolean supports(MethodParameter returnType, Class converterType) {
        log.info("------ response body aop supports call------");
        AnnotatedElement annotatedElement=returnType.getAnnotatedElement();
        boolean authflag = annotatedElement.isAnnotationPresent(NoNeedAuth.class);
        return  authflag?false:returnType.getMethod().getReturnType().isAssignableFrom(ResponseDto.class);
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType,
        MediaType selectedContentType, Class selectedConverterType, ServerHttpRequest request,
        ServerHttpResponse response) {
        log.info("-----response body beforeBodyWrite call-------");
        ResponseDto responseDto = (ResponseDto) body;
        ServletServerHttpRequest servletRequest = (ServletServerHttpRequest) request;
        HttpServletRequest req =servletRequest.getServletRequest();
        String loginName = (String) req.getAttribute(Const.PARAM_LOGIN_NAME);
        JwtUtils jwtUtils = null;
        try {
            jwtUtils = new JwtUtils(properties.JWT_SECURITY_KEY);
        } catch (UnsupportedEncodingException e) {
            log.error("response body beforeBodyWrite call error:{}",e);
            throw new ServerRuntimeException("jwt编码格式不支持,联系管理员");
        }
        String newToken = jwtUtils.generateTokenserName(loginName);
        responseDto.setRefreshToken(newToken);
        return responseDto;
    }
}
