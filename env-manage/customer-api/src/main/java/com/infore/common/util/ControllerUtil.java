package com.infore.common.util;

import com.infore.model.ResponseDto;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

/**
 * @desc Controller帮助类
 * @author Created by Cai.xu
 * @date 2017/6/28-下午3:49
 */
public class ControllerUtil {
    /**
     * 获取request请求对象
     * @return
     */
    public static HttpServletRequest getRequest() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        return request;
    }

    /**
     * 获取session
     * @return
     */
    public static HttpSession getSession(){
        return getRequest().getSession();
    }

    /**
     * request取值转换为Map
     * @param request
     * @return
     */
    public static Map<String, Object> getParametersMap(HttpServletRequest request) {
        Map<String, Object> map = new HashMap<String, Object>();
        Enumeration<?> parameterNames = request.getParameterNames();
        while (parameterNames.hasMoreElements()) {
            String paraName = (String) parameterNames.nextElement();
            map.put(paraName, request.getParameter(paraName).trim());
        }
        return map;
    }


    /**
     * request取值转换为Bean
     * @param request
     * @param clazz
     * @return
     * @throws Exception
     */
    public static <T> T getParametersBean(HttpServletRequest request, Class clazz) throws Exception {
        Map<String, Object> map = new HashMap<String, Object>();
        Enumeration<?> parameterNames = request.getParameterNames();
        while (parameterNames.hasMoreElements()) {
            String paraName = (String) parameterNames.nextElement();
            map.put(paraName, request.getParameter(paraName).trim());
        }
        Object value = null;

        Class[] paramTypes = new Class[1];

        Object obj = null;

        try {
            //创建实例
            obj = clazz.newInstance();
            Field[] f = clazz.getDeclaredFields();
            List<Field[]> flist = new ArrayList<Field[]>();
            flist.add(f);

            Class superClazz = clazz.getSuperclass();
            while(superClazz != null){
                f = superClazz.getFields();
                flist.add(f);
                superClazz = superClazz.getSuperclass();
            }

            for (Field[] fields : flist) {
                for (Field field : fields) {
                    String fieldName = field.getName();
                    value = map.get(fieldName);
                    if(value != null){
                        paramTypes[0] = field.getType();
                        Method method = null;
                        //调用相应对象的set方法
                        StringBuffer methodName = new StringBuffer("set");
                        methodName.append(fieldName.substring(0, 1).toUpperCase());
                        methodName.append(fieldName.substring(1, fieldName.length()));
                        //测试输出
                        //System.out.println(paramTypes[0].getName());
                        method = clazz.getMethod(methodName.toString(), paramTypes);
                        method.invoke(obj, ConvertUtil.getValue(value.toString(), fieldName, paramTypes[0]));
                    }
                }
            }
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (SecurityException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return (T)obj;
    }

    /**
     * 响应结果
     * @param success
     * @param msg
     * @param data
     * @return
     */
    public static ResponseDto returnDto(boolean success, String msg, Object data){
        ResponseDto dto = new ResponseDto(success, msg, data);
        return dto;
    }
}
