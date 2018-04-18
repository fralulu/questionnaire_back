package com.infore.service.impl;

import com.infore.mapper.TenantMapper;
import com.infore.model.Tenant;
import com.infore.service.TenantService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by xuyao on 2017/9/14.
 */
@Service
public class TenantServiceImpl implements TenantService {
  private static final Logger log = LoggerFactory.getLogger(TenantServiceImpl.class);

  @Autowired
  private TenantMapper tenantMapper;

  @Override
  public int add(Tenant tenant) {
    return tenantMapper.insertSelective(tenant);
  }

  @Override
  public int update(Tenant tenant) {
    return tenantMapper.updateByPrimaryKeySelective(tenant);
  }

  @Override
  public int del(Integer id) {
    return tenantMapper.deleteByPrimaryKey(id);
  }

    @Override
    public Tenant getTenantById(Integer id) {
        return tenantMapper.selectByPrimaryKey(id);
    }
}
