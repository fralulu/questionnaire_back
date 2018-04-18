package com.infore.mapper;

import java.util.List;

import com.infore.model.TPaper;
import com.infore.model.TTest;
import com.infore.model.dto.TPaperVo;

public interface TPaperMapper extends IotBaseMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TPaper record);

    int insertSelective(TPaper record);

    TPaper selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TPaper record);

    int updateByPrimaryKey(TPaper record);
    
    
    List<TPaper> selectListLike(TPaperVo vo);
    
    void batchDeleteTestByPaperId(Integer paperId);
    
}