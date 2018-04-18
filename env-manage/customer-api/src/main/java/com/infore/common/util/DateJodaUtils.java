package com.infore.common.util;

import org.joda.time.DateTime;
import org.joda.time.DateTime.Property;
import org.joda.time.LocalDate;

/**
 * Created by xuyao on 20/12/2017.
 */
public class DateJodaUtils {


    /**
     * 获取当天日期的integer格式
     * @return
     */
    public static Integer getCurrentDateInteger() {
        LocalDate currentDate = new LocalDate();
        return getDateInteger(currentDate);
    }

    /**
     * 获取日期的integer格式
     * @param sourceDate 源日期
     * @return
     */
    public static Integer getDateInteger(LocalDate sourceDate) {
        return Integer.valueOf(sourceDate.toString().replace("-", ""));
    }

    /**
     * 获取当天日期减去minusDays天数后的之前日期
     * @param minusDays 减去的天数
     * @return 当天日期-minusDays 后的日期整型格式
     */
    public static Integer getCurrentDateMinusDays(int minusDays) {

        return Integer.valueOf(new LocalDate().minusDays(minusDays).toString().replace("-",""));
    }


    public static void main(String[] args) {
        int d = 20171010;
        String s = "20171010";
        DateTime dateTime = new DateTime(2017,10,10,0,0);
//        DateTime dateTime = DateTime.parse(s, DateTimeFormat.longDate());
        Property property=dateTime.dayOfYear();
        System.out.println("==="+dateTime);
        System.out.println("local date=="+dateTime.toLocalDateTime()+"---"+dateTime.toLocalDate()+"==="+dateTime.toLocalTime());


        DateTime dateTime1 = dateTime.minusDays(9);
        String yyd = dateTime1.toLocalDateTime().toString();
        System.out.println("_---"+yyd);
        System.out.println("=====-----"+yyd.replace("-",""));

        LocalDate localDate = new LocalDate(2017, 10, 10);
        System.out.println("****"+localDate);
        LocalDate localDate1=localDate.minusDays(180);
        System.out.println("------"+localDate1.toString());

        LocalDate currentDate = new LocalDate();
        System.out.println("currentDate:"+currentDate);

        System.out.println("*&&**&*&&*"+getCurrentDateMinusDays(10));
    }
}
