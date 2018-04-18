package com.infore.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.infore.auth.annotation.NoNeedAuth;
import com.infore.common.constant.Const;
import com.infore.common.enums.MenuTypeEnum;
import com.infore.model.Menu;
import com.infore.model.MenuRole;
import com.infore.model.ResponseDto;
import com.infore.service.MenuRoleService;
import com.infore.service.MenuService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.LinkedList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
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
 * Created by xuyao on 28/12/2017.
 */
@RestController
@RequestMapping("/menu")
@Api(value = "菜单功能",description = "菜单功能",produces = MediaType.APPLICATION_JSON)
public class MenuController {
    private static final Logger log = LoggerFactory.getLogger(MenuController.class);

    @Autowired
    private MenuService menuService;

    @Autowired
    private MenuRoleService menuRoleService;

    @ApiOperation(value = "增加菜单")
    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public ResponseDto add(@RequestBody @Valid Menu menu) {
        menuService.add(menu);
        return new ResponseDto();
    }

    @ApiOperation(value = "修改菜单")
    @RequestMapping(value = "/edit",method = RequestMethod.POST)
    public ResponseDto edit(@RequestBody @Valid Menu menu) {
        menuService.update(menu);
        return new ResponseDto();
    }

    @ApiOperation(value = "删除菜单")
    @RequestMapping(value = "/del", method = RequestMethod.POST)
    public ResponseDto del(@RequestParam Integer id) {
        if (menuRoleService.isExistMenusByMenuId(id)){
            return new ResponseDto(false,"已关联了角色不允许删除");
        }
        menuService.del(id);
        return new ResponseDto();
    }

    @ApiOperation(value = "查询数据资源")
    @RequestMapping(value = "/source",method = RequestMethod.GET)
    public ResponseDto source(@RequestParam(required = false) String queryLike
        , @RequestParam Integer pageNum, @RequestParam Integer pageSize) {
        startPage(pageNum,pageSize);
        List<Menu> sources=menuService.querySources(queryLike);
        PageInfo pageInfo = new PageInfo(sources);
        return new ResponseDto(pageInfo);
    }

    @ApiOperation(value = "查询所有菜单，包含角色ID下已选中的")
    @RequestMapping(value = "/role-source",method = RequestMethod.GET)
    public ResponseDto roleSource(@RequestParam(required = false) String queryLike,@RequestParam Integer roleId
        , @RequestParam Integer pageNum, @RequestParam Integer pageSize) {
        startPage(pageNum,pageSize);
        List<Menu> sources = menuService.querySources(queryLike);
        List<Menu> roleMenus = menuService.queryMenusByRoleId(roleId, MenuTypeEnum.TYPE_DEFAULT_99.getCode());
        for (Menu m: sources) {
            boolean checked = roleMenus.stream().anyMatch(rm -> rm.getId() == m.getId());
            if (checked) {
                m.setChecked(Const.YES);
            }
        }
        PageInfo pageInfo = new PageInfo(sources);
        return new ResponseDto(pageInfo);
    }

    @NoNeedAuth
    @ApiOperation(value = "查询菜单树")
    @RequestMapping(value = "/tree",method = RequestMethod.GET)
    public ResponseDto tree(HttpServletRequest request) {
        String trees=menuService.getMenuTrees();
        return new ResponseDto(trees);
    }

    @ApiOperation(value = "根据角色ID列表，查询菜单树")
    @RequestMapping(value = "/role-ids-tree",method = RequestMethod.GET)
    public ResponseDto treeByRoleIds(@RequestParam List<Integer> roleIds) {
        String trees=menuService.getMenuTreeByRoleIds(roleIds);
        return new ResponseDto(trees);
    }

    @ApiOperation(value = "根据角色id查询数据资源")
    @RequestMapping(value = "/role-id-source", method = RequestMethod.GET)
    public ResponseDto sourceByRoleId(@RequestParam Integer roleId
        , @RequestParam Integer pageNum, @RequestParam Integer pageSize
    ) {
        startPage(pageNum,pageSize);
        List<Menu> sources = menuService.querySourcesByRoleId(roleId);
        return new ResponseDto(new PageInfo(sources));
    }

    @ApiOperation(value = "根据角色id查询功能菜单项")
    @RequestMapping(value = "/role-id-menu", method = RequestMethod.GET)
    public ResponseDto menuByRoleId(HttpServletRequest request, @RequestParam Integer roleId) {

        List<Menu> sources = menuService.queryMenusByRoleId(roleId,MenuTypeEnum.TYPE_1.getCode());
        return new ResponseDto(sources);
    }

    private void startPage(Integer pageNum, Integer pageSize) {
        pageNum = pageNum == null || pageNum <= 0 ? 1 : pageNum;
        pageSize = pageSize == null || pageSize <= 20 ? 20 : pageSize;
        PageHelper.startPage(pageNum, pageSize);
    }

}
