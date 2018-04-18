package com.infore.model;


import java.io.Serializable;


/**
 * @desc ajax请求的结果返回类。
 * @author Cai.xu
 * @datetime 2016年9月20日 下午4:44:04
 */
public class ResponseDto<T> implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 9111052457885041749L;
	private Boolean success = true;
	private String msg = "成功";
	private T data;
    private String refreshToken;

	public ResponseDto(){
	}

    public ResponseDto(T data){
        this.data = data;
    }

    public ResponseDto(Boolean success, String msg, T data) {
        this.success = success;
        this.msg = msg;
        this.data = data;
    }

    public ResponseDto(boolean failed, String msg) {
        this.success = failed;
        this.msg = msg;
        this.data = null;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }
}