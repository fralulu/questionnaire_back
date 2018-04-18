package com.infore.service.impl;

import com.infore.mapper.RoleResourceMapper;
import com.infore.model.RoleResource;
import com.infore.service.RoleResourceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by xuyao on 2017/9/15.
 */
@Service
public class RoleResourceServiceImpl implements RoleResourceService{

  private static final Logger log = LoggerFactory.getLogger(RoleResourceServiceImpl.class);

  @Autowired
  private RoleResourceMapper roleResourceMapper;

  @Override
  public int add(RoleResource roleResource) {
    return roleResourceMapper.insertSelective(roleResource);
  }

  @Override
  public int update(RoleResource roleResource) {
    return roleResourceMapper.updateByPrimaryKeySelective(roleResource);
  }

  @Override
  public int del(Integer id) {
    return roleResourceMapper.deleteByPrimaryKey(id);
  }
}
