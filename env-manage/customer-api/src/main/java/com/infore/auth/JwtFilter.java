package com.infore.auth;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.infore.common.Properties;
import com.infore.common.constant.Const;
import com.infore.common.constant.Const.Jwt;
import com.infore.common.enums.ErrorTypeEnum;
import com.infore.common.exception.ServerRuntimeException;
import com.infore.model.User;
import com.infore.service.UserService;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.filter.OncePerRequestFilter;

/**
 * Created by xuyao on 2017/7/7. 对url进行认证过滤
 */
public class JwtFilter extends OncePerRequestFilter {
    @Autowired
    private FilterConfig filterConfig;
    @Value("${server.context-path}")
    private String contextPath;
    @Autowired
    private Properties properties;
    @Autowired
    private UserService userService;

    @Override
    public void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
        throws ServletException, IOException{

        final String authHeader = request.getHeader("E-User-Token");
        /*
		http://localhost:8080/CarsiLogCenter_new/idpstat.jsp?action=idp.sptopn
		request.getRequestURL() http://localhost:8080/CarsiLogCenter_new/idpstat.jsp
		request.getRequestURI() /CarsiLogCenter_new/idpstat.jsp
		request.getContextPath()/CarsiLogCenter_new
		request.getServletPath() /idpstat.jsp
		*/
        List<String> noAuthurls = filterConfig.getNoAuthurls();
        noAuthurls.add(contextPath+"/shutdown:POST");
//        noAuthurls.add(contextPath+"/swagger-ui.html:swagger");
//        noAuthurls.add(contextPath+"/swagger-resources:swagger");
//        noAuthurls.add(contextPath+"/webjars:swagger");
        String requestURl = request.getRequestURI();
        boolean swaggerResource = StringUtils.containsAny(requestURl,"swagger-resources","webjars","swagger-ui.html","/v2/api-docs");
        if (swaggerResource) {
            response.setStatus(HttpServletResponse.SC_OK);
            filterChain.doFilter(request, response);
            return;
        }

        boolean matchUrl = noAuthurls.stream().anyMatch(new Predicate<String>() {
            @Override
            public boolean test(String s) {
                if (StringUtils.substringBefore(s,":").equals(requestURl)) {
                    return true;
                }
                return false;
            }
        });
        if (matchUrl) {
            response.setStatus(HttpServletResponse.SC_OK);
            filterChain.doFilter(request, response);
        } else {
            if (authHeader == null ) {
                throw new ServerRuntimeException("noAuthurls error:"+matchUrl+"-"+requestURl, " Missing or invalid Authorization header");
            }
            final String token = authHeader;
            JwtUtils jwtUtils = new JwtUtils(properties.JWT_SECURITY_KEY);
            jwtUtils.verifyToken(token);
            DecodedJWT decodedJWT=jwtUtils.decodeToken(token);
            String loginName=decodedJWT.getClaim(Jwt.LOGIN_NAME).asString();
            String tid=decodedJWT.getClaim(Const.TID).asString();
            String uid = decodedJWT.getClaim(Jwt.USERID).asString();
            if (StringUtils.isNotBlank(uid)) {
                User user = userService.getUserByid(Integer.parseInt(uid));
                if (Const.YES.equals(user.getForceLogin())) {
                    throw new ServerRuntimeException(ErrorTypeEnum.FORCE_LOGIN.getCode());
                }
                if (Const.YES.equals(user.getLogoutFlag())) {
                    throw new ServerRuntimeException(ErrorTypeEnum.LOGOUT.getCode());
                }
            }

            request.setAttribute(Const.TID,tid);
            request.setAttribute(Const.PARAM_LOGIN_NAME,loginName);
            request.setAttribute(Jwt.USERID,uid);
            filterChain.doFilter(request, response);
        }
    }

    public static void main(String[] args) {
        List<String> noAuthurls = Arrays.asList("/login:","/swagger-ui.html:POST");
        String requestURl = "/swagger-ui.html";
        boolean matchUrl = noAuthurls.stream().anyMatch(new Predicate<String>() {
            @Override
            public boolean test(String s) {
                if (StringUtils.substringBefore(s,":").equals(requestURl)) {
                    return true;
                }
                return false;
            }
        });
        System.out.println("--"+matchUrl);
    }
}
