/**
 * 
 */
package com.infore.model.dto;

import java.util.List;

/**
 * @desc   
 * @class  DeviceMeteTypeVo
 * @author  create author by deer
 * @date  2018年1月15日下午4:40:17
 */
public class DeviceMeteTypeVo {
	private Short deviceType;
	private List<Integer> meteTypes;
	/**
	 * @return the deviceType
	 */
	public Short getDeviceType() {
		return deviceType;
	}
	/**
	 * @param deviceType the deviceType to set
	 */
	public void setDeviceType(Short deviceType) {
		this.deviceType = deviceType;
	}
	/**
	 * @return the meteTypes
	 */
	public List<Integer> getMeteTypes() {
		return meteTypes;
	}
	/**
	 * @param meteTypes the meteTypes to set
	 */
	public void setMeteTypes(List<Integer> meteTypes) {
		this.meteTypes = meteTypes;
	}
	
	
	
	
}
