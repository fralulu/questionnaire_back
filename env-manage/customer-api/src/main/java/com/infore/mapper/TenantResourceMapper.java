package com.infore.mapper;

import com.infore.model.TenantResource;

public interface TenantResourceMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TenantResource record);

    int insertSelective(TenantResource record);

    TenantResource selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TenantResource record);

    int updateByPrimaryKey(TenantResource record);
}