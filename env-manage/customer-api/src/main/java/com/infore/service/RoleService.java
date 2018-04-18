package com.infore.service;

import com.infore.model.Roles;
import java.util.List;

/**
 * Created by xuyao on 2017/9/14.
 */
public interface RoleService {

	public int add(Roles roles);

	public int update(Roles roles);

	public int del(Integer roleId);

	public List<Roles> queryList();

	public List<Roles> queryByRoleId(Integer userId);
}
