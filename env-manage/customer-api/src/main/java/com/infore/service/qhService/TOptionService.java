/**
 * 
 */
package com.infore.service.qhService;

import java.util.List;

import com.github.pagehelper.PageInfo;
import com.infore.model.TOption;
import com.infore.model.TTest;
import com.infore.model.vo.TOptionCreateVo;
import com.infore.model.vo.TOptionUpdateVo;
import com.infore.service.IotBaseService;

/**
 * @desc   
 * @class  TOptionService
 * @author  create author by deer
 * @date  2018年4月4日下午4:11:57
 */
public interface TOptionService<TOption> extends IotBaseService<TOption>{
	  public int deleteByPrimaryKey(int id);
	  public TOption selectByPrimaryKey(int id);
	  public int updateOne(int id,TOptionUpdateVo tOption);
	  public PageInfo<TOption> query(String queryLike,Integer pageNum,Integer pageSize);
	  
	  public TOption addOPtions(TOptionCreateVo vo);
	  
	  public void batchDeleteByTestIds(List<TTest> testList);
}
