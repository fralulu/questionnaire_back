package com.infore.mapper;

import java.util.List;
import java.util.Map;


/**
 * @description 基础Mapper 提供公共方法 必须在Mapper.xml文件中提供实现的SQL
 * @auth Created by Cai.xu
 * @date 2017-04-12 17:23
 */
public interface BaseMapper {

	/**
	 * 根据ID删除记录
	 * @param id
	 * @return
	 */
    int deleteByPrimaryKey(Object id);

    /**
     * 插入记录
     * @param record
     * @return
     */
    <T> int insert(T record);

    /**
     * 选择插入记录
     * @param record
     * @return
     */
    <T> int insertSelective(T record);

    /**
     * 根据主键查询记录
     * @param id
     * @return
     */
    <T> T selectByPrimaryKey(Object id);
    
    /**
     * 选择性修改记录
     * @param record
     * @return
     */
    <T>  int updateByPrimaryKeySelective(T record);
    
    /**
     * 修改记录
     * @param record
     * @return
     */
    <T>  int updateByPrimaryKey(T record);
	
	/**
	 * 分页查询
	 * @param parasMap
	 * @return
     */
    <T> List<T> selectListByPagination(Map<String, Object> parasMap);
    
    /**
     * 将实体类作为查询条件的参数进行分页查询，减少接收参数的步骤
     * @param record
     * @return
     * @author XiaChengwei
     */
    <T> List<T> selectListByEntityPagination(T record);

    /**
     * 查询条数
     * @param parasMap
     * @param <T>
     * @return
     */
    <T> int queryCount(Map<String, Object> parasMap);

}
