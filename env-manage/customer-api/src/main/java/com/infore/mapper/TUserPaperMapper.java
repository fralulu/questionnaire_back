package com.infore.mapper;

import com.infore.model.TUserPaper;

public interface TUserPaperMapper extends IotBaseMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TUserPaper record);

    int insertSelective(TUserPaper record);

    TUserPaper selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TUserPaper record);

    int updateByPrimaryKey(TUserPaper record);
}