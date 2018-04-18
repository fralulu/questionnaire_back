/**
 * 
 */
package com.infore.model.dto;

/**
 * @desc   
 * @class  MeteInfo
 * @author  create author by deer
 * @date  2017年12月18日下午2:31:35
 */
public class MeteInfo {
	/**
	 * 检测量类型
	 */
	private Short meteType;
	/**
	 * 检测量种类
	 */
	private Byte meteKind;
	/**
	 * 检测量种类中文名称
	 */
	private String meteKindStr;
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
	 * @return the meteKind
	 */
	public Byte getMeteKind() {
		return meteKind;
	}
	/**
	 * @param meteKind the meteKind to set
	 */
	public void setMeteKind(Byte meteKind) {
		this.meteKind = meteKind;
	}
	/**
	 * @return the meteKindStr
	 */
	public String getMeteKindStr() {
		return meteKindStr;
	}
	/**
	 * @param meteKindStr the meteKindStr to set
	 */
	public void setMeteKindStr(String meteKindStr) {
		this.meteKindStr = meteKindStr;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "MeteInfo [meteType=" + meteType + ", meteKind=" + meteKind + ", meteKindStr=" + meteKindStr + "]";
	}
	
	
	
}
