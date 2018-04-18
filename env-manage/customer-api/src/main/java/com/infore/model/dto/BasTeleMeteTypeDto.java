/**
 * 
 */
package com.infore.model.dto;

/**
 * @desc   
 * @class  BasTeleMeteTypeDto
 * @author  create author by deer
 * @date  2017年12月14日上午9:22:29
 */
public class BasTeleMeteTypeDto {
	
	/**
	 * 遥测量类型
	 */
	private Short meteType;
	
	/**
	 * 遥测量类型名称
	 */
	private String metetypeName;
	
	/**
	 * 监控量类型的英文名称
	 */
	private String metetypeNameEn;

	/**
	 * @return the meteType
	 */
	public Short getMeteType() {
		return meteType;
	}

	/**
	 * @param meteType the meteType to set
	 */
	public void setMeteType(Short meteType) {
		this.meteType = meteType;
	}

	/**
	 * @return the metetypeName
	 */
	public String getMetetypeName() {
		return metetypeName;
	}

	/**
	 * @param metetypeName the metetypeName to set
	 */
	public void setMetetypeName(String metetypeName) {
		this.metetypeName = metetypeName;
	}

	/**
	 * @return the metetypeNameEn
	 */
	public String getMetetypeNameEn() {
		return metetypeNameEn;
	}

	/**
	 * @param metetypeNameEn the metetypeNameEn to set
	 */
	public void setMetetypeNameEn(String metetypeNameEn) {
		this.metetypeNameEn = metetypeNameEn;
	}
	
	
	
	
}
