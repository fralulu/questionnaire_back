package com.infore.common.constant;

/**
 * Created by xuyao on 2017/7/12.
 */
public interface Const {

    /**用户登录名参数*/
    String PARAM_LOGIN_NAME = "loginName";

    String YES = "Y";
    String NO = "N";
    String SUCCESS="SUCCESS";
    
    String SEPARATE_COLON=":";

    String VIDEO_CMD_STREAM = "stream";

    /**租户id*/
    String TID = "tid";

    /**
     * jwt常量
     */
    interface Jwt {

        String LOGIN_NAME = "ln";//登录名key:ln
        String IMG_PATH = "img";
        String MOBILE = "mb";
        String USER_NAME = "un";
        String USERID = "uid";
        String FORCE_LOGIN = "fl";
        String LOGOUT_FLAG = "lf";

    }

}
