package com.infore.common.enums;

/**
 * 流媒体服务器状态
 * @author xuy
 * @date 2017年11月22日
 */
public enum VideoStreamStatusEnums {
	STREAM_STATUS_0(0,"不在线"),
	STREAM_STATUS_1(1,"在线");
	
	private int value;
	private String name;
	
	private VideoStreamStatusEnums(int value, String name){
		this.value=value;
		this.name=name;	
	}

	/**
	 * @return the value
	 */
	public int getValue() {
		return value;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	
	public static int getValue(String name){
		for(VideoStreamTypeEnums typeEnums:VideoStreamTypeEnums.values()){
			if (typeEnums.getName().equals(name)) {
				return typeEnums.getValue();
			}
		}
		return 0;
	}
	
	
}
