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

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.infore.common.exception.ServerRuntimeException;
import com.infore.mapper.IotBaseMapper;
import com.infore.mapper.TOptionMapper;
import com.infore.model.TOption;
import com.infore.model.TTest;
import com.infore.model.dto.TOptionVo;
import com.infore.model.vo.TOptionCreateVo;
import com.infore.model.vo.TOptionUpdateVo;
import com.infore.service.impl.IotBaseServiceImpl;
import com.infore.service.qhService.TOptionService;
import com.infore.service.qhService.TTestService;

/**
 * @desc   
 * @class  TOptionServiceImpl
 * @author  create author by deer
 * @date  2018年4月4日下午4:14:41
 */
@Service
public class TOptionServiceImpl extends IotBaseServiceImpl<TOption> implements TOptionService<TOption>{

	@Autowired
	private TTestService<TTest> tTestService;
	
	@Autowired
	private TOptionMapper tOptionMapper;
	
	/* (non-Javadoc)
	 * @see com.infore.service.impl.IotBaseServiceImpl#getMapper()
	 */
	@Override
	protected IotBaseMapper getMapper() {
		return tOptionMapper;
	}   
	
	/* (non-Javadoc)
	 * @see com.infore.service.qhService.TOptionService#deleteByPrimaryKey(int)
	 */
	@Override
	public int deleteByPrimaryKey(int id) {
		return this.tOptionMapper.deleteByPrimaryKey(id);
	}

	/* (non-Javadoc)
	 * @see com.infore.service.qhService.TOptionService#selectByPrimaryKey(int)
	 */
	@Override
	public TOption selectByPrimaryKey(int id) {
		return this.tOptionMapper.selectByPrimaryKey(id);
	}

	/* (non-Javadoc)
	 * @see com.infore.service.qhService.TOptionService#updateOne(int, java.lang.Object)
	 */
	@Override
	public int updateOne(int id, TOptionUpdateVo tOption) {
		String log = null;
		TOption newObject = this.tOptionMapper.selectByPrimaryKey(id);
		if(null == newObject) {
			log = this.bulidLogInfo("The tOption=[",tOption.toString(),"] info is not existence !");
			throw new ServerRuntimeException(log);
		}
		
		if(!newObject.getCreateUser().equals(tOption.getUpdateUser())) {
			log = this.bulidLogInfo("The UpdateUser not equals the Test's CreateUser!");
			throw new ServerRuntimeException(log);
		}
		
		BeanUtils.copyProperties(tOption, newObject, "id","createUser","createTime","testId");
		return this.updateByPrimaryKeySelective(newObject);
	}

	/* (non-Javadoc)
	 * @see com.infore.service.qhService.TOptionService#query(java.lang.String, java.lang.Integer, java.lang.Integer)
	 */
	@Override
	public PageInfo<TOption> query(String queryLike, Integer pageNum, Integer pageSize) {
		TOptionVo vo = new TOptionVo();
		vo.setQueryLike(StringUtils.trimToNull(queryLike));
		PageHelper.startPage(pageNum, pageSize);
		List<TOption> list = tOptionMapper.selectListLike(vo);
		PageInfo<TOption> pageInfo = new PageInfo<>(list);
	    return pageInfo;
	}
	
	public TOption addOPtions(TOptionCreateVo vo) {
		//判断试题是否存在
		TTest test = tTestService.selectByPrimaryKey(vo.getTestId());
		String log = null;
		if(test == null) {
			log = this.bulidLogInfo("The tTest=[",String.valueOf(vo.getTestId()),"] info is not existence !");
			throw new ServerRuntimeException(log);
		}
		if(!test.getCreateUser().equals(vo.getCreateUser())) {
			log = this.bulidLogInfo("The CreateUser not equals the Test's CreateUser!");
			throw new ServerRuntimeException(log);
		}
		
		TOption option = new TOption();
		BeanUtils.copyProperties(vo, option);
		option.setCreateTime(new Date());
		tOptionMapper.insertSelective(option);
		return option;
	}
	
	public void batchDeleteByTestIds(List<TTest> testList) {
		this.tOptionMapper.batchDeleteOptionsByTestIds(testList);
	}
	
	


}
