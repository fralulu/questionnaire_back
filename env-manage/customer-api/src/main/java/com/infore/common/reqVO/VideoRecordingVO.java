package com.infore.common.reqVO;

import javax.validation.constraints.NotNull;

/**
 * @author xuy
 * @date 2017年11月24日
 */
public class VideoRecordingVO extends VideoBaseVO {

	@NotNull(message="不能为空")
	private String ipc_id;
	
	@NotNull(message="不能为空")
	private String yyd;
	
    @NotNull(message="不能为空")
    private String vod_store_path;

    @NotNull(message="不能为空")
    private String hls_store_path;
	
	@NotNull(message="不能为空")
	private String vod_url;
	
	@NotNull(message="不能为空")
	private String hls_url;
	
	@NotNull(message="不能为空")
	private Integer period;
	
	@NotNull(message="不能为空")
	private String duration;
	
	@NotNull(message="不能为空")
	private String beg;
	
	@NotNull(message="不能为空")
	private String end;

    public String getIpc_id() {
        return ipc_id;
    }

    public void setIpc_id(String ipc_id) {
        this.ipc_id = ipc_id;
    }

    public String getYyd() {
        return yyd;
    }

    public void setYyd(String yyd) {
        this.yyd = yyd;
    }

    public String getVod_store_path() {
        return vod_store_path;
    }

    public void setVod_store_path(String vod_store_path) {
        this.vod_store_path = vod_store_path;
    }

    public String getHls_store_path() {
        return hls_store_path;
    }

    public void setHls_store_path(String hls_store_path) {
        this.hls_store_path = hls_store_path;
    }

    public String getVod_url() {
        return vod_url;
    }

    public void setVod_url(String vod_url) {
        this.vod_url = vod_url;
    }

    public String getHls_url() {
        return hls_url;
    }

    public void setHls_url(String hls_url) {
        this.hls_url = hls_url;
    }

    public Integer getPeriod() {
        return period;
    }

    public void setPeriod(Integer period) {
        this.period = period;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getBeg() {
        return beg;
    }

    public void setBeg(String beg) {
        this.beg = beg;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }
}
