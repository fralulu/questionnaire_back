package com.infore.common.aspect.weblog;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
public @interface WebLog {
	//操作业务名称
	String value() default "";
	//对应 表 iot_bas_useroperation 里面的编号
	String useroperationId() default "";
}
