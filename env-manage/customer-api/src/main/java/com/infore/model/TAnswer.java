package com.infore.model;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.NotBlank;
/**
 * 用户答案信息表
 * 实体类对应的数据表为：  t_answer
 * @author Jeff
 * @date 2018-03-29 17:30:00
 */
@ApiModel(value ="TAnswer")
public class TAnswer {
    @ApiModelProperty(value = "编号")
    @NotNull
    private Integer id;

    @ApiModelProperty(value = "答题用户")
    @NotNull
    private Integer userId;

    @ApiModelProperty(value = "试卷编号")
    @NotNull
    private Integer paperId;

    @ApiModelProperty(value = "试题编号")
    @NotNull
    private Integer testId;

    @ApiModelProperty(value = "答案：多选题以|分割开")
    private String answer;

    @ApiModelProperty(value = "答题来源")
    private String answerIp;

    @ApiModelProperty(value = "答题渠道：1-手机微信；2-手机qq;3-pc；9-其他")
    private String channel;

    @ApiModelProperty(value = "统计标识：0-未统计；1-统计完毕")
    @NotBlank
    private String statisticsFlag;

    @ApiModelProperty(value = "备注")
    private String remark;

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

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
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

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer == null ? null : answer.trim();
    }

    public String getAnswerIp() {
        return answerIp;
    }

    public void setAnswerIp(String answerIp) {
        this.answerIp = answerIp == null ? null : answerIp.trim();
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel == null ? null : channel.trim();
    }

    public String getStatisticsFlag() {
        return statisticsFlag;
    }

    public void setStatisticsFlag(String statisticsFlag) {
        this.statisticsFlag = statisticsFlag == null ? null : statisticsFlag.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
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
        sb.append(", userId=").append(userId);
        sb.append(", paperId=").append(paperId);
        sb.append(", testId=").append(testId);
        sb.append(", answer=").append(answer);
        sb.append(", answerIp=").append(answerIp);
        sb.append(", channel=").append(channel);
        sb.append(", statisticsFlag=").append(statisticsFlag);
        sb.append(", remark=").append(remark);
        sb.append(", createUser=").append(createUser);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateUser=").append(updateUser);
        sb.append(", updateTime=").append(updateTime);
        sb.append("]");
        return sb.toString();
    }
}