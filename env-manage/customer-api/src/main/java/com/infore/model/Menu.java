package com.infore.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.infore.common.constant.Const;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.Date;
import java.util.List;
import javax.validation.constraints.NotNull;
import org.springframework.format.annotation.DateTimeFormat;
/**
 * 实体类对应的数据表为：  sys_menu
 * @author
 *
 *  @date:
 */

@ApiModel(value ="功能菜单")
public class Menu {
    @ApiModelProperty(value = "")
    private Integer id;

    @ApiModelProperty(value = "父级id")
    private Integer pid;

    @ApiModelProperty(value = "资源类型；1：banner位菜单、2：'1'类型下对应的菜单资源、99：数据资源(默认)")
    private Integer type;

    @ApiModelProperty(value = "菜单名")
    @NotNull(message = "不能为空")
    private String name;

    @ApiModelProperty(value = "菜单url")
    private String url;

    @ApiModelProperty(value = "是否显示(Y/N)")
    private String isShow;

    @ApiModelProperty(value = "备注")
    private String remarks;

    @ApiModelProperty(value = "")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createDate;

    @ApiModelProperty(value = "")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateDate;

    private List<Menu> children;

    private String checked= Const.NO;


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


    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }


    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
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

    public List<Menu> getChildren() {
        return children;
    }

    public void setChildren(List<Menu> children) {
        this.children = children;
    }

    public String getChecked() {
        return checked;
    }

    public void setChecked(String checked) {
        this.checked = checked;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", pid=").append(pid);
        sb.append(", type=").append(type);
        sb.append(", name=").append(name);
        sb.append(", url=").append(url);
        sb.append(", isShow=").append(isShow);
        sb.append(", remarks=").append(remarks);
        sb.append(", createDate=").append(createDate);
        sb.append(", updateDate=").append(updateDate);
        sb.append("]");
        return sb.toString();
    }
}