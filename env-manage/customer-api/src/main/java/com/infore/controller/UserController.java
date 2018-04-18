package com.infore.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.infore.common.constant.Const;
import com.infore.common.constant.Const.Jwt;
import com.infore.common.exception.ServerRuntimeException;
import com.infore.common.util.CodeEncrypt;
import com.infore.model.ResponseDto;
import com.infore.model.User;
import com.infore.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.MediaType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by xuyao on 2017/11/16.
 */
@RestController
@RequestMapping("/user")
@Api(value = "用户功能",description = "用户功能",produces = MediaType.APPLICATION_JSON)
public class UserController {
    private static final Logger log = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @ApiOperation(value = "增加用户")
    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public ResponseDto addUser(HttpServletRequest request, @RequestBody User user) {
        handleUser(request, user);
        try {
            userService.add(user);
        } catch (Exception e) {
            log.error("add user error:{}",e);
            return new ResponseDto(false,"新增用户失败，请联系管理员");
        }
        return new ResponseDto(Const.SUCCESS);
    }

    private void handleUser(HttpServletRequest request, User user) {

        user.setCreaterId(Integer.valueOf((String) request.getAttribute(Jwt.USERID)));
        user.setPassword(CodeEncrypt.encryptSHA(user.getPassword()));
        user.setPwdLastDate(new Date());
    }

    @ApiOperation(value = "用户信息修改（密码除外）")
    @RequestMapping(value = "/modify",method = RequestMethod.POST)
    public ResponseDto modify(HttpServletRequest request, @RequestBody User user) {
        userService.update(user);
        return new ResponseDto();
    }

    @ApiOperation(value = "密码修改")
    @RequestMapping(value = "/upd-pwd", method = RequestMethod.GET)
    public ResponseDto upd(HttpServletRequest request,
        @RequestParam String oldPwd, @RequestParam String newPwd,@RequestParam Integer uid) {
        String jwtUID=(String) request.getAttribute(Jwt.USERID);
        if (uid.intValue() != Integer.valueOf(jwtUID).intValue()) {
            throw new ServerRuntimeException("token与登录用户不匹配");
        }
        if (!userService.checkOldPwd(CodeEncrypt.encryptSHA(oldPwd), uid)) {
            throw new ServerRuntimeException("旧密码不对");
        }
        User updUser = new User();
        updUser.setPassword(CodeEncrypt.encryptSHA(newPwd));
        updUser.setPwdLastDate(new Date());
        updUser.setId(uid);
        userService.update(updUser);
        return new ResponseDto();
    }


    @ApiOperation(value = "查询用户")
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public ResponseDto list(@RequestBody  User user
        , @RequestParam Integer pageNum, @RequestParam Integer pageSize) {
        pageNum = pageNum == null || pageNum <= 0 ? 1 : pageNum;
        pageSize = pageSize == null || pageSize <= 20 ? 20 : pageSize;
        PageHelper.startPage(pageNum, pageSize);
        PageInfo pageInfo = new PageInfo(userService.queryList(user));
        return new ResponseDto(pageInfo);
    }
}
