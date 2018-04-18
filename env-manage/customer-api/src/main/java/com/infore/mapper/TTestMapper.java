package com.infore.mapper;

import java.util.List;

import com.infore.model.TTest;
import com.infore.model.dto.TTestVo;

public interface TTestMapper extends IotBaseMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TTest record);

    int insertSelective(TTest record);

    TTest selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TTest record);

    int updateByPrimaryKey(TTest record);
    
    List<TTest> selectListLike(TTestVo record);
    
    void batchDeleteOptionsByTestId(Integer testId);
}