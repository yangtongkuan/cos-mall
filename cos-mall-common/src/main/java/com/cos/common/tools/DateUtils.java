package com.cos.common.tools;

import org.apache.commons.lang.StringUtils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * @Classname DateUtils
 * @Description 日期类处理
 * @Date 2019/4/22 11:03
 * @Created by yangtk
 */
public class DateUtils {

    private static final String SIMPLE_YM = "yyyy-MM";
    private static final String SIMPLE_YMD = "yyyy-MM-dd";
    private static final String SIMPLE_YMD_HM = "yyyy-MM-dd HH:mm";
    private static final String SIMPLE_YMD_HMS = "yyyy-MM-dd HH:mm:ss";
    private static final String SIMPLE_YMDHMS = "yyyy-MM-dd HH:mm:ss";
    private static final String SIMPLE_YMD_HMS_S = "yyyy-MM-dd HH:mm:ss SSS";
    private static final String SIMPLE_YMDHMSS = "yyyy-MM-dd HH:mm:ss SSS";

    public final static ThreadLocal<DateFormat> sdfDateMilliSeconds = new ThreadLocal<DateFormat>() {
        protected DateFormat initialValue() {
            return new SimpleDateFormat("yyyyMMddHHmmssSSS", Locale.SIMPLIFIED_CHINESE);
        }
    };
    public final static ThreadLocal<DateFormat> sdfDateMilli = new ThreadLocal<DateFormat>() {
        protected DateFormat initialValue() {
            return new SimpleDateFormat("yyyyMMddHHmmss", Locale.SIMPLIFIED_CHINESE);
        }
    };
    public final static ThreadLocal<DateFormat> sdfYMDate = new ThreadLocal<DateFormat>() {
        protected DateFormat initialValue() {
            return new SimpleDateFormat(SIMPLE_YM, Locale.SIMPLIFIED_CHINESE);
        }
    };
    public final static ThreadLocal<DateFormat> sdfYMDDate = new ThreadLocal<DateFormat>() {
        protected DateFormat initialValue() {
            return new SimpleDateFormat(SIMPLE_YMD, Locale.SIMPLIFIED_CHINESE);
        }
    };
    public final static ThreadLocal<DateFormat> sdfHMDateTime = new ThreadLocal<DateFormat>() {
        protected DateFormat initialValue() {
            return new SimpleDateFormat(SIMPLE_YMD_HM, Locale.SIMPLIFIED_CHINESE);
        }
    };
    public final static ThreadLocal<DateFormat> sdfHMSDatTime = new ThreadLocal<DateFormat>() {
        protected DateFormat initialValue() {
            return new SimpleDateFormat(SIMPLE_YMD_HMS, Locale.SIMPLIFIED_CHINESE);
        }
    };
    public final static ThreadLocal<DateFormat> sdfHMSHDatTime = new ThreadLocal<DateFormat>() {
        protected DateFormat initialValue() {
            return new SimpleDateFormat(SIMPLE_YMD_HMS_S, Locale.SIMPLIFIED_CHINESE);
        }
    };

    // 获取当前时间戳
    public static Long getCurrentTimeMillis() {
        return System.currentTimeMillis();
    }

    /**
     * from yyyy-MM
     *
     * @param dateStr
     * @return
     */
    public static Date getDateFromYM(String dateStr) {
        // 方式1和2都可以 都不会出现线程安全问题  方式1内存开销更大
        // 方式1
//        return parseDate(dateStr, SIMPLE_YM);
        // 方式2
        return convertToLocalSimpleDate(sdfYMDate, dateStr);
    }

    /**
     * from yyyy-MM-dd hh:mm:ss
     *
     * @param dateStr
     * @return
     */
    public static Date getDateFromYMD(String dateStr) {
//        return parseDate(dateStr, SIMPLE_YMD);
        return convertToLocalSimpleDate(sdfYMDDate, dateStr);
    }

    /**
     * from yyyy-MM-dd hh:mm
     *
     * @param dateStr
     * @return
     */
    public static Date getDateFromYMDHM(String dateStr) {
//        return parseDate(dateStr, SIMPLE_YMD_HM);
        return convertToLocalSimpleDate(sdfHMDateTime, dateStr);
    }

    /**
     * from yyyy-MM-dd hh:mm:ss
     *
     * @param dateStr
     * @return
     */
    public static Date getDateFromYMHMS(String dateStr) {
//        return parseDate(dateStr, SIMPLE_YMD_HMS);
        return convertToLocalSimpleDate(sdfHMSDatTime, dateStr);
    }

    /**
     * 线程安全方式实现
     *
     * @param dateFormatThreadLocal
     * @param dateStr
     * @return
     */
    private static Date convertToLocalSimpleDate(ThreadLocal<DateFormat> dateFormatThreadLocal, String dateStr) {
        if (dateFormatThreadLocal == null || StringUtils.isEmpty(dateStr)) {
            return null;
        }
        try {
            return dateFormatThreadLocal.get().parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 未选择时间类型则自由匹配
     *
     * @param dateStr
     * @return
     */
    public static Date convertToDate(String dateStr) {
        if (StringUtils.isEmpty(dateStr)) {
            return null;
        }
        dateStr = dateStr.trim();
        if (StringUtils.isEmpty(dateStr)) {
            return null;
        }
        if (dateStr.matches("^\\d{4}-\\d{1,2}$")) {
            return parseDate(dateStr, SIMPLE_YM);
        } else if (dateStr.matches("^\\d{4}-\\d{1,2}-\\d{1,2}$")) {
            return parseDate(dateStr, SIMPLE_YMD);
        } else if (dateStr.matches("^\\d{4}-\\d{1,2}-\\d{1,2} {1}\\d{1,2}:\\d{1,2}$")) {
            return parseDate(dateStr, SIMPLE_YMD_HM);
        } else if (dateStr.matches("^\\d{4}-\\d{1,2}-\\d{1,2} {1}\\d{1,2}:\\d{1,2}:\\d{1,2}$")) {
            return parseDate(dateStr, SIMPLE_YMD_HMS);
        } else {
            System.out.println("未知的时间类型" + dateStr);
            return null;
        }
    }

    /**
     * datestr to  date 时间类型转换（线程是安全的，内存开销比较大  不推荐使用）
     *
     * @param dateStr
     * @param format
     * @return
     */
    public static Date parseDate(String dateStr, String format) {
        if (StringUtils.isNotEmpty(dateStr)) {
            try {
                return new SimpleDateFormat(format).parse(dateStr);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public static void main(String[] args) {

        System.out.println(DateUtils.getDateFromYMHMS("2019-04-19 14:14:14"));

    }

}