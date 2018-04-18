package com.infore.mapper;

import com.infore.model.TAnswer;

public interface TAnswerMapper extends IotBaseMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TAnswer record);

    int insertSelective(TAnswer record);

    TAnswer selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TAnswer record);

    int updateByPrimaryKey(TAnswer record);
}