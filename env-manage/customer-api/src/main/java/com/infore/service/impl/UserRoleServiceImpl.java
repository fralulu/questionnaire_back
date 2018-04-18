package com.infore.service.impl;

import com.infore.mapper.UserRoleMapper;
import com.infore.model.UserRole;
import com.infore.service.UserRoleService;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by xuyao on 2017/9/14.
 */
@Service
public class UserRoleServiceImpl implements UserRoleService{

  private static final Logger log = LoggerFactory.getLogger(UserRoleServiceImpl.class);

  @Autowired
  private UserRoleMapper userRoleMapper;

  @Override
  public int add(UserRole userRole) {
    return userRoleMapper.insertSelective(userRole);
  }

  @Override
  public int update(UserRole userRole) {
    return userRoleMapper.updateByPrimaryKeySelective(userRole);
  }

  @Override
  public int del(Integer roleId, Integer userId) {
    return userRoleMapper.deleteByPrimaryKey(roleId, userId);
  }

    @Override
    public List<UserRole> queryAll() {
        return userRoleMapper.queryByParam(null);
    }

    @Override
    public boolean isExistUserId(Integer userId) {
        return userRoleMapper.countByUserId(userId) == 0 ? false : true;
    }

    @Override
    public int delByUserId(Integer userId) {
        return userRoleMapper.deleteByUserId(userId);
    }

    @Override
    public int batchInsert(Integer userId, List<Integer> roleIds) {
        List<UserRole> userRoles = new LinkedList<>();
        for (Integer roleId: roleIds
        ) {
            UserRole userRole = new UserRole();
            userRole.setUserId(userId);
            userRole.setRoleId(roleId);
            userRoles.add(userRole);
        }
        return userRoleMapper.batchInsert(userRoles);
    }

    @Override
    @Transactional
    public boolean delAndInsert(Integer userId, List<Integer> roleIds) {
        int del = delByUserId(userId);
        int ins = batchInsert(userId, roleIds);
        boolean flag = (del != 0) && (ins != 0);
        return flag;
    }

    @Override
    public List<Integer> queryRoleIdsByUserId(Integer userId) {
        List<Integer> roleIds = new LinkedList<>();
        List<UserRole> userRoles = userRoleMapper.queryByParam(new UserRole() {{
            setUserId(userId);
        }});
        if (CollectionUtils.isNotEmpty(userRoles)) {
            roleIds=userRoles.stream().map(UserRole::getRoleId).collect(Collectors.toList());
        }
        return roleIds;
    }

}
