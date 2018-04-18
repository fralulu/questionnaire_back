package com.infore.service;

/**
 * 
 */

import java.util.List;
import java.util.Map;

import com.github.pagehelper.PageInfo;


/**
 * @desc   
 * @class  IotBaseService
 * @author  create author by deer
 * @date  2017年11月2日下午4:23:21
 */
public interface IotBaseService <T>{
	
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
     * 将实体类作为查询条件的参数进行查询，减少接收参数的步骤
     * @param record
     * @return
     * @author XiaChengwei
     */
    public List<T> selectListByEntity(T record);
    

    /**
     * 查询条数
     * @param parasMap
     * @return
     */
    public int queryCount(Map<String, Object> parasMap);
    
    /**
     * 
     * @Title: queryList   
     * @Description: TODO(这里用一句话描述这个方法的作用)   
     * @param: @return      
     * @return: List<T>      
     * @throws
     */
    public  List<T> queryList();
    
    /**
     * 
     * @Title: selectListByPagination 
     * @Description: TODO(这里用一句话描述这个方法的作用)   
     * @param: @param pageNum
     * @param: @param pageSize
     * @param: @param orderBy    
     * @return: PageInfo<T>      
     * @throws
     */
    public  PageInfo<T> selectListByPagination(Integer pageNum,Integer pageSize,String orderBy, T entity);
    
    
    
}
