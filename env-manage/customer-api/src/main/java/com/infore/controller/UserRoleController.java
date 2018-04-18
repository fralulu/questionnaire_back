package com.infore.controller;

import com.infore.model.ResponseDto;
import com.infore.model.UserRole;
import com.infore.service.UserRoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.List;
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
 * Created by xuyao on 02/01/2018.
 */
@RestController
@RequestMapping("/user-role")
@Api(value = "用户角色功能",description = "用户角色功能",produces = MediaType.APPLICATION_JSON)
public class UserRoleController {
    private static final Logger log = LoggerFactory.getLogger(UserRoleController.class);

    @Autowired
    private UserRoleService userRoleService;

//    @ApiOperation(value = "添加用户角色关联")
//    @RequestMapping(value = "/add",method = RequestMethod.POST)
//    public ResponseDto add(@RequestBody UserRole userRole) {
//        userRoleService.add(userRole);
//        return new ResponseDto();
//    }

//    @ApiOperation(value = "删除关系")
//    @RequestMapping(value = "/del",method = RequestMethod.POST)
//    public ResponseDto del(@RequestBody UserRole userRole) {
//        userRoleService.del(userRole.getRoleId(),userRole.getUserId());
//        return new ResponseDto();
//    }

    @ApiOperation(value = "关联关系编辑")
    @RequestMapping(value = "/relation", method = RequestMethod.GET)
    public ResponseDto relation(@RequestParam Integer userId, @RequestParam List<Integer> roleIds) {
        boolean flag;
        if (userRoleService.isExistUserId(userId)) {
            flag = userRoleService.delAndInsert(userId, roleIds);
        } else {
            flag = userRoleService.batchInsert(userId, roleIds) != 0;
        }
        return new ResponseDto(flag, flag ? "添加成功" : "添加失败");
    }


    @ApiOperation(value = "查询所有")
    @RequestMapping(value = "/all",method = RequestMethod.GET)
    public ResponseDto all() {
        return new ResponseDto(userRoleService.queryAll());
    }
}
