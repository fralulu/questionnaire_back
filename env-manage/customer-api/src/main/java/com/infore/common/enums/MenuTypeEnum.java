package com.infore.common.enums;

/**
 * Created by xuyao on 28/12/2017.
 */
public enum MenuTypeEnum {
    TYPE_1(1, "菜单")
    ,TYPE_DEFAULT_99(99, "数据资源默认")
    ;

    private Integer code;
    private String desc;

    MenuTypeEnum(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public Integer getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }
}
