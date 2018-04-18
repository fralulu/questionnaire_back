package com.infore.controller.login;

import com.infore.auth.JwtUtils;
import com.infore.auth.annotation.NoNeedAuth;
import com.infore.common.Properties;
import com.infore.common.constant.Const;
import com.infore.common.constant.Const.Jwt;
import com.infore.common.enums.ErrorTypeEnum;
import com.infore.common.exception.ServerRuntimeException;
import com.infore.common.util.CodeEncrypt;
import com.infore.model.ResponseDto;
import com.infore.model.User;
import com.infore.service.TenantService;
import com.infore.service.UserRoleService;
import com.infore.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.ws.rs.core.MediaType;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
//@Produces(MediaType.APPLICATION_JSON)
@Api(value = "login",description = "用户登录",produces = "application/json")
public class LogInController {

    private static final Logger log = LoggerFactory.getLogger(LogInController.class);
    @Autowired
    private Properties properties;

    @Autowired
    private UserService userService;

    @Autowired
    private UserRoleService userRoleService;

    @Autowired
    private TenantService tenantService;

    //TODO 先放本地缓存。多个服务器时，可改成redis
    // 用户和Session绑定关系
    public static final Map<String, HttpSession> USR_SESSION = new LinkedHashMap<>();
    // 用户和SessionId的绑定关系
    public static final Map<String, String> USR_SESSIONID = new LinkedHashMap();


    @NoNeedAuth
    @ApiOperation(value = "登录", notes = "认证token(E-User-Token)不作检验,随便输入即可.如:输入123")
    @RequestMapping(value = "/mblogin", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON)
    public ResponseDto login(HttpServletRequest request,HttpServletResponse response,
        @RequestParam(value = Const.PARAM_LOGIN_NAME, required = true) String loginName,
        @RequestParam(value = "pwd", required = true) String pwd) {

        User loginUser = userService.checkUser(loginName, null);
        if (loginUser == null) {
            throw new ServerRuntimeException("登录名错误");
        }
        User user = userService.checkUser(loginName, CodeEncrypt.encryptSHA(pwd));
        if (user == null) {
            throw new ServerRuntimeException("密码错误");
        }

        if (Const.NO.equalsIgnoreCase(user.getLoginFlag())) {
            throw new ServerRuntimeException("用户被停用，请联系管理员");
        }

        updateLoginUserCommon(user);
        String jwtToken = genernatorJwt(user, loginName);
        userLoginHandle(request, user, response);

        User rspUser =getResponeUser(user);
        Map<String, Object> respMap = new HashMap();
        respMap.put("token", jwtToken);
        respMap.put("user", rspUser);

        return new ResponseDto(respMap);

    }

    /**
     * 判断用户是否同时登陆同一个用户
     *
     * */
    @ApiOperation(value = "当前用户在线检测")
    @RequestMapping(value="/checkUserOnline", method = RequestMethod.GET)
    @ResponseBody
    public ResponseDto checkUserOnline(HttpServletRequest request) {
        HttpSession session=request.getSession();
        String msg = (String) session.getAttribute("msg");
        String clientIp = getIpAddr(request);
        String userId = (String) request.getAttribute(Jwt.USERID);
        log.info("client ip:{},userID:{},current session id:{},msg:{}", clientIp, userId, session.getId(),
            session.getAttribute("msg"));
        if (StringUtils.isNotEmpty(msg) &&
            msg.equals(ErrorTypeEnum.REPEAT_LOGIN.getCode())) {
            session.invalidate();
            return new ResponseDto(ErrorTypeEnum.REPEAT_LOGIN.getCode());
        }
        return new ResponseDto(session.getAttribute("msg"));
    }

    /**
     * 用户登出
     * */
    @ApiOperation(value = "用户登出")
    @RequestMapping(value="/logout", method = RequestMethod.GET)
    @ResponseBody
    public ResponseDto logout(HttpServletRequest request
        , @RequestParam(value = Jwt.USERID, required = true) String uid) {
        String jwtUID=(String) request.getAttribute(Jwt.USERID);
        if (!uid.equals(jwtUID)) {
            throw new ServerRuntimeException("token与注销用户不匹配");
        }

        User user = userService.getUserByid(Integer.parseInt(uid));
        user.setLogoutFlag(Const.YES);
        userService.update(user);

//        HttpSession session=request.getSession();
//        session.invalidate();
        return new ResponseDto();
    }

    /**
     * 处理重复登录
     */
    private void userLoginHandle(HttpServletRequest request, User user,
        HttpServletResponse response) {    // 当前登录的用户

        String userId = String.valueOf(user.getId());
        HttpSession currentSession = request.getSession();
        String sessionId = currentSession.getId();
//        Cookie cookie = new Cookie("JSESSIONID11", userId + "-" + sessionId);
//        cookie.setPath("/");
//        response.addCookie(cookie);

        log.info("current session id START :{},userId:{},usr_session map:{},usr_session_id map:{}",
            sessionId, userId,
            USR_SESSION, USR_SESSIONID);
        if (!USR_SESSION.containsKey(userId)) {
            USR_SESSION.put(userId, currentSession);
            USR_SESSIONID.put(userId, sessionId);
            currentSession.removeAttribute("msg");
        }

        if (USR_SESSION.containsKey(userId)
            && !USR_SESSIONID.get(userId).equalsIgnoreCase(sessionId)) {

            USR_SESSIONID.remove(userId);
            // 删除当前登录用户绑定的HttpSession
            HttpSession oldSession = USR_SESSION.remove(userId);
            if (oldSession != null) {
                USR_SESSIONID.put(userId, sessionId);
                USR_SESSION.put(userId, request.getSession());
                currentSession.removeAttribute("msg");
                try {
                    oldSession.setAttribute("msg", ErrorTypeEnum.REPEAT_LOGIN.getCode());
                } catch (IllegalStateException e) {
                    log.info("OLD current session invalidated id:{}",oldSession.getId());
                    throw new ServerRuntimeException("会话过期，请重试");
                }
                log.info("OLD current session id:{},userId:{},msg:{}", oldSession.getId(),userId,
                    oldSession.getAttribute("msg"));
            }
        }
        log.info("current session id END :{},userId:{},usr_session map:{},usr_session_id map:{}",
            sessionId, userId, USR_SESSION, USR_SESSIONID);

    }

    private void updateLoginUserCommon(User user) {
        user.setLoginDate(new Date());
        if (Const.YES.equals(user.getLogoutFlag())) {
            user.setLogoutFlag(Const.NO);
        }
        if (Const.YES.equals(user.getForceLogin())) {
            user.setForceLogin(Const.NO);
        }
        userService.update(user);
    }

    private User getResponeUser(User user) {
        User rspUser = new User();
        BeanUtils.copyProperties(user,rspUser);
        rspUser.setPassword("");
        rspUser.setTenantName(tenantService.getTenantById(rspUser.getTenantId()).getName());
        rspUser.setRoleIds(userRoleService.queryRoleIdsByUserId(user.getId()));
        return rspUser;
    }

    private String genernatorJwt(User user, String loginName) {
        String jwtToken = null;
        try {
            JwtUtils jwtUtils = new JwtUtils(properties.JWT_SECURITY_KEY);
            Map<String, String> claims = new LinkedHashMap<>();
            claims.put(Jwt.LOGIN_NAME, loginName);
            claims.put(Jwt.USERID, user.getId().toString());
            claims.put(Const.TID, user.getTenantId().toString());
            jwtToken = jwtUtils.generateTokenserName(claims);
        } catch (Exception e) {
            throw new ServerRuntimeException("Token生成错误,联系管理员");
        }
        return jwtToken;
    }

    private String getIp(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }

    /**
     * 获取访问者IP
     *
     * 在一般情况下使用Request.getRemoteAddr()即可，但是经过nginx等反向代理软件后，这个方法会失效。
     *
     * 本方法先从Header中获取X-Real-IP，如果不存在再从X-Forwarded-For获得第一个IP(用,分割)，
     * 如果还不存在则调用Request .getRemoteAddr()。
     *
     * @param request
     * @return
     */
    public String getIpAddr(HttpServletRequest request){
        String ip = request.getHeader("X-Real-IP");
        if (!StringUtils.isBlank(ip) && !"unknown".equalsIgnoreCase(ip)) {
            return ip;
        }
        ip = request.getHeader("X-Forwarded-For");
        if (!StringUtils.isBlank(ip) && !"unknown".equalsIgnoreCase(ip)) {
            // 多次反向代理后会有多个IP值，第一个为真实IP。
            int index = ip.indexOf(',');
            if (index != -1) {
                return ip.substring(0, index);
            } else {
                return ip;
            }
        } else {
            return request.getRemoteAddr();
        }
    }
}
