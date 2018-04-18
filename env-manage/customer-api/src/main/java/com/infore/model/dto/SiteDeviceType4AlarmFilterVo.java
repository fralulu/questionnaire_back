/**
 * 
 */
package com.infore.model.dto;

import java.util.List;

import javax.validation.constraints.NotNull;

/**
 * @desc   
 * @class  SiteDeviceType4AlarmFilterVo
 * @author  create author by deer
 * @date  2018年1月15日上午9:44:01
 */
public class SiteDeviceType4AlarmFilterVo {
	@NotNull
	private Integer tenantId;
	
	private Byte roleId;
	
	 @NotNull
	private String name;
	 
	 @NotNull
	private List<Integer> siteIds;
	 
	 @NotNull
	private List<DeviceMeteTypeVo> deviceMeteTypes;
	 
	 @NotNull
	private List<Byte> reportLevels;

	/**
	 * @return the tenantId
	 */
	public Integer getTenantId() {
		return tenantId;
	}

	/**
	 * @param tenantId the tenantId to set
	 */
	public void setTenantId(Integer tenantId) {
		this.tenantId = tenantId;
	}

	/**
	 * @return the roleId
	 */
	public Byte getRoleId() {
		return roleId;
	}

	/**
	 * @param roleId the roleId to set
	 */
	public void setRoleId(Byte roleId) {
		this.roleId = roleId;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the siteIds
	 */
	public List<Integer> getSiteIds() {
		return siteIds;
	}

	/**
	 * @param siteIds the siteIds to set
	 */
	public void setSiteIds(List<Integer> siteIds) {
		this.siteIds = siteIds;
	}

	/**
	 * @return the deviceMeteTypes
	 */
	public List<DeviceMeteTypeVo> getDeviceMeteTypes() {
		return deviceMeteTypes;
	}

	/**
	 * @param deviceMeteTypes the deviceMeteTypes to set
	 */
	public void setDeviceMeteTypes(List<DeviceMeteTypeVo> deviceMeteTypes) {
		this.deviceMeteTypes = deviceMeteTypes;
	}

	/**
	 * @return the reportLevels
	 */
	public List<Byte> getReportLevels() {
		return reportLevels;
	}

	/**
	 * @param reportLevels the reportLevels to set
	 */
	public void setReportLevels(List<Byte> reportLevels) {
		this.reportLevels = reportLevels;
	}
	
	
	
}
