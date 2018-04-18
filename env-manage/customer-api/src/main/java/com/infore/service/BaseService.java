package com.infore.service;

import java.util.List;
import java.util.Map;

/**
 * @desc 基本Service层
 * @author Created by Cai.xu
 * @date 2017/6/28-上午10:10
 */
public interface BaseService<T> {

    /**
     * 根据ID删除记录
     *
     * @param id
     * @return
     */
	public int deleteByPrimaryKey(Object id);

    /**
     * 插入记录
     * @param record
     * @return
     */
	public int insert(T record);

    /**
     * 选择插入记录
     * @param record
     * @return
     */
	public int insertSelective(T record);

    /**
     * 根据主键查询记录
     * @param id
     * @return
     */
	public T selectByPrimaryKey(Object id);

    /**
     * 选择性修改记录
     * @param record
     * @return
     */
	public int updateByPrimaryKeySelective(T record);

    /**
     * 修改记录
     * @param record
     * @return
     */
	public int updateByPrimaryKey(T record);

    /**
     * 分页查询
     * @param parasMap
     * @return
     */
	public List<T> selectListByPagination(Map<String, Object> parasMap);
    
    /**
     * 将实体类作为查询条件的参数进行分页查询，减少接收参数的步骤
     * @param record
     * @return
     * @author XiaChengwei
     */
	public List<T> selectListByEntityPagination(T record);
    

    /**
     * 查询条数
     * @param parasMap
     * @return
     */
	public int queryCount(Map<String, Object> parasMap);
}
