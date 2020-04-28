package com.zza.jpaa.util;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {

    public static String DEFAULT_DATE_PATTERN = "yyyy-MM-dd";

    public static String YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";

    /**
     * 获得默认的 date pattern
     */
    public static String getDatePattern() {
        return YYYY_MM_DD_HH_MM_SS;
    }

    /**
     * 获得日期时间pattern
     *
     * @return
     */
    public static String getDateTimePattern() {
        return YYYY_MM_DD_HH_MM_SS;
    }

    /**
     * 获取当前时间
     *
     * @return
     */
    public static Date getNowDate() {
        return new Date();
    }

    /**
     * 返回预设Format的当前日期字符串
     */
    public static String getToday() {
        final Date today = new Date();
        return format(today);
    }



    /**
     * 使用预设Format格式化Date成字符串
     *
     * @param date
     * @return
     */
    public static String format(final Date date) {
        return date == null ? "" : format(date, getDatePattern());
    }

    /**
     * 使用参数Format格式化Date成字符串
     *
     * @param date
     * @param pattern
     * @return
     */
    public static String format(final Date date, final String pattern) {
        return date == null ? "" : new SimpleDateFormat(pattern).format(date);
    }


    public static Date yesterDayBefore(){
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE,-1);
        return calendar.getTime();
    }
}
