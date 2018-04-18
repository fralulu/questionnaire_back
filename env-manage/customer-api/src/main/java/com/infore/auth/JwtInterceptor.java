package com.infore.auth;

import com.infore.common.Properties;
import com.infore.common.constant.Const;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 * Created by xuyao on 2017/7/21.
 */
public class JwtInterceptor extends HandlerInterceptorAdapter {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private Properties properties;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
        Object handler) throws Exception {
        log.info("-------- preHandle getAttribute:{}----getParameter:{}--",request.getAttribute(
            Const.PARAM_LOGIN_NAME),request.getParameter(Const.PARAM_LOGIN_NAME));
        HandlerMethod handlerMethod=(HandlerMethod) handler;
//        Annotation annotation=handlerMethod.getMethodAnnotation();
        handlerMethod.getResolvedFromHandlerMethod();
        return super.preHandle(request, response, handler);
    }

//    @Override
//    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
//        ModelAndView modelAndView) throws Exception {
////        super.postHandle(request, response, handler, modelAndView);
//        String requestURl = request.getRequestURI();
//        boolean swaggerResource = StringUtils
//            .containsAny(requestURl,"swagger-resources","webjars","swagger-ui.html","/v2/api-docs");
//        if (swaggerResource) {
//            response.setStatus(HttpServletResponse.SC_OK);
//            return;
//        }
//        JwtUtils jwtUtils = new JwtUtils(properties.JWT_SECURITY_KEY);
//        String token=request.getHeader("E-User-Token");
//        DecodedJWT decodedJWT=jwtUtils.decodeToken(token);
//        String LOGIN_NAME=decodedJWT.getClaim(jwt.LOGIN_NAME).asString();
//        String newToken = jwtUtils.generateTokenserName(loginName);
//        response.addHeader("E-User-Token",newToken);
//        Cookie cookie = new Cookie("E-User-Token1",newToken);
//        response.addCookie(cookie);
////        response.getWriter().write(newToken);
//        response.addHeader("Access-Control-Expose-Headers","header1,header2,header3");
//        response.addHeader("E-User-Token2",newToken);
//    }
}
