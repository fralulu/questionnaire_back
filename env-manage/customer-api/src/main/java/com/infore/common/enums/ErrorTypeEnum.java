package com.infore.common.enums;

/**
 * Created by xuyao on 09/01/2018.
 */
public enum ErrorTypeEnum {

    LOGOUT("LOGOUT","用户已退出，重新登录")
    , REPEAT_LOGIN("REPEAT_LOGIN","用户在其他地方登录，返回登录界面")
    , TOKEN_ERROR("TOKEN_ERROR","token错误，重新登录")
    , FORCE_LOGIN("FORCE_LOGIN","强制重新登录");

    private String code;
    private String desc;

    private ErrorTypeEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public String getCode() {
        return code;
    }


    public String getDesc() {
        return desc;
    }

}
