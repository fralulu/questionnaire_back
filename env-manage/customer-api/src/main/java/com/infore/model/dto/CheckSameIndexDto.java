/**
 * 
 */
package com.infore.model.dto;

import java.util.List;

/**
 * @desc   
 * @class  CheckSameIndexDto
 * @author  create author by deer
 * @date  2017年12月21日上午9:35:00
 */
public class CheckSameIndexDto {
	private Short deviceType;
	private List<Same> sames;
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
	 * @return the sames
	 */
	public List<Same> getSames() {
		return sames;
	}
	/**
	 * @param sames the sames to set
	 */
	public void setSames(List<Same> sames) {
		this.sames = sames;
	}
	
	public class Same{
		private String basMeteId;
		private Short meteType;
		/**
		 * @return the basMeteId
		 */
		public String getBasMeteId() {
			return basMeteId;
		}
		/**
		 * @param basMeteId the basMeteId to set
		 */
		public void setBasMeteId(String basMeteId) {
			this.basMeteId = basMeteId;
		}
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
		
	}
	
}

