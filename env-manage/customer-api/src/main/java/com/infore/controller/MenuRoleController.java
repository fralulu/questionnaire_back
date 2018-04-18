package com.infore.controller;

import com.infore.common.reqVO.MenuRolesVO;
import com.infore.model.MenuRole;
import com.infore.model.ResponseDto;
import com.infore.service.MenuRoleService;
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
@RequestMapping("/menu-role")
@Api(value = "菜单对应角色功能",description = "菜单对应角色功能",produces = MediaType.APPLICATION_JSON)
public class MenuRoleController {
    private static final Logger log = LoggerFactory.getLogger(MenuRoleController.class);

    @Autowired
    private MenuRoleService menuRoleService;

    @ApiOperation(value = "增加")
    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public ResponseDto add(@RequestBody MenuRole menuRole) {
        menuRoleService.add(menuRole);
        return new ResponseDto();
    }

//    @ApiOperation(value = "修改")
//    @RequestMapping(value = "/edit",method = RequestMethod.POST)
//    public ResponseDto edit(@RequestBody MenuRole menuRole) {
//        menuRoleService.update(menuRole);
//        return new ResponseDto();
//    }

//    @ApiOperation(value = "删除")
//    @RequestMapping(value = "/del",method = RequestMethod.GET)
//    public ResponseDto del(@RequestParam Integer id) {
//        menuRoleService.del(id);
//        return new ResponseDto();
//    }

    @ApiOperation(value = "根据角色ID查询菜单项")
    @RequestMapping(value = "/menus",method = RequestMethod.GET)
    public ResponseDto menus(@RequestParam Integer roleId) {
        List<MenuRole> menuRoles=menuRoleService.queryByRoleId(roleId);
        return new ResponseDto(menuRoles);
    }

    @ApiOperation(value = "关联关系项")
    @RequestMapping(value = "/relation", method = RequestMethod.POST)
    public ResponseDto relation(@RequestBody MenuRolesVO menuRoles) {

        boolean flag;
        if (menuRoleService.isExistMenusByRoleId(menuRoles.getRoleId())) {
            flag = menuRoleService.delAndInsert(menuRoles.getRoleId(), menuRoles.getMenuIds());
        } else {
            flag = menuRoleService.batchInsert(menuRoles.getRoleId(), menuRoles.getMenuIds()) != 0;
        }
        return new ResponseDto(flag, flag ? "添加成功" : "添加失败");
    }

    @ApiOperation(value = "分配")
    @RequestMapping(value = "/allocate", method = RequestMethod.POST)
    public ResponseDto allocate(@RequestBody MenuRolesVO menuRoles) {

        boolean flag = menuRoleService.batchInsert(menuRoles.getRoleId(), menuRoles.getMenuIds()) != 0;
        return new ResponseDto(flag, flag ? "分配成功" : "分配失败");
    }

    @ApiOperation(value = "取消分配")
    @RequestMapping(value = "/cancel", method = RequestMethod.POST)
    public ResponseDto cancel(@RequestBody MenuRolesVO menuRoles) {
        boolean flag = menuRoleService.batchDelete(menuRoles.getRoleId(), menuRoles.getMenuIds()) != 0;
        return new ResponseDto(flag, flag ? "取消成功" : "取消失败");
    }
}
