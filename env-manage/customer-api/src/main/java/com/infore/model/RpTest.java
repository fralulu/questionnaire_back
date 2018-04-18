package com.infore.model;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.NotBlank;
/**
 * 试题答题结果统计信息
 * 实体类对应的数据表为：  rp_test
 * @author Jeff
 * @date 2018-03-29 17:30:00
 */
@ApiModel(value ="RpTest")
public class RpTest {
    @ApiModelProperty(value = "编号")
    @NotNull
    private Integer id;

    @ApiModelProperty(value = "试卷编号")
    @NotNull
    private Integer paperId;

    @ApiModelProperty(value = "试题编号")
    @NotNull
    private Integer testId;

    @ApiModelProperty(value = "试题选项")
    @NotBlank
    private String testOptions;

    @ApiModelProperty(value = "试题选项答题统计")
    @NotBlank
    private String optionAnswerCount;

    @ApiModelProperty(value = "创建人")
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

    public Integer getTestId() {
        return testId;
    }

    public void setTestId(Integer testId) {
        this.testId = testId;
    }

    public String getTestOptions() {
        return testOptions;
    }

    public void setTestOptions(String testOptions) {
        this.testOptions = testOptions == null ? null : testOptions.trim();
    }

    public String getOptionAnswerCount() {
        return optionAnswerCount;
    }

    public void setOptionAnswerCount(String optionAnswerCount) {
        this.optionAnswerCount = optionAnswerCount == null ? null : optionAnswerCount.trim();
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

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", paperId=").append(paperId);
        sb.append(", testId=").append(testId);
        sb.append(", testOptions=").append(testOptions);
        sb.append(", optionAnswerCount=").append(optionAnswerCount);
        sb.append(", createUser=").append(createUser);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateUser=").append(updateUser);
        sb.append(", updateTime=").append(updateTime);
        sb.append("]");
        return sb.toString();
    }
}