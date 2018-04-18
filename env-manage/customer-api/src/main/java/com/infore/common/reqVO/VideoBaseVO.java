package com.infore.common.reqVO;

import javax.validation.constraints.NotNull;

/**
 * @author xuy
 * @date 2017年11月27日
 */
public class VideoBaseVO {

    @NotNull(message="不能为空")
	private String cmd;
	
	@NotNull(message="不能为空")
	private String sign;
	
	/**
	 * @return the cmd
	 */
	public String getCmd() {
		return cmd;
	}
	/**
	 * @param cmd the cmd to set
	 */
	public void setCmd(String cmd) {
		this.cmd = cmd;
	}
	/**
	 * @return the sign
	 */
	public String getSign() {
		return sign;
	}
	/**
	 * @param sign the sign to set
	 */
	public void setSign(String sign) {
		this.sign = sign;
	}
	
	
}
