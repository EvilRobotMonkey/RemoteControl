package com.orbyun.utils;

import android.text.TextUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 作者：Liq on 2018/10/31 11:06
 * <p>
 * 邮箱：18513215341@139.com
 */
public class DateUtil {
    /*
     *  8位数字字符串转 年月日
     */
    public static String Str2Date(String time) {
        if (TextUtils.isEmpty(time)) {
            return "--";
        }
        if (time.equals("0"))
            return "--";
        Date date = null;
        try {
            date = new SimpleDateFormat("yyyyMMdd").parse(time);
            String now = new SimpleDateFormat("yyyy年MM月dd日").format(date);
            return now;
        } catch (ParseException e) {
            e.printStackTrace();
            return time;
        }
    }
}
