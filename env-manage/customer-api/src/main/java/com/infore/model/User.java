package com.infore.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import java.util.Date;

import java.util.List;
import org.apache.htrace.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
/**
 * 实体类对应的数据表为：  sys_user
 * @author
 *
 *  @date:
 */

@ApiModel(value ="用户表")
@JsonInclude(Include.NON_EMPTY)
public class
User {
    @ApiModelProperty(value = "")
    private Integer id;

    @ApiModelProperty(value = "租户id")
    private Integer tenantId;

    @ApiModelProperty(value = "用户名")
    private String userName;

    @ApiModelProperty(value = "密码")
    private String password;

    @ApiModelProperty(value = "手机号")
    private String mobile;

    @ApiModelProperty(value = "邮箱号")
    private String email;

    @ApiModelProperty(value = "是否可登陆(Y/N)")
    private String loginFlag;

    @ApiModelProperty(value = "登出标志")
    private String logoutFlag;

    @ApiModelProperty(value = "强制重新登陆(Y/N)")
    private String forceLogin;

    @ApiModelProperty(value = "登陆时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date loginDate;

    @ApiModelProperty(value = "密码最近修改时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date pwdLastDate;

    @ApiModelProperty(value = "创建人ID")
    private Integer createrId;

    @ApiModelProperty(value = "")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createDate;

    @ApiModelProperty(value = "")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateDate;

    private String tenantName;

    private List<Integer> roleIds;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    public Integer getTenantId() {
        return tenantId;
    }

    public void setTenantId(Integer tenantId) {
        this.tenantId = tenantId;
    }


    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }


    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile == null ? null : mobile.trim();
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }


    public String getLoginFlag() {
        return loginFlag;
    }

    public void setLoginFlag(String loginFlag) {
        this.loginFlag = loginFlag == null ? null : loginFlag.trim();
    }


    public String getLogoutFlag() {
        return logoutFlag;
    }

    public void setLogoutFlag(String logoutFlag) {
        this.logoutFlag = logoutFlag == null ? null : logoutFlag.trim();
    }


    public String getForceLogin() {
        return forceLogin;
    }

    public void setForceLogin(String forceLogin) {
        this.forceLogin = forceLogin == null ? null : forceLogin.trim();
    }

    @JsonFormat(locale = "zh", timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    public Date getLoginDate() {
        return loginDate;
    }

    public void setLoginDate(Date loginDate) {
        this.loginDate = loginDate;
    }

    @JsonFormat(locale = "zh", timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    public Date getPwdLastDate() {
        return pwdLastDate;
    }

    public void setPwdLastDate(Date pwdLastDate) {
        this.pwdLastDate = pwdLastDate;
    }


    public Integer getCreaterId() {
        return createrId;
    }

    public void setCreaterId(Integer createrId) {
        this.createrId = createrId;
    }

    @JsonFormat(locale = "zh", timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    @JsonFormat(locale = "zh", timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", tenantId=").append(tenantId);
        sb.append(", userName=").append(userName);
        sb.append(", password=").append(password);
        sb.append(", mobile=").append(mobile);
        sb.append(", email=").append(email);
        sb.append(", loginFlag=").append(loginFlag);
        sb.append(", logoutFlag=").append(logoutFlag);
        sb.append(", forceLogin=").append(forceLogin);
        sb.append(", loginDate=").append(loginDate);
        sb.append(", pwdLastDate=").append(pwdLastDate);
        sb.append(", createrId=").append(createrId);
        sb.append(", createDate=").append(createDate);
        sb.append(", updateDate=").append(updateDate);
        sb.append("]");
        return sb.toString();
    }

    public String getTenantName() {
        return tenantName;
    }

    public void setTenantName(String tenantName) {
        this.tenantName = tenantName;
    }

    public List<Integer> getRoleIds() {
        return roleIds;
    }

    public void setRoleIds(List<Integer> roleIds) {
        this.roleIds = roleIds;
    }
}