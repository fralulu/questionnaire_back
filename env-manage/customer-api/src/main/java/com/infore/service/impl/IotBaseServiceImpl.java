/**
 * 
 */
package com.infore.service.impl;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.infore.common.exception.ServerRuntimeException;
import com.infore.common.util.BytesUtils;
import com.infore.mapper.IotBaseMapper;
import com.infore.service.IotBaseService;

/**
 * @desc   
 * @class  IotBaseServiceImpl
 * @author  create author by deer
 * @param <T>
 * @date  2017年11月2日下午4:23:45
 */
public  abstract class IotBaseServiceImpl<T> implements  IotBaseService<T>{ 
	
	protected final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Value("${user.max.page.size:50}")
    private Integer maxPageSize;

	/**
     * 获取mapper
     * @return
     */
    protected abstract  IotBaseMapper getMapper();
    
    @Override
	public int insert(T record) {
		return getMapper().insert(record);
	}

	@Override
	public int insertSelective(T record) {
		return getMapper().insertSelective(record);
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
	public List<T> selectListByEntity(T record){
		return getMapper().selectListByEntity(record);
	}

	@Override
	public int queryCount(Map<String, Object> parasMap) {
		return getMapper().queryCount(parasMap);
	}
	
   
	/**
	 * 
	 * @Title: quryList   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param: @return      
	 * @return: List<IotBasSiteKind>      
	 * @throws
	 */
     @Override
     public List<T> queryList(){
	    // return getMapper().selectListByPagination(null);
	     return getMapper().selectListByEntity(null);
     }
   
     
    /**
 	 * 
 	 * @Title: selectListByPagination  
 	 * @Description: 单表分页实现 ，包含总条数
 	 * @param: pageNum  页码
 	 * @param: pageSize 每页显示数量
 	 *    
 	 * @return:PageInfo<T>      
 	 * @throws
 	 */

 	@SuppressWarnings({ "rawtypes", "unchecked" })
 	@Override
 	public  PageInfo<T> selectListByPagination(Integer pageNum,Integer pageSize,String orderBy, T entity) {
 		
 		 try {
 	            if (orderBy != null && !"".equals(orderBy)) {
 	                PageHelper.startPage(pageNum, pageSize, orderBy);
 	            } else {
 	                PageHelper.startPage(pageNum, pageSize);
 	            }
 	            List<T> list = getMapper().selectListByEntity(entity);
 	            if (list == null || list.isEmpty()) {
 	                return null;
 	            }

 	          return new PageInfo(list);
 	          
 	     } catch (Exception e) {
 	        logger.error("query sql error:",e);
 	        throw new ServerRuntimeException("query sql error:"+e.getMessage());
 	     }
 	}
 	
 	/**
 	 * 
 	 * @Title: bulidLogInfo   
 	 * @Description: TODO(这里用一句话描述这个方法的作用)   
 	 * @param: @param strings
 	 * @param: @return      
 	 * @return: String      
 	 * @throws
 	 */
 	public String bulidLogInfo(String...strings) {
 		StringBuilder logBuffer = new StringBuilder();
 	    for(String s:strings) {
 	    	logBuffer.append(s);
 	    }
 		return logBuffer.toString();
 	}
 	
 	
    /**
     * 获取基础监控量表里面的bas_mete_id
     * @Title: getBasMeteId   
     * @Description: TODO(这里用一句话描述这个方法的作用)   
     * @param: @param meteKind
     * @param: @param deviceType
     * @param: @param meteType
     * @param: @param sameMeteTypeIndex
     * @param: @return      
     * @return: String      
     * @throws
     */
	public  String getBasMeteId(Byte meteKind,Short deviceType,Short meteType,Short sameMeteTypeIndex) {
		byte[] meteKindByte = {meteKind.byteValue()};
		StringBuilder sbBasMeteId = new StringBuilder();
		sbBasMeteId.append(BytesUtils.bytes2hex(meteKindByte))
		           .append(BytesUtils.bytes2hex(BytesUtils.shortToByte2(deviceType)))
		           .append(BytesUtils.bytes2hex(BytesUtils.shortToByte2(meteType)))
		           .append(BytesUtils.bytes2hex(BytesUtils.shortToByte2(sameMeteTypeIndex)));
		return sbBasMeteId.toString();
	}
	
	public Integer getBasChannelNo(Short deviceModel,Integer basChannelNo) {
		return deviceModel*3000+basChannelNo;
	}
    
}
