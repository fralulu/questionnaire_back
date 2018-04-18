package com.infore.model.dto;

import com.infore.model.UserInfo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "UserInfoDto")
public class UserInfoDto extends UserInfo{
	@ApiModelProperty(value = "所在部门名称")
	private String orgName;

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

}