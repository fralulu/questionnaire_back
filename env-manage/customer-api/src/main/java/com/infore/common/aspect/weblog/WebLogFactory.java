/**
 * 
 */
package com.infore.common.aspect.weblog;


import java.util.Date;

import com.infore.common.constants.IotManagerConstants;
import com.infore.model.IotHisOperation;


/**
 * @desc   
 * @class  WebLogObj
 * @author  create author by deer
 * @date  2018年1月12日上午9:28:39
 */
public class WebLogFactory {

	public static IotHisOperation createLog(Short resultCode, Integer userId, Integer tenantId, String bussinessName,
			String clazzName, String methodName, String msg, String useroperationId,String errorMes) {
        IotHisOperation iotHisOperation = new IotHisOperation();
    	
    	iotHisOperation.setTenantId(tenantId);//用户所在的租户编号
    	iotHisOperation.setOperateTime(new Date());//操作时间
    	iotHisOperation.setUserId(userId);//系统用户id
    	//操作针对的站点编号
    	//操作针对的设备编号
    	//操作针对的监控量类型
    	//监控量类型
    	iotHisOperation.setOperateCode(Short.valueOf(useroperationId));//操作内容编号
    	iotHisOperation.setResultCode(resultCode);//操作结果
    	StringBuilder sb = new StringBuilder();
    	sb.append("操作业务=[").append(bussinessName).append("],")
    	  .append("clazzName=[").append(clazzName).append("],")
    	  .append("methodName=[").append(methodName).append("],")
    	  .append("params=[").append(msg).append("]");
    	if(IotManagerConstants.RESULT_CODE_FAILD.equals(resultCode)) {
    		sb.append(",errMsg=[").append(errorMes).append("]");
    	}
    	iotHisOperation.setOperateContent(sb.toString());//操作内容
    	return iotHisOperation;
	}


}
