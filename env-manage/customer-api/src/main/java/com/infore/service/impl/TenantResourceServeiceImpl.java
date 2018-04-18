package com.infore.service.impl;

import com.infore.mapper.TenantResourceMapper;
import com.infore.model.TenantResource;
import com.infore.service.TenantResourceServeice;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by xuyao on 2017/9/15.
 */
@Service
public class TenantResourceServeiceImpl implements TenantResourceServeice{

  private static final Logger log = LoggerFactory.getLogger(TenantResourceServeiceImpl.class);

  @Autowired
  private TenantResourceMapper tenantResourceMapper;

  @Override
  public int add(TenantResource tenantResource) {
    return tenantResourceMapper.insertSelective(tenantResource);
  }

  @Override
  public int update(TenantResource tenantResource) {
    return tenantResourceMapper.updateByPrimaryKeySelective(tenantResource);
  }

  @Override
  public int del(Integer id) {
    return tenantResourceMapper.deleteByPrimaryKey(id);
  }
}
