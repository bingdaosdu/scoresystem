package com.gde.integral.service.common.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 积分的日期处理工具类
 *
 * @author ~
 * @date 2019/6/26
 */
public class IntegralDateUtils {

    private static final SimpleDateFormat SIMPLE_DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");
    private static final SimpleDateFormat SIMPLE_DATETIME_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    /**
     * 将字符串格式的日期(yyyy-MM-dd)转为Date
     * @return 返回日期
     */
    private static Date stringParseDate(String dateStr) {
        Date date = null;
        try {
            date = SIMPLE_DATE_FORMAT.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    /**
     * 将字符串格式的日期(yyyy-MM-dd HH:mm:ss)转为Date
     *
     * @param dateStr 日期字符串
     * @return 转换好的日期
     */
    public static Date stringParseDateTime(String dateStr) {
        Date date = null;
        try {
            date = SIMPLE_DATETIME_FORMAT.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    /**
     * 获取某个日期(字符串形式)该月的最后一天
     *
     * @param dateStr 日期字符串
     * @return 日期所属月份最后一天
     */
    public static String getEndDateStrFromDateStr(String dateStr) {
        Date date = stringParseDate(dateStr);
        return getEndDateStrFromDate(date);
    }

    /**
     * 获取某个日期该月的最后一天
     *
     * @param date 某个日期
     * @return 日期所属月份的最后一天
     */
    public static String getEndDateStrFromDate(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
        String dateStr;
        synchronized (SIMPLE_DATE_FORMAT) {
            dateStr = SIMPLE_DATE_FORMAT.format(cal.getTime());
        }
        return dateStr;
    }

    /**
     * 获取某个日期(字符串形式)该月的第一天
     *
     * @param dateStr 日期字符串
     * @return 日期字符串所属月份的第一天
     */
    public static String getStartDateStrFromDateStr(String dateStr) {
        Date date = stringParseDate(dateStr);
        return getStartDateStrFromDate(date);
    }

    /**
     * 获取某个日期该月的第一天
     *
     * @param date 某个日期
     * @return 该日期所属月份的第一天
     */
    public static String getStartDateStrFromDate(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.DAY_OF_MONTH, cal.getMinimum(Calendar.DAY_OF_MONTH));
        String dateStr;
        synchronized (SIMPLE_DATE_FORMAT) {
            dateStr = SIMPLE_DATE_FORMAT.format(cal.getTime());
        }
        return dateStr;
    }

    /**
     * 获取相对今天指定天数的日期
     *
     * @param appoint 指定天数
     * @return 处理好的字符串形式的日期
     */
    public static Date appointDate(int appoint) {
        Calendar appointDayCalendar = Calendar.getInstance();
        appointDayCalendar.add(Calendar.DAY_OF_MONTH, appoint);
        appointDayCalendar.set(appointDayCalendar.get(Calendar.YEAR), appointDayCalendar.get(Calendar.MONTH), appointDayCalendar.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
        return appointDayCalendar.getTime();
    }

    /**
     * 获取相对今天指定天数的日期(字符串形式)
     *
     * @param appoint 指定的天数
     * @return 返回处理好的日期
     */
    public static String appointDateStr(int appoint) {
        String dateStr;
        synchronized (SIMPLE_DATE_FORMAT) {
            dateStr = SIMPLE_DATE_FORMAT.format(appointDate(appoint));
        }
        return dateStr;
    }

    /**
     * 获取相对于某天指定天数的日期(字符串形式)
     *
     * @param appoint 指定天数
     * @param appointTime 指定的日期
     * @return 处理好的字符串形式的日期
     */
    public static String relativeDateAppointDateStr(int appoint, Date appointTime) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(appointTime);
        cal.add(Calendar.DAY_OF_MONTH, appoint);
        String dateStr;
        synchronized (SIMPLE_DATE_FORMAT) {
            dateStr = SIMPLE_DATE_FORMAT.format(cal.getTime());
        }
        return dateStr;
    }

    /**
     * 将日期转换为字符串
     *
     * @param date 日期
     * @return 字符串形式的日期
     */
    public static String dateFormatStr(Date date) {
        String dateStr;
        synchronized (SIMPLE_DATE_FORMAT) {
            dateStr = SIMPLE_DATE_FORMAT.format(date);
        }
        return dateStr;
    }

    /**
     * 获取指定某个月份今天的日期
     *
     * @param appoint 相对于本月的指定某个月
     * @return 返回某个月份的今天的日期
     */
    public static Date appointMonthByToday(int appoint) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH) + appoint);
        return calendar.getTime();
    }

    /**
     * 获取上个月第一天
     * @return 字符串形式
     */
    public static String lastMonthStartDate() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, -1);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        return SIMPLE_DATE_FORMAT.format(calendar.getTime());
    }

    /**
     * 获取本月第一天
     * @return 字符串形式
     */
    public static String thisMonthStartDate() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, 0);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        return SIMPLE_DATE_FORMAT.format(calendar.getTime());
    }

    /**
     * 获取上个月最后一天
     * @return 字符串形式
     */
    public static String lastMonthEndDate() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_MONTH, 0);
        return SIMPLE_DATE_FORMAT.format(calendar.getTime());
    }

}
