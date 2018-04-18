package com.infore.mapper;

import com.infore.model.TAccountTrans;

public interface TAccountTransMapper extends IotBaseMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TAccountTrans record);

    int insertSelective(TAccountTrans record);

    TAccountTrans selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TAccountTrans record);

    int updateByPrimaryKey(TAccountTrans record);
}