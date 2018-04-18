package com.infore.service;

import com.infore.model.UserRole;
import java.util.List;

/**
 * Created by xuyao on 2017/9/14.
 */
public interface UserRoleService {

	public int add(UserRole userRole);

	public int update(UserRole userRole);

	public int del(Integer roleId, Integer userId);

	public  List<UserRole> queryAll();


    boolean isExistUserId(Integer userId);

    public  int delByUserId(Integer userId);

    public int batchInsert(Integer userId, List<Integer> roleIds);

    public boolean delAndInsert(Integer userId, List<Integer> roleIds);

    public List<Integer> queryRoleIdsByUserId(Integer userId);

}
