/**
 * 
 */
package com.infore.model.vo;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

import io.swagger.annotations.ApiModelProperty;

/**
 * @desc   
 * @class  TTestCreateVo
 * @author  create author by deer
 * @date  2018年4月10日下午3:24:55
 */
public class TTestCreateVo {
	@ApiModelProperty(value = "试卷编号")
    @NotNull
    private Integer paperId;

    @ApiModelProperty(value = "试题类型:1-单选题；2-多选题；3-文本题；4-问答题")
    @NotBlank
    private String testType;

    @ApiModelProperty(value = "试题名称")
    @NotBlank
    private String testName;

    @ApiModelProperty(value = "试题提示说明")
    private String description;

    @ApiModelProperty(value = "是否必答:0-非必答；1-必答")
    @NotBlank
    private String required;

    @ApiModelProperty(value = "跳题序号")
    private Integer jumpId;

    @ApiModelProperty(value = "关联试题")
    private String relationTest;

    @ApiModelProperty(value = "显示控制:0-横向排列；1-竖向排列")
    @NotBlank
    private String viewControl;

    @ApiModelProperty(value = "选项控制(多选题使用)")
    @NotBlank
    private String optionControl;

    @ApiModelProperty(value = "创建人(出题人)")
    @NotNull
    private Integer createUser;
    
    @ApiModelProperty(value = "备注")
    private String remark;

	/**
	 * @return the paperId
	 */
	public Integer getPaperId() {
		return paperId;
	}

	/**
	 * @param paperId the paperId to set
	 */
	public void setPaperId(Integer paperId) {
		this.paperId = paperId;
	}

	/**
	 * @return the testType
	 */
	public String getTestType() {
		return testType;
	}

	/**
	 * @param testType the testType to set
	 */
	public void setTestType(String testType) {
		this.testType = testType;
	}

	/**
	 * @return the testName
	 */
	public String getTestName() {
		return testName;
	}

	/**
	 * @param testName the testName to set
	 */
	public void setTestName(String testName) {
		this.testName = testName;
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
	 * @return the required
	 */
	public String getRequired() {
		return required;
	}

	/**
	 * @param required the required to set
	 */
	public void setRequired(String required) {
		this.required = required;
	}

	/**
	 * @return the jumpId
	 */
	public Integer getJumpId() {
		return jumpId;
	}

	/**
	 * @param jumpId the jumpId to set
	 */
	public void setJumpId(Integer jumpId) {
		this.jumpId = jumpId;
	}

	/**
	 * @return the relationTest
	 */
	public String getRelationTest() {
		return relationTest;
	}

	/**
	 * @param relationTest the relationTest to set
	 */
	public void setRelationTest(String relationTest) {
		this.relationTest = relationTest;
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
	 * @return the optionControl
	 */
	public String getOptionControl() {
		return optionControl;
	}

	/**
	 * @param optionControl the optionControl to set
	 */
	public void setOptionControl(String optionControl) {
		this.optionControl = optionControl;
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
