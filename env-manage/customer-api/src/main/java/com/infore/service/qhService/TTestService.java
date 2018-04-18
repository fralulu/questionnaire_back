/**
 * 
 */
package com.infore.service.qhService;

import com.github.pagehelper.PageInfo;
import com.infore.model.TTest;
import com.infore.model.vo.TTestCreateVo;
import com.infore.model.vo.TTestUpdateVo;
import com.infore.service.IotBaseService;

/**
 * @desc   
 * @class  TTestService
 * @author  create author by deer
 * @date  2018年4月4日下午3:53:37
 */
public interface TTestService<TTest> extends IotBaseService<TTest>{
	  public int deleteByPrimaryKey(int id);
	  public TTest selectByPrimaryKey(int id);
	  public int updateOne(int id,TTestUpdateVo tTest);
	  public PageInfo<TTest> query(Integer createUser,Integer paperId,String queryLike,Integer pageNum,Integer pageSize);
	  
	  public TTest addTest(TTestCreateVo vo);
	  
	  public void deleteByTestIds(Integer createUser,Integer testId);
}
