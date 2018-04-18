package com.infore.common.reqVO;

import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.NotNull;

/**
 * Created by xuyao on 2017/11/29.
 */
public class VideoBackVO {

    @NotNull(message = "客户端类别不能为空")
    @ApiModelProperty(value = "客户端类别", required = true, notes = "Streamer:直播类型、VodServer：录像类型")
    private String clientType;

    @NotNull(message = "摄像头id不能为空")
    @ApiModelProperty(value = "摄像头id", required = true)
    private Integer ipcId;

    @NotNull(message = "年月日不能为空")
    @ApiModelProperty(value = "视频年月日：20171211", required = true)
    private String yyd;

    @NotNull(message = "视频的录制时间段不能为空")
    @ApiModelProperty(value = "视频的录制时间段", required = true)
    private Integer period;

    public String getClientType() {
        return clientType;
    }

    public void setClientType(String clientType) {
        this.clientType = clientType;
    }

    public Integer getIpcId() {
        return ipcId;
    }

    public void setIpcId(Integer ipcId) {
        this.ipcId = ipcId;
    }

    public String getYyd() {
        return yyd;
    }

    public void setYyd(String yyd) {
        this.yyd = yyd;
    }

    public Integer getPeriod() {
        return period;
    }

    public void setPeriod(Integer period) {
        this.period = period;
    }
}
