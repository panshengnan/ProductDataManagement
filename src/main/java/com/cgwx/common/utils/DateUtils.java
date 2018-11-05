package com.cgwx.common.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by IntelliJ IDEA.
 * User:SunTao
 * Date:2017/2/26
 * Time:15:18
 * Email:suntao2808@163.com
 * Description
 */
public class DateUtils {
    /**
     * yyyyMMddHHmmsssss
     */
    public final static String YY_MM_DD_HH_MM_SS_SSS_PATTERN = "yyMMddHHmmsssss";

    /**
     * 返回自定义格式的当前日期时间字符串
     *
     * @param format 格式规则
     * @return String 返回当前字符串型日期时间
     */
    public static String getCurrentTime(String format) {
        String returnStr = null;
        SimpleDateFormat f = new SimpleDateFormat(format);
        Date date = new Date();
        returnStr = f.format(date);
        return returnStr;
    }

    public static Date stringToDate(String dateString) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = sdf.parse(dateString);
        return date;
    }

    public static Date stringToDateTime(String dateString) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = sdf.parse(dateString);
        return date;
    }
}
