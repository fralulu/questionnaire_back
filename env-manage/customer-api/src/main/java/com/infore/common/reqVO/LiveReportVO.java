package com.infore.common.reqVO;

import javax.validation.constraints.NotNull;

/**
 * @author xuy
 * @date 2017年11月28日
 */
public class LiveReportVO extends VideoBaseVO {
	@NotNull(message="不能为空")
	private String reg_id;
	
	@NotNull(message="不能为空")
	private String ipc_id;
	
	@NotNull(message="不能为空")
	private String rtmp_url;
	
	@NotNull(message="不能为空")
	private String hls_url;

	@NotNull(message = "不能为空")
    private String status;

    public String getReg_id() {
        return reg_id;
    }

    public void setReg_id(String reg_id) {
        this.reg_id = reg_id;
    }

    public String getIpc_id() {
        return ipc_id;
    }

    public void setIpc_id(String ipc_id) {
        this.ipc_id = ipc_id;
    }

    public String getRtmp_url() {
        return rtmp_url;
    }

    public void setRtmp_url(String rtmp_url) {
        this.rtmp_url = rtmp_url;
    }

    public String getHls_url() {
        return hls_url;
    }

    public void setHls_url(String hls_url) {
        this.hls_url = hls_url;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
