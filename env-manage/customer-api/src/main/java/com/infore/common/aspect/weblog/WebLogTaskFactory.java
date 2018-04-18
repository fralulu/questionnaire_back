package com.infore.common.aspect.weblog;

import java.util.TimerTask;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.infore.common.util.SpringContextUtils;
import com.infore.model.IotHisOperation;
import com.infore.service.IotHisOperationService;

/**
 * 日志操作任务创建工厂
 *
 */
public class WebLogTaskFactory {

    private static Logger logger = LoggerFactory.getLogger(WebLogTaskFactory.class);
    @SuppressWarnings("unchecked")
	private static  IotHisOperationService<IotHisOperation> hisOperationService = SpringContextUtils.getBean(IotHisOperationService.class);


    /**
     * 
     * @Title: bussinessLog   
     * @Description: TODO(这里用一句话描述这个方法的作用)   
     * 
     * @param: @param resultCode
     * 
     * @param: @param userId
     *                用户id
     * @param: @param bussinessName
     *                业务名称
     * @param: @param clazzName
     *                类名称
     * @param: @param methodName
     *                方法名称
     * @param: @param params
     *                操作日志
     * @param: @param useroperationId
     *                用户操作编号
     * @param: @param errMes
     *                错误情况下，controller返回的错误信息
     * @param: @return      
     * @return: TimerTask      
     * @throws
     */
    public static TimerTask bussinessLog(Short resultCode,final Integer userId, final Integer tenantId,final String bussinessName, final String clazzName, 
    		final String methodName, final String params,final String useroperationId,String errMes ) {
        return new TimerTask() {
            @Override
            public void run() {
            	IotHisOperation operationLog = WebLogFactory.createLog(resultCode, userId, tenantId, bussinessName, clazzName, methodName, params, useroperationId,errMes);
                try {
                	//hisOperationService.insert(operationLog);
                } catch (Exception e) {
                    logger.error("创建业务日志异常!", e);
                }
            }
        };
    }
}