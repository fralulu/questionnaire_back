package com.infore.service;

import com.infore.model.MenuRole;
import java.util.List;

/**
 * Created by xuyao on 2017/9/14.
 */
public interface MenuRoleService {

	public int add(MenuRole menuRole);

	public int update(MenuRole menuRole);

	public int del(Integer id);

    /**
     * 检查用户访问的url 是否具有相应权限
     *
     * @return true:具备访问URL权限
     */
	public boolean checkUserMenu(int user_id, String url);

	public List<MenuRole> queryByRoleId(Integer roleId);

	public int delByRoleId(Integer roleId);
    
	public boolean isExistMenusByRoleId(Integer roleId);

	public int batchInsert(Integer roleId, List<Integer> menuIds);

	public boolean delAndInsert(Integer roleId, List<Integer> menuIds);

    /**
     *
     * @param menuId
     * @return true:存在数据
     */
	public boolean isExistMenusByMenuId(Integer menuId);

	public int batchDelete(Integer roleId, List<Integer> menuIds);
}
