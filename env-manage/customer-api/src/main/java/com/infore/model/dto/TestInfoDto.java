package com.infore.model.dto;

import com.infore.common.date.DateHourParam;
import com.infore.model.TestInfo;
import java.io.Serializable;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * Created by xuyao on 2017/7/5.
 */
public class TestInfoDto extends TestInfo implements Serializable {

    private DateHourParam time;
    @DateTimeFormat(pattern="yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
    private Date date;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public DateHourParam getTime() {
        return time;
    }

    public void setTime(DateHourParam time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "TestInfoDto{" +
            "time=" + time +
            ", date=" + date +
            '}';
    }
}
