package com.infore.service.impl;

import com.infore.mapper.MenuRoleMapper;
import com.infore.model.MenuRole;
import com.infore.service.MenuRoleService;
import java.util.LinkedList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by xuyao on 2017/9/14.
 */

@Service
public class MenuRoleServiceImpl implements MenuRoleService {

    private static final Logger log = LoggerFactory.getLogger(MenuRoleServiceImpl.class);

    @Autowired
    private MenuRoleMapper menuRoleMapper;

    @Override
    public int add(MenuRole menuRole) {
        return 0;
    }

    @Override
    public int update(MenuRole menuRole) {
        return 0;
    }

    @Override
    public int del(Integer id) {
        return 0;
    }

    @Override
    public boolean checkUserMenu(int user_id, String url) {
        return menuRoleMapper.countByUrl(user_id, url) >= 1 ? true : false;
    }

    @Override
    public List<MenuRole> queryByRoleId(Integer roleId) {
        return menuRoleMapper.queryByParam(new MenuRole(){{setRoleId(roleId);}});
    }

    @Override
    public int delByRoleId(Integer roleId) {
        return menuRoleMapper.deleteByRoleId(roleId);
    }

    @Override
    public boolean isExistMenusByRoleId(Integer roleId) {
        return menuRoleMapper.countByRoleId(roleId) == 0 ? false : true;
    }

    @Override
    public int batchInsert(Integer roleId, List<Integer> menuIds) {
        List<MenuRole> menuRoles = new LinkedList<>();
        for (Integer menuId: menuIds
        ) {
            MenuRole menuRole = new MenuRole();
            menuRole.setRoleId(roleId);
            menuRole.setMenuId(menuId);
            menuRoles.add(menuRole);
        }
        return menuRoleMapper.batchInsert(menuRoles);
    }

    @Override
    @Transactional
    public boolean delAndInsert(Integer roleId, List<Integer> menuIds) {
        int del = delByRoleId(roleId);
        int ins = batchInsert(roleId, menuIds);
        boolean flag = (del != 0) && (ins != 0);
        return flag;
    }

    @Override
    public boolean isExistMenusByMenuId(Integer menuId) {
        return menuRoleMapper.countByMenuId(menuId) == 0 ? false : true;
    }

    @Override
    public int batchDelete(Integer roleId, List<Integer> menuIds) {
        return menuRoleMapper.deleteRoleByMenuIds(roleId, menuIds);
    }
}
