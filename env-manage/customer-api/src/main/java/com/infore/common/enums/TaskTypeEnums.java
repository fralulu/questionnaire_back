package com.infore.common.enums;

/**
 * @author Created by Cai.xu
 * @desc 任务类型枚举类
 * @date 2017/6/29-下午4:28
 */
public enum TaskTypeEnums {
    TASK_TYPE_0("建设项目",0),TASK_TYPE_1("限期治理",1),TASK_TYPE_2("应急监察",2),
    TASK_TYPE_3("日常监察",3),TASK_TYPE_4("许可证监察",4),TASK_TYPE_5("信访监察",5),
    TASK_TYPE_6("专项执法",6);

    private String name;
    private int value;

    // 构造方法
    private TaskTypeEnums(String name, int value) {
        this.name = name;
        this.value = value;
    }

    // 普通方法
    public static String getName(int value) {
        for (TaskTypeEnums c : TaskTypeEnums.values()) {
            if (c.getValue() == value) {
                return c.name;
            }
        }
        return null;
    }

    // get set 方法
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getValue() {
        return value;
    }

    public void setIndex(int value) {
        this.value = value;
    }
}
