package com.infore.service.impl;

import com.infore.mapper.RolesMapper;
import com.infore.model.Roles;
import com.infore.service.RoleService;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by xuyao on 2017/9/14.
 */
@Service
public class RoleServiceImpl implements RoleService{

  private static final Logger log = LoggerFactory.getLogger(RoleServiceImpl.class);

  @Autowired
  private RolesMapper rolesMapper;

  @Override
  public int add(Roles roles) {
    return rolesMapper.insertSelective(roles);
  }

  @Override
  public int update(Roles roles) {
    return rolesMapper.updateByPrimaryKeySelective(roles);
  }

  @Override
  public int del(Integer roleId) {
    return rolesMapper.deleteByPrimaryKey(roleId);
  }

  @Override
  public List<Roles> queryList() {
    return rolesMapper.queryByParam(null);
  }

    @Override
    public List<Roles> queryByRoleId(Integer userId) {
        return rolesMapper.queryByUserId(userId);
    }
}
