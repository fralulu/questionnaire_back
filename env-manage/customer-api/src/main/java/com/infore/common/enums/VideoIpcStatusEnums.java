package com.infore.common.enums;

/**
 * 流媒体服务器状态
 * @author xuy
 * @date 2017年11月22日
 */
public enum VideoIpcStatusEnums {
	//IPC状态.notConn:未连接;paramErr:已连接参数错误;conn:已连接;allotSuccess:分配成功;recording:正在录制
	
	NOT_CONN("notConn","不在线"),
	PARAM_ERR("paramErr","已连接参数错误"),
	CONN("conn","已连接"),
	ALLOT_SUCCESS("allotSuccess","分配成功"),
	RECORDING("recording","正在录制");
	
	private String code;
	private String desc;
	
	private VideoIpcStatusEnums(String code, String desc){
		this.code=code;
		this.desc=desc;	
	}

		
	public static int getValue(String name){
		for(VideoStreamTypeEnums typeEnums:VideoStreamTypeEnums.values()){
			if (typeEnums.getName().equals(name)) {
				return typeEnums.getValue();
			}
		}
		return 0;
	}


	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}


	/**
	 * @return the desc
	 */
	public String getDesc() {
		return desc;
	}
	
	
}
