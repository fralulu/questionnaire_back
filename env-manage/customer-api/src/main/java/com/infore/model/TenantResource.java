package com.infore.model;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
/**
 * 实体类对应的数据表为：  sys_tenant_resource
 * @author 
 *
 *  @date: 
 */

 @ApiModel(value ="租户定制资源表")
public class TenantResource {
    @ApiModelProperty(value = "")
    private Integer id;

    @ApiModelProperty(value = "父级id")
    private Integer pid;

    @ApiModelProperty(value = "所有父id")
    private String pids;

    @ApiModelProperty(value = "菜单名")
    private String name;

    @ApiModelProperty(value = "资源访问url")
    private String ulr;

    @ApiModelProperty(value = "是否显示(Y/N)")
    private String isShow;

    @ApiModelProperty(value = "备注")
    private String remarks;

    @ApiModelProperty(value = "创建人ID")
    private Integer createrId;

    @ApiModelProperty(value = "")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createDate;

    @ApiModelProperty(value = "")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateDate;

    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    
    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    
    public String getPids() {
        return pids;
    }

    public void setPids(String pids) {
        this.pids = pids == null ? null : pids.trim();
    }

    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    
    public String getUlr() {
        return ulr;
    }

    public void setUlr(String ulr) {
        this.ulr = ulr == null ? null : ulr.trim();
    }

    
    public String getIsShow() {
        return isShow;
    }

    public void setIsShow(String isShow) {
        this.isShow = isShow == null ? null : isShow.trim();
    }

    
    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks == null ? null : remarks.trim();
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
        sb.append(", pid=").append(pid);
        sb.append(", pids=").append(pids);
        sb.append(", name=").append(name);
        sb.append(", ulr=").append(ulr);
        sb.append(", isShow=").append(isShow);
        sb.append(", remarks=").append(remarks);
        sb.append(", createrId=").append(createrId);
        sb.append(", createDate=").append(createDate);
        sb.append(", updateDate=").append(updateDate);
        sb.append("]");
        return sb.toString();
    }
}