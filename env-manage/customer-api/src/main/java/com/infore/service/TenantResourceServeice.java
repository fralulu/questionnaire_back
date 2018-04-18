package com.infore.service;

import com.infore.model.TenantResource;

/**
 * Created by xuyao on 2017/9/15.
 */
public interface TenantResourceServeice {

  int add(TenantResource tenantResource);

  int update(TenantResource tenantResource);

  int del(Integer id);

}
