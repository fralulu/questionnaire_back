package com.infore.common.reqVO;

import javax.validation.constraints.NotNull;

/**
 * Created by xuyao on 15/12/2017.
 */
public class QueryRecordVO extends VideoBaseVO {
//    @NotNull(message = "年月日不能为空")
    private Integer capacity_flag;

    @NotNull(message = "不能为空")
    private Integer ipc_id;

    public Integer getCapacity_flag() {
        return capacity_flag;
    }

    public void setCapacity_flag(Integer capacity_flag) {
        this.capacity_flag = capacity_flag;
    }

    public Integer getIpc_id() {
        return ipc_id;
    }

    public void setIpc_id(Integer ipc_id) {
        this.ipc_id = ipc_id;
    }
}
