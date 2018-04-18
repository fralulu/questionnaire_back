package com.infore.model;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.NotBlank;
/**
 * 试题信息表
 * 实体类对应的数据表为：  t_test
 * @author Jeff
 * @date 2018-03-29 17:30:00
 */
@ApiModel(value ="TTest")
public class TTest {
    @ApiModelProperty(value = "试题编号")
    @NotNull
    private Integer id;

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

    public Integer getPaperId() {
        return paperId;
    }

    public void setPaperId(Integer paperId) {
        this.paperId = paperId;
    }

    public String getTestType() {
        return testType;
    }

    public void setTestType(String testType) {
        this.testType = testType == null ? null : testType.trim();
    }

    public String getTestName() {
        return testName;
    }

    public void setTestName(String testName) {
        this.testName = testName == null ? null : testName.trim();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public String getRequired() {
        return required;
    }

    public void setRequired(String required) {
        this.required = required == null ? null : required.trim();
    }

    public Integer getJumpId() {
        return jumpId;
    }

    public void setJumpId(Integer jumpId) {
        this.jumpId = jumpId;
    }

    public String getRelationTest() {
        return relationTest;
    }

    public void setRelationTest(String relationTest) {
        this.relationTest = relationTest == null ? null : relationTest.trim();
    }

    public String getViewControl() {
        return viewControl;
    }

    public void setViewControl(String viewControl) {
        this.viewControl = viewControl == null ? null : viewControl.trim();
    }

    public String getOptionControl() {
        return optionControl;
    }

    public void setOptionControl(String optionControl) {
        this.optionControl = optionControl == null ? null : optionControl.trim();
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
        sb.append(", paperId=").append(paperId);
        sb.append(", testType=").append(testType);
        sb.append(", testName=").append(testName);
        sb.append(", description=").append(description);
        sb.append(", required=").append(required);
        sb.append(", jumpId=").append(jumpId);
        sb.append(", relationTest=").append(relationTest);
        sb.append(", viewControl=").append(viewControl);
        sb.append(", optionControl=").append(optionControl);
        sb.append(", createUser=").append(createUser);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateUser=").append(updateUser);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", remark=").append(remark);
        sb.append("]");
        return sb.toString();
    }
}