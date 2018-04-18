package com.infore.mapper;

import com.infore.model.TUserOperation;

public interface TUserOperationMapper extends IotBaseMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TUserOperation record);

    int insertSelective(TUserOperation record);

    TUserOperation selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TUserOperation record);

    int updateByPrimaryKey(TUserOperation record);
}