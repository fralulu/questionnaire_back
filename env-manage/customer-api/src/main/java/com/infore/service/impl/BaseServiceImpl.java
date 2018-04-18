package com.infore.service.impl;

import com.infore.mapper.BaseMapper;
import com.infore.service.BaseService;

import java.util.List;
import java.util.Map;

/**
 * @desc 基本Service实现类
 * @author Created by Cai.xu
 * @date 2017/6/28-上午10:10
 */
public abstract class BaseServiceImpl<T> implements BaseService<T> {

    /**
     * 获取mapper
     * @return
     */
    protected abstract BaseMapper getMapper();

	@Override
	public int deleteByPrimaryKey(Object id) {
		return getMapper().deleteByPrimaryKey(id);
	}

	@Override
	public int insert(T record) {
		return getMapper().insert(record);
	}

	@Override
	public int insertSelective(T record) {
		return getMapper().insertSelective(record);
	}

	@Override
	public  T selectByPrimaryKey(Object id) {
		return getMapper().selectByPrimaryKey(id);
	}

	@Override
	public  int updateByPrimaryKeySelective(T record) {
		return getMapper().updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(T record) {
		return getMapper().updateByPrimaryKey(record);
	}

	@Override
	public  List<T> selectListByPagination(Map<String, Object> parasMap) {
        return getMapper().selectListByPagination(parasMap);
	}
	
	@Override
	public List<T> selectListByEntityPagination(T record){
		return getMapper().selectListByEntityPagination(record);
	}

	@Override
	public int queryCount(Map<String, Object> parasMap) {
		return getMapper().queryCount(parasMap);
	}




}
