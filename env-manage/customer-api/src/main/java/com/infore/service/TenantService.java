package com.infore.service;

import com.infore.model.Tenant;

/**
 * Created by xuyao on 2017/9/14.
 */
public interface TenantService {

  int add(Tenant tenant);

  int update(Tenant tenant);

  int del(Integer id);

   Tenant getTenantById(Integer id);

}
