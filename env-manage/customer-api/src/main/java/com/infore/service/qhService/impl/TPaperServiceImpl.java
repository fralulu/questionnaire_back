/**
 * 
 */
package com.infore.service.qhService.impl;

import java.util.ArrayList;
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
import com.infore.mapper.TPaperMapper;
import com.infore.model.TOption;
import com.infore.model.TPaper;
import com.infore.model.TTest;
import com.infore.model.dto.TPaperDto;
import com.infore.model.dto.TPaperVo;
import com.infore.model.vo.TPaperCreateVo;
import com.infore.model.vo.TPaperUpdateVo;
import com.infore.service.impl.IotBaseServiceImpl;
import com.infore.service.qhService.TOptionService;
import com.infore.service.qhService.TPaperService;
import com.infore.service.qhService.TTestService;

/**
 * @desc   
 * @class  TPaperServiceImpl
 * @author  create author by deer
 * @date  2018年3月29日下午7:31:46
 */
@Service
public class TPaperServiceImpl extends IotBaseServiceImpl<TPaper> implements TPaperService<TPaper>{
	
	@Autowired
	private TTestService<TTest> tTestService;
	@Autowired
	private TOptionService<TOption> tOptionService;
	
	@Autowired
	private TPaperMapper tPaperMapper;
	/* (non-Javadoc)
	 * @see com.infore.service.impl.IotBaseServiceImpl#getMapper()
	 */
	@Override
	protected IotBaseMapper getMapper() {
		return tPaperMapper;
	}


	/* (non-Javadoc)
	 * @see com.infore.service.qhService.TPaperService#deleteByPrimaryKey(int)
	 */
	@Override
	public int deleteByPrimaryKey(int id) {
		return this.tPaperMapper.deleteByPrimaryKey(id);
	}

	/* (non-Javadoc)
	 * @see com.infore.service.qhService.TPaperService#selectByPrimaryKey(int)
	 */
	@Override
	public TPaper selectByPrimaryKey(int id) {
		return this.tPaperMapper.selectByPrimaryKey(id);
	}

	/* (non-Javadoc)
	 * @see com.infore.service.qhService.TPaperService#updateOne(int, java.lang.Object)
	 */
	@Override
	public int updateOne(int id, TPaperUpdateVo tPaper) {
		String log = null;
		TPaper newObject = this.tPaperMapper.selectByPrimaryKey(id);
		if(null == newObject) {
			log = this.bulidLogInfo("The tPaper=[",tPaper.toString(),"] info is not existence !");
			throw new ServerRuntimeException(log);
		}
		if(!newObject.getCreateUser().equals(tPaper.getUpdateUser())) {
			log = this.bulidLogInfo("The CreateUser not equals the UpdateUser!");
			throw new ServerRuntimeException(log);
		}
		BeanUtils.copyProperties(tPaper, newObject, "id","createUser","createTime");
		newObject.setUpdateTime(new Date());
		
		return this.updateByPrimaryKeySelective(newObject);
	}

	/* (non-Javadoc)
	 * @see com.infore.service.qhService.TPaperService#query(java.lang.String, java.lang.Integer, java.lang.Integer)
	 */
	@Override
	public PageInfo<TPaper> query(Integer createUser,String queryLike, Integer pageNum, Integer pageSize) {
		TPaperVo vo = new TPaperVo();
		vo.setCreateUser(createUser);
		vo.setQueryLike(StringUtils.trimToNull(queryLike));
		PageHelper.startPage(pageNum, pageSize);
		List<TPaper> list = tPaperMapper.selectListLike(vo); 
		PageInfo<TPaper> pageInfo = new PageInfo<>(list);
	    return pageInfo;
	}
	
	public TPaper addPaper(TPaperCreateVo vo) {
		TPaper paper = new TPaper();
		BeanUtils.copyProperties(vo, paper);
		paper.setCreateTime(new Date());//创建时间
		
		tPaperMapper.insertSelective(paper);
		return paper;
	}
	
	@Transactional
	public void deletePaperById(Integer createUser,Integer paperId) {
		//查询该试题是否存在
		String log = null;
		TPaper  paper = tPaperMapper.selectByPrimaryKey(paperId);
		if(paper == null) {
			log = this.bulidLogInfo("The tPaper=[",String.valueOf(paperId),"] info is not existence !");
			throw new ServerRuntimeException(log);
		}
		
		if(!paper.getCreateUser().equals(createUser)) {
			log = this.bulidLogInfo("you are not the paper's owner!");
			throw new ServerRuntimeException(log);
		}
		
		//删除试卷
		this.tPaperMapper.deleteByPrimaryKey(paperId);
		//查询该试卷下面的试题
		TTest test = new TTest();
		test.setPaperId(paperId);
		test.setCreateUser(createUser);
		List<TTest> testList = tTestService.selectListByEntity(test);
		if(testList != null && testList.size() >= 1 ) {
			//批量删除试题
			this.tPaperMapper.batchDeleteTestByPaperId(paperId);
			
			//删除试题选项
			tOptionService.batchDeleteByTestIds(testList);
		}
		
	}
	
	
	public TPaperDto queryPaperInfo(Integer createUser,Integer paperId){
		//查询试题信息
		TPaper paper = this.tPaperMapper.selectByPrimaryKey(paperId);
		String log = null;
		if(paper == null || !paper.getCreateUser().equals(createUser)) {
			log = this.bulidLogInfo("The tPaper=[",String.valueOf(paperId),"] info is not existence !");
			throw new ServerRuntimeException(log);
		}
		
		//查询试题信息
		TTest test = new TTest();
		test.setPaperId(paperId);
		List<TTest> testList = this.tTestService.selectListByEntity(test);
		TOption option = null;
		
		TPaperDto paperDto = new TPaperDto();//试卷信息
		List<TPaperDto.TTestDto> testDtoList = new ArrayList<>();//试题信息
		TPaperDto.TTestDto testDto = null;
		if(testList != null && testList.size() > 0) {
			
			for(int i=0;i<testList.size();i++) {
				TTest tempTest = testList.get(i);
				//查询选项信息
				option = new TOption();
				option.setTestId(tempTest.getId());
				List<TOption> optionList = this.tOptionService.selectListByEntity(option);
				 testDto =  paperDto.new TTestDto();
				if(optionList!=null && optionList.size() > 0) {
					testDto.setOptionList(optionList);//复制options信息
				}
				BeanUtils.copyProperties(tempTest,testDto);//复制test信息
				
				testDtoList.add(testDto);
			}
			
		}
		BeanUtils.copyProperties(paper,paperDto);//复制paper信息
		paperDto.setTestList(testDtoList);//试题信息
		
		return paperDto;
	}


}
