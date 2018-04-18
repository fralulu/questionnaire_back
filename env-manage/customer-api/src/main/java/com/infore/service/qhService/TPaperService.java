/**
 * 
 */
package com.infore.service.qhService;

import java.util.List;

import com.github.pagehelper.PageInfo;
import com.infore.model.dto.TPaperDto;
import com.infore.model.vo.TPaperCreateVo;
import com.infore.model.vo.TPaperUpdateVo;
import com.infore.service.IotBaseService;

/**
 * @desc   
 * @class  TPaperService
 * @author  create author by deer
 * @date  2018年3月29日下午7:25:37
 */
public interface TPaperService<TPaper> extends IotBaseService<TPaper>{
	  public int deleteByPrimaryKey(int id);
	  public TPaper selectByPrimaryKey(int id);
	  public int updateOne(int id,TPaperUpdateVo tPaper);
	  public PageInfo<TPaper> query(Integer createUser,String queryLike,Integer pageNum,Integer pageSize);
	  
	  public TPaper addPaper(TPaperCreateVo vo);
	  
	  public void deletePaperById(Integer createUser,Integer paperId);
	  
	  public TPaperDto queryPaperInfo(Integer createUser,Integer paperId);
}
