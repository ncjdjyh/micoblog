package com.ncjdjyh.series.feed.util;

import cn.hutool.core.date.DateUtil;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Author: ncjdjyh
 * @FirstInitial: 2019/8/29
 * @Description: ~
 */

public class DateUtils {

    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");


    public static String formatDate(Date date) {
        return sdf.format(date);
    }

    public static Date parseDate(String s) {
        try {
            return sdf.parse(s);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Double parseOffsetDoubleDate(Date date, int offset) {
        DateUtil.offsetDay(date, offset);
        return parseDoubleDate(date);
    }

    public static Double parseDoubleDate(Date date) {
        return (double) date.getTime();
    }

    public static void main(String[] args) {
        int[] s = new int[]{6, 8};
    }
}
