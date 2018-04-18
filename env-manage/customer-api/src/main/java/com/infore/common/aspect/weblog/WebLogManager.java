package com.infore.common.aspect.weblog;

import java.util.TimerTask;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 日志管理器
 */
public class WebLogManager {

    //日志记录操作延时
    private final int OPERATE_DELAY_TIME = 5;

    //异步操作记录日志的线程池
    private ScheduledThreadPoolExecutor executor = new ScheduledThreadPoolExecutor(10);

    private WebLogManager() {
    }

    public static WebLogManager logManager = new WebLogManager();

    public static WebLogManager me() {
        return logManager;
    }

    public void executeLog(TimerTask task) {
        executor.schedule(task, OPERATE_DELAY_TIME, TimeUnit.MILLISECONDS);
    }
}
