/**
 * 
 */
package com.infore.service.qhService.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.infore.common.exception.ServerRuntimeException;
import com.infore.mapper.IotBaseMapper;
import com.infore.mapper.TTestMapper;
import com.infore.model.TPaper;
import com.infore.model.TTest;
import com.infore.model.dto.TTestVo;
import com.infore.model.vo.TTestCreateVo;
import com.infore.model.vo.TTestUpdateVo;
import com.infore.service.impl.IotBaseServiceImpl;
import com.infore.service.qhService.TPaperService;
import com.infore.service.qhService.TTestService;

/**
 * @desc   
 * @class  TTestServiceImpl
 * @author  create author by deer
 * @date  2018年4月4日下午3:55:03
 */
@Service
public class TTestServiceImpl extends IotBaseServiceImpl<TTest> implements TTestService<TTest>{

	@Autowired
	private TTestMapper tTestMapper;
	
	@Autowired
	private TPaperService<TPaper> tPaperService;
	
	
	
	/* (non-Javadoc)
	 * @see com.infore.service.impl.IotBaseServiceImpl#getMapper()
	 */
	@Override
	protected IotBaseMapper getMapper() {
		return tTestMapper;
	}
	
	
	/* (non-Javadoc)
	 * @see com.infore.service.qhService.TTestService#deleteByPrimaryKey(int)
	 */
	@Override
	public int deleteByPrimaryKey(int id) {
		return this.tTestMapper.deleteByPrimaryKey(id);
	}

	/* (non-Javadoc)
	 * @see com.infore.service.qhService.TTestService#selectByPrimaryKey(int)
	 */
	@Override
	public TTest selectByPrimaryKey(int id) {
		return this.tTestMapper.selectByPrimaryKey(id);
	}

	/* (non-Javadoc)
	 * @see com.infore.service.qhService.TTestService#updateOne(int, java.lang.Object)
	 */
	@Override
	public int updateOne(int id, TTestUpdateVo tTest) {
		String log = null;
		TTest newObject = this.tTestMapper.selectByPrimaryKey(id);
		if(null == newObject) {
			log = this.bulidLogInfo("The tTest=[",tTest.toString(),"] info is not existence !");
			throw new ServerRuntimeException(log);
		}
		
		if(!newObject.getCreateUser().equals(tTest.getUpdateUser())) {
			log = this.bulidLogInfo("The CreateUser not equals the UpdateUser!");
			throw new ServerRuntimeException(log);
		}
		
		BeanUtils.copyProperties(tTest, newObject, "id","paperId","createUser","createTime");
		newObject.setUpdateTime(new Date());
		return this.updateByPrimaryKeySelective(newObject);
	}

	/* (non-Javadoc)
	 * @see com.infore.service.qhService.TTestService#query(java.lang.String, java.lang.Integer, java.lang.Integer)
	 */
	@Override
	public PageInfo<TTest> query(Integer createUser,Integer paperId,String queryLike, Integer pageNum, Integer pageSize) {
		TTestVo vo = new TTestVo();
		vo.setCreateUser(createUser);
		vo.setPaperId(paperId);
		vo.setQueryLike(StringUtils.trimToNull(queryLike));
		PageHelper.startPage(pageNum, pageSize);
		List<TTest> list = tTestMapper.selectListLike(vo); 
		PageInfo<TTest> pageInfo = new PageInfo<>(list);
	    return pageInfo;
	}
	
	
	public TTest addTest(TTestCreateVo vo) {
		TTest test = new TTest();
		BeanUtils.copyProperties(vo, test);
		//试题是否存在
		TPaper paper = tPaperService.selectByPrimaryKey(vo.getPaperId());
		String log = null;
		if(paper == null) {
			log = this.bulidLogInfo("The paper=[",String.valueOf(vo.getPaperId()),"] info is not existence !");
			throw new ServerRuntimeException(log);
		}
		
		if(!paper.getCreateUser().equals(vo.getCreateUser())) {
			log = this.bulidLogInfo("The paper=[",String.valueOf(vo.getPaperId()),"] is not ownered=[",
					String.valueOf(vo.getCreateUser()),"]");
			throw new ServerRuntimeException(log);
		}
		
		test.setCreateTime(new Date());
		
		tTestMapper.insertSelective(test);
		
		return test;
		
	}
	
	@Transactional
	public void deleteByTestIds(Integer createUser,Integer testId) {
		 //查询test
		TTest test = this.tTestMapper.selectByPrimaryKey(testId);
		String log = null;
		if(test == null) {
			log = this.bulidLogInfo("The test=[",String.valueOf(testId),"] info is not existence !");
			throw new ServerRuntimeException(log);
		}
		
		if(test.getCreateUser().equals(createUser)) {
			log = this.bulidLogInfo("you are not the test's owner!");
			throw new ServerRuntimeException(log);
		}
		
		//删除试题
		this.tTestMapper.deleteByPrimaryKey(testId);
		
		//删除试题选项
		this.tTestMapper.batchDeleteOptionsByTestId(testId);
		
	}

}
