/**
 * 
 */
package com.infore.model.vo;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

import io.swagger.annotations.ApiModelProperty;

/**
 * @desc   
 * @class  TPaperUpdateVo
 * @author  create author by deer
 * @date  2018年4月10日下午3:01:05
 */
public class TPaperUpdateVo {
	
	@ApiModelProperty(value = "试卷编号")
    @NotNull
    private Integer id;
	
	@ApiModelProperty(value = "试卷类型:1-在线考试；2-在线投票；3-报名表单；4-在线测评")
    @NotBlank
    private String paperType;

    @ApiModelProperty(value = "试卷名称")
    @NotBlank
    private String paperName;

    @ApiModelProperty(value = "试卷说明")
    private String description;

    @ApiModelProperty(value = "试卷状态:0-新建；1-启用（启用后才可以使用）；2-禁用；3-删除")
    @NotBlank
    private String status;
    
    @ApiModelProperty(value = "修改人")
    private Integer updateUser;
    
    @ApiModelProperty(value = "备注")
    private String remark;

	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * @return the paperType
	 */
	public String getPaperType() {
		return paperType;
	}

	/**
	 * @param paperType the paperType to set
	 */
	public void setPaperType(String paperType) {
		this.paperType = paperType;
	}

	/**
	 * @return the paperName
	 */
	public String getPaperName() {
		return paperName;
	}

	/**
	 * @param paperName the paperName to set
	 */
	public void setPaperName(String paperName) {
		this.paperName = paperName;
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
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * @return the updateUser
	 */
	public Integer getUpdateUser() {
		return updateUser;
	}

	/**
	 * @param updateUser the updateUser to set
	 */
	public void setUpdateUser(Integer updateUser) {
		this.updateUser = updateUser;
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
