package com.infore.mapper;

import com.infore.model.RpPaper;

public interface RpPaperMapper extends IotBaseMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(RpPaper record);

    int insertSelective(RpPaper record);

    RpPaper selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(RpPaper record);

    int updateByPrimaryKey(RpPaper record);
}