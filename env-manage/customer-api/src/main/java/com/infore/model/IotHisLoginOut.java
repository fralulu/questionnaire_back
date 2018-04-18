package com.infore.model;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.NotBlank;
/**
 * 用户登退录表
 * 实体类对应的数据表为：  iot_his_loginout
 * @author Jeff
 * @date 2017-11-09 14:28:47
 */
@ApiModel(value ="IotHisLoginOut")
public class IotHisLoginOut {
    @ApiModelProperty(value = "")
    @NotNull
    private Integer idNum;

    @ApiModelProperty(value = "租户编号")
    @NotNull
    private Integer tenantId;

    @ApiModelProperty(value = "系统用户ID")
    @NotNull
    private Integer userId;

    @ApiModelProperty(value = "登录时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date logintime;

    @ApiModelProperty(value = "退出时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date logoutime;

    public Integer getIdNum() {
        return idNum;
    }

    public void setIdNum(Integer idNum) {
        this.idNum = idNum;
    }

    public Integer getTenantId() {
        return tenantId;
    }

    public void setTenantId(Integer tenantId) {
        this.tenantId = tenantId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @JsonFormat(locale = "zh", timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    public Date getLogintime() {
        return logintime;
    }

    public void setLogintime(Date logintime) {
        this.logintime = logintime;
    }

    @JsonFormat(locale = "zh", timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    public Date getLogoutime() {
        return logoutime;
    }

    public void setLogoutime(Date logoutime) {
        this.logoutime = logoutime;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", idNum=").append(idNum);
        sb.append(", tenantId=").append(tenantId);
        sb.append(", userId=").append(userId);
        sb.append(", logintime=").append(logintime);
        sb.append(", logoutime=").append(logoutime);
        sb.append("]");
        return sb.toString();
    }
}