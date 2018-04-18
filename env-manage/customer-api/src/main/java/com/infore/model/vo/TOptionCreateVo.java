/**
 * 
 */
package com.infore.model.vo;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

import io.swagger.annotations.ApiModelProperty;

/**
 * @desc   
 * @class  TOptionCreateVo
 * @author  create author by deer
 * @date  2018年4月11日上午8:56:13
 */
public class TOptionCreateVo {
	
	@ApiModelProperty(value = "试题编号")
    @NotNull
    private Integer testId;

    @ApiModelProperty(value = "选项类型：1-文字；2-图片")
    @NotBlank
    private String optionType;

    @ApiModelProperty(value = "选项内容")
    @NotBlank
    private String content;

    @ApiModelProperty(value = "图片")
    private String img;

    @ApiModelProperty(value = "选项提示信息")
    private String description;

    @ApiModelProperty(value = "显示控制:0-横向排列；1-竖向排列")
    @NotBlank
    private String viewControl;

    @ApiModelProperty(value = "选项关联关系")
    private String relationOption;

    @ApiModelProperty(value = "创建人(出题人)")
    @NotNull
    private Integer createUser;
    
    @ApiModelProperty(value = "备注")
    private String remark;

	/**
	 * @return the testId
	 */
	public Integer getTestId() {
		return testId;
	}

	/**
	 * @param testId the testId to set
	 */
	public void setTestId(Integer testId) {
		this.testId = testId;
	}

	/**
	 * @return the optionType
	 */
	public String getOptionType() {
		return optionType;
	}

	/**
	 * @param optionType the optionType to set
	 */
	public void setOptionType(String optionType) {
		this.optionType = optionType;
	}

	/**
	 * @return the content
	 */
	public String getContent() {
		return content;
	}

	/**
	 * @param content the content to set
	 */
	public void setContent(String content) {
		this.content = content;
	}

	/**
	 * @return the img
	 */
	public String getImg() {
		return img;
	}

	/**
	 * @param img the img to set
	 */
	public void setImg(String img) {
		this.img = img;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the viewControl
	 */
	public String getViewControl() {
		return viewControl;
	}

	/**
	 * @param viewControl the viewControl to set
	 */
	public void setViewControl(String viewControl) {
		this.viewControl = viewControl;
	}

	/**
	 * @return the relationOption
	 */
	public String getRelationOption() {
		return relationOption;
	}

	/**
	 * @param relationOption the relationOption to set
	 */
	public void setRelationOption(String relationOption) {
		this.relationOption = relationOption;
	}

	/**
	 * @return the createUser
	 */
	public Integer getCreateUser() {
		return createUser;
	}

	/**
	 * @param createUser the createUser to set
	 */
	public void setCreateUser(Integer createUser) {
		this.createUser = createUser;
	}

	/**
	 * @return the remark
	 */
	public String getRemark() {
		return remark;
	}

	/**
	 * @param remark the remark to set
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}
    
    
}
