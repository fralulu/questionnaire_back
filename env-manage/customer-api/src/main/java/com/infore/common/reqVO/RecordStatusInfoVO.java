package com.infore.common.reqVO;

import javax.validation.constraints.NotNull;

/**
 * 录制状态信息
 * @author xuy
 * @date 2017年11月27日
 */
public class RecordStatusInfoVO extends VideoBaseVO {
	
	@NotNull(message="不能为空")
	private String ipc_id;
	
	@NotNull(message="不能为空")
	private String status;
	
	/**
	 * @return the ipc_id
	 */
	public String getIpc_id() {
		return ipc_id;
	}
	/**
	 * @param ipc_id the ipc_id to set
	 */
	public void setIpc_id(String ipc_id) {
		this.ipc_id = ipc_id;
	}
	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}
	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}
	
	
}
