package com.infore.controller;

import com.infore.model.ResponseDto;
import com.infore.model.Roles;
import com.infore.service.RoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
@RequestMapping("/role")
@Api(value = "角色功能",description = "角色功能",produces = MediaType.APPLICATION_JSON)
public class RoleController {
    private static final Logger log = LoggerFactory.getLogger(RoleController.class);

    @Autowired
    private RoleService roleService;

    @ApiOperation(value = "增加")
    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public ResponseDto add(@RequestBody Roles roles) {
        roleService.add(roles);
        return new ResponseDto();
    }

    @ApiOperation(value = "修改")
    @RequestMapping(value = "/edit",method = RequestMethod.POST)
    public ResponseDto edit(@RequestBody Roles roles) {
        roleService.update(roles);
        return new ResponseDto();
    }

    @ApiOperation(value = "删除")
    @RequestMapping(value = "/del",method = RequestMethod.GET)
    public ResponseDto del(@RequestParam Integer id) {
        roleService.del(id);
        return new ResponseDto();
    }

    @ApiOperation(value = "查询所有")
    @RequestMapping(value = "/all",method = RequestMethod.GET)
    public ResponseDto all() {
        return new ResponseDto(roleService.queryList());
    }

    @ApiOperation(value = "查询用户应用的角色")
    @RequestMapping(value = "/single",method = RequestMethod.GET)
    public ResponseDto single(@RequestParam Integer userId) {
        return new ResponseDto(roleService.queryByRoleId(userId));
    }
}
