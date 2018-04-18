/**
 * 
 */
package com.infore.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.infore.common.constants.IotManagerConstants;
//import com.infore.mapper.IotBasTeleadjustMapper;
//import com.infore.mapper.IotBasTeleconfigMapper;
//import com.infore.mapper.IotBasTelecontrolMapper;
//import com.infore.mapper.IotBasTelemeterMapper;
//import com.infore.mapper.IotBasTelesignalMapper;
import com.infore.model.dto.MeteInfo;
import com.infore.service.MeteTypeCacheService;

/**
 * @desc   
 * @class  MeteTypeCacheServiceImpl
 * @author  create author by deer
 * @date  2017年12月18日下午2:22:58
 */
@Service
public class MeteTypeCacheServiceImpl implements MeteTypeCacheService{
	/*protected final Logger logger = LoggerFactory.getLogger(this.getClass());
	*//**
     * 在支持Spring Cache的环境下
     * //@Cacheable Spring在每次执行前都会检查Cache中是否存在相同key的缓存元素，如果存在就不再执行该方法，而是直接从缓存中获取结果进行返回，否则才会执行并将返回结果存入指定的缓存中。
     * //@CacheEvict 清除缓存
     * //@CachePut @CachePut也可以声明一个方法支持缓存功能。使用@CachePut标注的方法在执行前不会去检查缓存中是否存在之前执行过的结果，而是每次都会执行该方法，并将执行结果以键值对的形式存入指定的缓存中。
     *
     *//*
	
	@Autowired
	 private IotBasTelemeterMapper  iotBasTelemeterMapper;
	
	@Autowired
	 private IotBasTelesignalMapper  iotBasTelesignalMapper;
	
	@Autowired
	 private IotBasTeleadjustMapper  iotBasTeleadjustMapper;
	
	@Autowired
	 private IotBasTelecontrolMapper  iotBasTelecontrolMapper;
	
	@Autowired
	 private IotBasTeleconfigMapper  iotBasTeleconfigMapper;
	
	
	
	//这里的单引号不能少，否则会报错，被识别是一个对象
    private static final String CACHE_KEY = "'meteInfo'";
    private static final String CACHE_NAME = "meteTypeCache";


    //删除数据
    @CacheEvict(value = CACHE_NAME,key = "'meteInfo_'+#meteKindStr")//这是清除缓存
    public void delete(String meteKindStr){
    	logger.info("从缓存中删除meteKind=["+meteKindStr+"]的数据");
    }

    //更新数据
    @CachePut(value = CACHE_NAME,key = "'meteInfo_'+#meteInfo.getMeteKindStr()")
    public MeteInfo update(MeteInfo meteInfo){
    	logger.info("更新缓存meteKind=["+meteInfo.getMeteKindStr()+"]的数据");
        return meteInfo;
    }

    //查找数据
    @Cacheable(value=CACHE_NAME,key="'meteInfo_'+#meteKindStr")
    public MeteInfo findByMeteKindStr(String meteKindStr){
        System.err.println("没有走缓存！"+meteKindStr);
        logger.error("没有走缓存！"+meteKindStr);
        MeteInfo meteInfo = new MeteInfo();
        Short max = null;
        switch(meteKindStr) {
	        case IotManagerConstants.METE_KIND_NAME_EN_AI:
	        	meteInfo.setMeteKind(Byte.valueOf(IotManagerConstants.METE_KIND_AI));
	        	meteInfo.setMeteKindStr(meteKindStr);
	        	max = iotBasTelemeterMapper.queryMaxMeteType(null);
	        	meteInfo.setMeteType(Short.valueOf(String.valueOf((max==null||max==0)?0: max)));
	        	
	        	break;
	        case IotManagerConstants.METE_KIND_NAME_EN_DI:
	        	meteInfo.setMeteKind(Byte.valueOf(IotManagerConstants.METE_KIND_DI));
	        	meteInfo.setMeteKindStr(meteKindStr);
	        	max = iotBasTelesignalMapper.queryMaxMeteType(null);
	        	meteInfo.setMeteType(Short.valueOf(String.valueOf((max==null||max==0)?1000: max)));
	        	break;
	        case IotManagerConstants.METE_KIND_NAME_EN_AO:
	        	meteInfo.setMeteKind(Byte.valueOf(IotManagerConstants.METE_KIND_AO));
	        	meteInfo.setMeteKindStr(meteKindStr);
	        	max = iotBasTeleadjustMapper.queryMaxMeteType(null);
	        	meteInfo.setMeteType(Short.valueOf(String.valueOf((max==null||max==0)?2000: max)));
	        	break;
	        case IotManagerConstants.METE_KIND_NAME_EN_DO:
	        	meteInfo.setMeteKind(Byte.valueOf(IotManagerConstants.METE_KIND_DO));
	        	meteInfo.setMeteKindStr(meteKindStr);
	        	max = iotBasTelecontrolMapper.queryMaxMeteType(null);
	        	meteInfo.setMeteType(Short.valueOf(String.valueOf((max==null||max==0)?3000: max)));
	        	break;
	        case IotManagerConstants.METE_KIND_NAME_EN_CF:
	        	meteInfo.setMeteKind(Byte.valueOf(IotManagerConstants.METE_KIND_CF));
	        	meteInfo.setMeteKindStr(meteKindStr);
	        	max = iotBasTeleconfigMapper.queryMaxMeteType(null);
	        	meteInfo.setMeteType(Short.valueOf(String.valueOf((max==null||max==0)?4000: max)));
	        	break;
	        default:
	        	break;
        }
        return meteInfo;
    }

    //保存数据
    @CacheEvict(value=CACHE_NAME,key=CACHE_KEY)
    public void save(MeteInfo meteInfo){
    	logger.info("保存缓存信息meteInfo="+meteInfo.toString());
    }
*/
}
