/**
 * 
 */
package com.infore.mapper;

import java.util.List;
import java.util.Map;

/**
 * @desc   
 * @class  IotBaseMapper
 * @author  create author by deer
 * @date  2017年11月2日下午5:21:58
 */
public interface IotBaseMapper {
	
	
	
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
     * 将实体类作为查询条件的参数进行查询，减少接收参数的步骤
     * @param record
     * @return
     * @author XiaChengwei
     */
    <T> List<T> selectListByEntity(T record);

    /**
     * 查询条数
     * @param parasMap
     * @param <T>
     * @return
     */
    <T> int queryCount(Map<String, Object> parasMap);

}
