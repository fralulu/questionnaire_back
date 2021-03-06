package com.infore.common.enums;

/**
 * 流媒体服务器类型
 * @author xuy
 * @date 2017年11月22日
 */
public enum VideoStreamTypeEnums {
	STREAM_TYPE_1(1,"Streamer"),
	STREAM_TYPE_2(2,"VodServer");
	
	private int value;
	private String name;
	
	private VideoStreamTypeEnums(int value, String name){
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
