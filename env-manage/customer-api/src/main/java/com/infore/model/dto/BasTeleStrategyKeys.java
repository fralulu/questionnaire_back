package com.infore.model.dto;

import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModelProperty;

public class BasTeleStrategyKeys{
	

    @ApiModelProperty(value = "遥测量类型")
    @NotNull
    private Short meteType;

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