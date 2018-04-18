/**
 * 
 */
package com.infore.model.dto;

import java.util.List;

/**
 * @desc   
 * @class  CopySiteVo
 * @author  create author by deer
 * @date  2018年1月8日下午1:14:00
 */
public class CopySiteVo {
	
	private Integer siteId;
	
	private List<AreaInfo> areaInfos;
	
	
	/**
	 * @return the siteId
	 */
	public Integer getSiteId() {
		return siteId;
	}



	/**
	 * @param siteId the siteId to set
	 */
	public void setSiteId(Integer siteId) {
		this.siteId = siteId;
	}



	/**
	 * @return the areaInfos
	 */
	public List<AreaInfo> getAreaInfos() {
		return areaInfos;
	}



	/**
	 * @param areaInfos the areaInfos to set
	 */
	public void setAreaInfos(List<AreaInfo> areaInfos) {
		this.areaInfos = areaInfos;
	}



	public static  class AreaInfo{
		//管辖区编号
		private Integer precinctId;
		//楼室编号
		private Integer roomId;
		//楼层编号
		private Integer floorId;
		//楼栋编号
		private Integer buildingId;
		/**
		 * @return the precinctId
		 */
		public Integer getPrecinctId() {
			return precinctId;
		}
		/**
		 * @param precinctId the precinctId to set
		 */
		public void setPrecinctId(Integer precinctId) {
			this.precinctId = precinctId;
		}
		/**
		 * @return the roomId
		 */
		public Integer getRoomId() {
			return roomId;
		}
		/**
		 * @param roomId the roomId to set
		 */
		public void setRoomId(Integer roomId) {
			this.roomId = roomId;
		}
		/**
		 * @return the floorId
		 */
		public Integer getFloorId() {
			return floorId;
		}
		/**
		 * @param floorId the floorId to set
		 */
		public void setFloorId(Integer floorId) {
			this.floorId = floorId;
		}
		/**
		 * @return the buildingId
		 */
		public Integer getBuildingId() {
			return buildingId;
		}
		/**
		 * @param buildingId the buildingId to set
		 */
		public void setBuildingId(Integer buildingId) {
			this.buildingId = buildingId;
		}

	}
}
