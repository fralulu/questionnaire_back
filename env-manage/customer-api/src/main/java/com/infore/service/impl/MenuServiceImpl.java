package com.infore.service.impl;

import com.infore.common.enums.MenuTypeEnum;
import com.infore.common.util.MenuTreeBuilder;
import com.infore.mapper.MenuMapper;
import com.infore.model.Menu;
import com.infore.service.MenuService;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * Created by xuyao on 2017/9/14.
 */
@Service
public class MenuServiceImpl implements MenuService {

    private static final Logger log = LoggerFactory.getLogger(MenuServiceImpl.class);

    @Autowired
    private MenuMapper menuMapper;

    @Override
    public int add(Menu menu) {
        return menuMapper.insertSelective(menu);
    }

    @Override
    public int update(Menu menu) {
        return menuMapper.updateByPrimaryKeySelective(menu);
    }

    @Override
    public int del(Integer id) {
        return menuMapper.deleteByPrimaryKey(id);
    }

    @Override
    public List<Menu> querySources(String queryLike) {
        return menuMapper.querySources(MenuTypeEnum.TYPE_DEFAULT_99.getCode(), queryLike);
    }

    @Override
    public String getMenuTrees() {
        List<Menu> sourceMenus = menuMapper.queryByParam(new Menu(){{setType(MenuTypeEnum.TYPE_1.getCode());}});
        String json = new MenuTreeBuilder().buildTree(sourceMenus);
        return json;
    }

    @Override
    @Cacheable(value = "menuCache", key = "#pid")
    public List<Menu> getMenusByPid(Integer pid) {
        return menuMapper.queryByParam(new Menu() {{
            setPid(pid);
        }});
    }

    @Override
    public List<Menu> querySourcesByRoleId(Integer roleId) {
        return menuMapper.querySourcesByRoleId(roleId);
    }

    @Override
    public List<Menu> queryMenusByRoleId(Integer roleId,Integer type) {
        return menuMapper.queryMenuIdByRoleId(roleId,new Menu(){{setType(type);}});
    }

    @Override
    public String getMenuTreeByRoleIds(List<Integer> roleIds) {
        List<Menu> sourceMenus = menuMapper.queryMenuByRoleIds(roleIds);
        String json = new MenuTreeBuilder().buildTree(sourceMenus);
        return json;
    }
}
