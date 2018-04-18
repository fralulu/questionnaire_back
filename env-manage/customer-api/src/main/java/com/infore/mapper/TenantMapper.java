package com.infore.mapper;

import com.infore.model.Tenant;

public interface TenantMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Tenant record);

    int insertSelective(Tenant record);

    Tenant selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Tenant record);

    int updateByPrimaryKey(Tenant record);
}