package com.infore.common.enums;

/**
 * @author Created by Cai.xu
 * @desc 任务状态枚举类
 * @date 2017/6/29-下午4:28
 */
public enum TaskStatusEnums {
    TASK_STATUS_0("新建",0),TASK_STATUS_1("待审核",1),TASK_STATUS_2("退回",2),
    TASK_STATUS_3("办理中",3),TASK_STATUS_4("已过期",4),TASK_STATUS_5("完成",5),
    TASK_STATUS_6("不通过",6),TASK_STATUS_7("通过",7);

    private String name;
    private int value;

    // 构造方法
    private TaskStatusEnums(String name, int value) {
        this.name = name;
        this.value = value;
    }

    // 普通方法
    public static String getName(int value) {
        for (TaskStatusEnums c : TaskStatusEnums.values()) {
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
