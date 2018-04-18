package com.infore.common.aspect;

import com.infore.common.Properties;
import com.infore.common.exception.ServerRuntimeException;
import com.infore.common.constant.Const;
import com.infore.model.User;
import com.infore.service.MenuRoleService;
import com.infore.service.UserService;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

/**
 * 用户访问url,权限Aop
 * Created by xuyao on 2017/9/19.
 */
@Aspect
@Component
public class UrlAuthAop {

    private static final Logger log = LoggerFactory.getLogger(UrlAuthAop.class);

    @Autowired
    private UserService userService;

    @Autowired
    private MenuRoleService menuRoleService;

    @Autowired
    private Properties properties;

    @Pointcut("execution(* com.infore.controller..*.*(..)) "
        + "&& !execution(* com.infore.controller.login.*.*(..))"
        + "&& !execution(* com.infore.controller.qhMobile.*.*(..))"
        + "&& !execution(* com.infore.controller.video.VideoController.*(..))"
    )
    private void controllerMethod() {
    }

    @Pointcut("@annotation(com.infore.auth.annotation.NoNeedAuth)")
    private void noNeedAuth() {
    }

//    @Before("controllerMethod() && !noNeedAuth()")
    public void checkUrl(JoinPoint point) {
        RequestAttributes requestAttributes=RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = (HttpServletRequest) requestAttributes
            .resolveReference(RequestAttributes.REFERENCE_REQUEST);
        String loginName = (String) request.getAttribute(Const.PARAM_LOGIN_NAME);
        String url = request.getRequestURI();
        String split=properties.CONTEXT_PATH;
        url=StringUtils.substringAfter(url, split);
        log.info("authURL user:[{}],aop url:[{}]",loginName,url);
        User user=userService.getUserByLoginName(loginName);
        if (user == null) {
            log.error("loginName[{}] not exist", loginName);
            throw new ServerRuntimeException("登录用户不存在，联系管理员");
        }
        boolean authFlag=menuRoleService.checkUserMenu(user.getId(), url);
        if (!authFlag) {
            throw new ServerRuntimeException("用户无此数据访问权限");
        }

    }
}
