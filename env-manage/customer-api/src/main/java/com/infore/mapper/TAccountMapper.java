package com.infore.mapper;

import com.infore.model.TAccount;

public interface TAccountMapper extends IotBaseMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TAccount record);

    int insertSelective(TAccount record);

    TAccount selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TAccount record);

    int updateByPrimaryKey(TAccount record);
}