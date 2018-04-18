package com.infore.mapper;

import java.util.List;

import com.infore.model.TOption;
import com.infore.model.TTest;
import com.infore.model.dto.TOptionVo;

public interface TOptionMapper extends IotBaseMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TOption record);

    int insertSelective(TOption record);

    TOption selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TOption record);

    int updateByPrimaryKey(TOption record);
    
    List<TOption> selectListLike(TOptionVo record);
    
    void batchDeleteOptionsByTestIds(List<TTest> testList);
}