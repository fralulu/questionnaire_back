package com.infore.model;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.NotBlank;
/**
 * 试题选项信息表
 * 实体类对应的数据表为：  t_option
 * @author Jeff
 * @date 2018-03-29 17:30:00
 */
@ApiModel(value ="TOption")
public class TOption {
    @ApiModelProperty(value = "选项编号")
    @NotNull
    private Integer id;

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

    @ApiModelProperty(value = "创建时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @NotNull
    private Date createTime;

    @ApiModelProperty(value = "修改人")
    private Integer updateUser;

    @ApiModelProperty(value = "修改时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

    @ApiModelProperty(value = "备注")
    private String remark;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getTestId() {
        return testId;
    }

    public void setTestId(Integer testId) {
        this.testId = testId;
    }

    public String getOptionType() {
        return optionType;
    }

    public void setOptionType(String optionType) {
        this.optionType = optionType == null ? null : optionType.trim();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img == null ? null : img.trim();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public String getViewControl() {
        return viewControl;
    }

    public void setViewControl(String viewControl) {
        this.viewControl = viewControl == null ? null : viewControl.trim();
    }

    public String getRelationOption() {
        return relationOption;
    }

    public void setRelationOption(String relationOption) {
        this.relationOption = relationOption == null ? null : relationOption.trim();
    }

    public Integer getCreateUser() {
        return createUser;
    }

    public void setCreateUser(Integer createUser) {
        this.createUser = createUser;
    }

    @JsonFormat(locale = "zh", timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(Integer updateUser) {
        this.updateUser = updateUser;
    }

    @JsonFormat(locale = "zh", timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", testId=").append(testId);
        sb.append(", optionType=").append(optionType);
        sb.append(", content=").append(content);
        sb.append(", img=").append(img);
        sb.append(", description=").append(description);
        sb.append(", viewControl=").append(viewControl);
        sb.append(", relationOption=").append(relationOption);
        sb.append(", createUser=").append(createUser);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateUser=").append(updateUser);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", remark=").append(remark);
        sb.append("]");
        return sb.toString();
    }
}