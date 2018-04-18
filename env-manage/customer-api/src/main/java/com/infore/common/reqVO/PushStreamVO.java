package com.infore.common.reqVO;

/**
 * Created by xuyao on 2017/12/8.
 */
public class PushStreamVO extends VideoBaseVO {

    private String ipc_id;

    private String rtsp_url;

    private String tasks;

    private String opt;

    public String getIpc_id() {
        return ipc_id;
    }

    public void setIpc_id(String ipc_id) {
        this.ipc_id = ipc_id;
    }

    public String getRtsp_url() {
        return rtsp_url;
    }

    public void setRtsp_url(String rtsp_url) {
        this.rtsp_url = rtsp_url;
    }

    public String getTasks() {
        return tasks;
    }

    public void setTasks(String tasks) {
        this.tasks = tasks;
    }

    public String getOpt() {
        return opt;
    }

    public void setOpt(String opt) {
        this.opt = opt;
    }
}
