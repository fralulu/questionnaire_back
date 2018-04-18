package com.infore.common.enums;

/**
 * @author xuy
 * @date 2017年12月6日
 */
public enum QueryTypeEnums {
	site("查询站点"),current("当前节点");
	
	private String value;
	
	private QueryTypeEnums(String value){
		this.value=value;
	}

}
