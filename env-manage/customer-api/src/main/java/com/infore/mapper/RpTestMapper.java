package com.infore.mapper;

import com.infore.model.RpTest;

public interface RpTestMapper extends IotBaseMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(RpTest record);

    int insertSelective(RpTest record);

    RpTest selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(RpTest record);

    int updateByPrimaryKey(RpTest record);
}