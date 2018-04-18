package com.infore.common.enums;

/**
 * @author Created by Cai.xu
 * @desc 任务状态枚举类
 * @date 2017/6/29-下午4:28
 */
public enum TaskProcessStatusEnums {
    TASK_PROCESS_STATUS_1("下达",1),TASK_PROCESS_STATUS_2("接收",2),
    TASK_PROCESS_STATUS_3("退回",3),TASK_PROCESS_STATUS_4("转派",4),
    TASK_PROCESS_STATUS_5("审核",5),TASK_PROCESS_STATUS_6("完成",6),TASK_PROCESS_STATUS_7("待审核",7);

    private String name;
    private int value;

    // 构造方法
    private TaskProcessStatusEnums(String name, int value) {
        this.name = name;
        this.value = value;
    }

    // 普通方法
    public static String getName(int value) {
        for (TaskProcessStatusEnums c : TaskProcessStatusEnums.values()) {
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
