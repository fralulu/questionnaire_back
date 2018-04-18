package com.infore.service;

import com.infore.model.Menu;
import java.util.List;

/**
 * Created by xuyao on 2017/9/14.
 */
public interface MenuService {

	public int add(Menu menu);

	public int update(Menu menu);

	public int del(Integer id);

	public List<Menu> querySources(String queryLike);

	public String getMenuTrees();

	public List<Menu> getMenusByPid(Integer pid);

	public List<Menu> querySourcesByRoleId(Integer roleId);

	public List<Menu> queryMenusByRoleId(Integer roleId,Integer type);

	public String getMenuTreeByRoleIds(List<Integer> roleIds);
}
