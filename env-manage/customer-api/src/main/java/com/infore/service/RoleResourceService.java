package com.infore.service;

import com.infore.model.RoleResource;

/**
 * 角色对应的资源 (如：设备、站点ID等)
 * Created by xuyao on 2017/9/15.
 */
public interface RoleResourceService {

	public int add(RoleResource roleResource);

	public int update(RoleResource roleResource);

	public int del(Integer id);

}
