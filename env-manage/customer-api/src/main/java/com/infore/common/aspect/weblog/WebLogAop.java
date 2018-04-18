/**
 * 
 */
package com.infore.common.aspect.weblog;

import java.lang.reflect.Method;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.infore.common.constant.Const;
import com.infore.common.constant.Const.Jwt;
import com.infore.common.constants.IotManagerConstants;
import com.infore.model.ResponseDto;

/**
 * @desc   记录日志 aop
 * @class  WebLogAop
 * @author  create author by deer
 * @date  2018年1月11日下午2:38:58
 * 
 * 实现AOP的切面主要有以下几个要素：
 *使用@Aspect注解将一个java类定义为切面类
 *使用@Pointcut定义一个切入点，可以是一个规则表达式，比如下例中某个package下的所有函数，也可以是一个注解等。
 *根据需要在切入点不同位置的切入内容
 *使用@Before在切入点开始处切入内容
 *使用@After在切入点结尾处切入内容
 *使用@AfterReturning在切入点return内容之后切入内容（可以用来对处理返回值做一些加工处理）
 *使用@Around在切入点前后切入内容，并自己控制何时执行切入点自身的内容
 *使用@AfterThrowing用来处理当切入内容部分抛出异常之后的处理逻辑
 */
@Aspect
@Component
public class WebLogAop {
	protected Logger logger = LoggerFactory.getLogger(this.getClass());
	
   //使用@Pointcut定义一个切入点
   @Pointcut(value = "@annotation(com.infore.common.aspect.weblog.WebLog)")
   public void webLog() {}
   
   
   @SuppressWarnings("rawtypes")
   @Around("webLog()")
   public Object writerWebLog(ProceedingJoinPoint point) throws Throwable {

       //先执行业务
       Object result = point.proceed();

       try {
    	   
    	   Short resultCode = IotManagerConstants.RESULT_CODE_SUCESS;
    	   String errorMessage = null;
    	   //判断执行业务是否有异常
           if(null != result ) {
        	   ResponseDto dto = (ResponseDto)result;
        	   if(!dto.getSuccess()) {
        		   resultCode = IotManagerConstants.RESULT_CODE_FAILD;
            	   errorMessage = dto.getMsg();
        	   }
           }else {
        	   resultCode = IotManagerConstants.RESULT_CODE_FAILD;
        	   errorMessage = "系统异常，control 返回 null";
           }
    	   
    	   //记录日志
           handle(point,resultCode,errorMessage);
       } catch (Exception e) {
    	   logger.error("日志记录出错!", e);
       }

       return result;
   }
   
   private void handle(ProceedingJoinPoint point,Short returnCode,String errorMessage) throws Exception {

       //获取拦截的方法名
       Signature sig = point.getSignature();
       MethodSignature msig = null;
       if (!(sig instanceof MethodSignature)) {
           throw new IllegalArgumentException("该注解只能用于方法");
       }
       msig = (MethodSignature) sig;
       Object target = point.getTarget();
       Method currentMethod = target.getClass().getMethod(msig.getName(), msig.getParameterTypes());
       String methodName = currentMethod.getName();
       
     
       //获取拦截方法的参数
       String className = point.getTarget().getClass().getName();
       Object[] params = point.getArgs();

       //获取操作名称
       WebLog annotation = currentMethod.getAnnotation(WebLog.class);
       String bussinessName = annotation.value();
       String useroperationId = annotation.useroperationId();
       Integer userId = null;
       Integer tenantId = null;
        
       //获取请求参数
        StringBuilder sb = new StringBuilder();
        for (Object param : params) {
       	 if (param instanceof HttpServletRequest) {
	       		 HttpServletRequest request = (HttpServletRequest) param;
	       		 //取得用户信息
	       		 userId = Integer.valueOf((String)request.getAttribute(Jwt.USERID));
	       		 tenantId = Integer.valueOf((int)request.getAttribute(Const.TID));
	       		 Map<String, Object> map = new HashMap<>();
       	        Enumeration<?> parameterNames = request.getParameterNames();
       	        while (parameterNames.hasMoreElements()) {
       	            String paraName = (String) parameterNames.nextElement();
       	            map.put(paraName, request.getParameter(paraName) == null ? null : request.getParameter(paraName).trim());
       	        }
       	        if(!map.isEmpty()) {
       	        	sb.append(map.toString());
       	        }
			}else {
				if(!StringUtils.isBlank(sb.toString())) {
		       		 sb.append(" & ");
		       	 }
				sb.append(JSON.toJSONString(param));
			}
       	 
       	 
       }
       //业务操作日志
       WebLogManager.me().executeLog(WebLogTaskFactory.bussinessLog(returnCode,userId,tenantId, bussinessName, 
    		   className, methodName, sb.toString(),useroperationId,errorMessage));
   }
   
   
	
}
