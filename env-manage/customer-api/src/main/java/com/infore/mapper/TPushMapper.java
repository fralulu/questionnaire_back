package com.infore.mapper;

import com.infore.model.TPush;

public interface TPushMapper extends IotBaseMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TPush record);

    int insertSelective(TPush record);

    TPush selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TPush record);

    int updateByPrimaryKey(TPush record);
}