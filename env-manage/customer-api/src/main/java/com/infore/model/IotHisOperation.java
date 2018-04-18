package com.infore.model;


import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.NotBlank;
/**
 * 用户操作日志表
 * 实体类对应的数据表为：  iot_his_operation
 * @author Jeff
 * @date 2018-01-12 09:46:08
 */
@ApiModel(value ="IotHisOperation")
public class IotHisOperation {
    @ApiModelProperty(value = "用户所在的租户编号")
    @NotNull
    private Integer tenantId;

    @ApiModelProperty(value = "操作时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @NotNull
    private Date operateTime;

    @ApiModelProperty(value = "系统用户id")
    @NotNull
    private Integer userId;

    @ApiModelProperty(value = "站点编号")
    private Integer siteId;

    @ApiModelProperty(value = "操作针对的设备编号")
    private Integer deviceId;

    @ApiModelProperty(value = "操作针对的监控量类型")
    private Integer meteId;

    @ApiModelProperty(value = "监控量类型")
    private Byte meteKind;

    @ApiModelProperty(value = "操作内容编号")
    @NotNull
    private Short operateCode;

    @ApiModelProperty(value = "操作结果")
    private Short resultCode;

    @ApiModelProperty(value = "操作内容")
    @NotBlank
    private String operateContent;

    public Integer getTenantId() {
        return tenantId;
    }

    public void setTenantId(Integer tenantId) {
        this.tenantId = tenantId;
    }

    @JsonFormat(locale = "zh", timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    public Date getOperateTime() {
        return operateTime;
    }

    public void setOperateTime(Date operateTime) {
        this.operateTime = operateTime;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getSiteId() {
        return siteId;
    }

    public void setSiteId(Integer siteId) {
        this.siteId = siteId;
    }

    public Integer getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(Integer deviceId) {
        this.deviceId = deviceId;
    }

    public Integer getMeteId() {
        return meteId;
    }

    public void setMeteId(Integer meteId) {
        this.meteId = meteId;
    }

    public Byte getMeteKind() {
        return meteKind;
    }

    public void setMeteKind(Byte meteKind) {
        this.meteKind = meteKind;
    }

    public Short getOperateCode() {
        return operateCode;
    }

    public void setOperateCode(Short operateCode) {
        this.operateCode = operateCode;
    }

    public Short getResultCode() {
        return resultCode;
    }

    public void setResultCode(Short resultCode) {
        this.resultCode = resultCode;
    }

    public String getOperateContent() {
        return operateContent;
    }

    public void setOperateContent(String operateContent) {
        this.operateContent = operateContent == null ? null : operateContent.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", tenantId=").append(tenantId);
        sb.append(", operateTime=").append(operateTime);
        sb.append(", userId=").append(userId);
        sb.append(", siteId=").append(siteId);
        sb.append(", deviceId=").append(deviceId);
        sb.append(", meteId=").append(meteId);
        sb.append(", meteKind=").append(meteKind);
        sb.append(", operateCode=").append(operateCode);
        sb.append(", resultCode=").append(resultCode);
        sb.append(", operateContent=").append(operateContent);
        sb.append("]");
        return sb.toString();
    }
}