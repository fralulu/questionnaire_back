package com.infore.common.event;

/**
 * 流媒体stream服务器变化事件
 * Created by xuyao on 13/12/2017.
 */
public class StreamServerChangeEvent extends GuavaEvent{

    private String regId;

    public StreamServerChangeEvent(String regId) {
        this.regId = regId;
    }

    public String getRegId() {
        return regId;
    }

    @Override
    public String toString() {
        return "StreamServerChangeEvent{" +
            "regId='" + regId + '\'' +
            '}';
    }
}
