package com.infore.common.reqVO;

import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * 流媒体服务器注册请求对象
 * @author xuy
 * @date 2017年11月22日
 */
public class StreamRegisterVO extends VideoBaseVO {
	@NotNull(message="不能为空")
	private String ip;
	
	@NotNull(message="不能为空")
	private String port;

    @NotEmpty(message="不能为空")
	private String service_url;

    @NotEmpty(message="不能为空")
	private String vod_url;
	
	@NotNull(message="不能为空")
	private String type;
	
	/**
	 * @return the ip
	 */
	public String getIp() {
		return ip;
	}
	/**
	 * @param ip the ip to set
	 */
	public void setIp(String ip) {
		this.ip = ip;
	}
	/**
	 * @return the port
	 */
	public String getPort() {
		return port;
	}
	/**
	 * @param port the port to set
	 */
	public void setPort(String port) {
		this.port = port;
	}
	/**
	 * @return the service_url
	 */
	public String getService_url() {
		return service_url;
	}
	/**
	 * @param service_url the service_url to set
	 */
	public void setService_url(String service_url) {
		this.service_url = service_url;
	}
	/**
	 * @return the vod_url
	 */
	public String getVod_url() {
		return vod_url;
	}
	/**
	 * @param vod_url the vod_url to set
	 */
	public void setVod_url(String vod_url) {
		this.vod_url = vod_url;
	}
	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}
	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}
}
