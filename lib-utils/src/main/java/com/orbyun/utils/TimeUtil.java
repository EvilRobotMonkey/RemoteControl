package com.orbyun.utils;

import android.text.TextUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class TimeUtil {
    private static TimeUtil instance = new TimeUtil();

    public final String FORMAT_TODAY = "今天 HH:mm";
    public final String FORMAT_YESTERDAY = "昨天 HH:mm";
    public final String FORMAT_THIS_YEAR = "M 月 d 日";
    public final String FORMAT_OTHER_YEAR = "yyyy-M-d";
    public final String FORMAT_YEAR_MONTH = "yyyy 年 M 月";
    public final String FORMAT_FULL = "yyyy-MM-dd HH:mm:ss";

    private final int SECOND_MILLISECONDS = 1000;
    private final int YEAR_BASE = 1900;

    private TimeUtil() {
    }

    public static TimeUtil getInstance() {
        return instance;
    }

    public String getNoticeTime(String time) {
        String times = "";
        if (!TextUtils.isEmpty(time)) {
            String[] sttrs = time.split(" ");
            String time2 = sttrs[1].substring(0, sttrs[1].lastIndexOf(":"));
            times = sttrs[0] + "  " + time2;
        }
        return times;
    }

    // "2011-01-05T15:19:21+00:00" to long
    public long parseStringTolong(String s) {
        long result = 0;
        String s1 = s.replace("T", " ");
        String s2 = s1.replace("+", " ");
        System.out.println(s2);
        SimpleDateFormat sf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            result = sf1.parse(s2).getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return result;
    }

    // 将long型的时间转成xxxx年xx月xx日 星期x
    public String parselongToString(long time) {
        Date date = new Date();
        date.setTime(time);
        int year = date.getYear() + YEAR_BASE;
        int month = date.getMonth() + 1;
        int date2 = date.getDate();
        int day = date.getDay();
        String s = year + "年" + month + "月" + date2 + "日" + " "
                + getDayOfWeek(day);
        return s;
    }

    /**
     * 使用给定的formatter格式化时间
     *
     * @param aSeconds
     * @return
     */
    public String formatTime(long aSeconds, String aFormat) {
        SimpleDateFormat sdf = new SimpleDateFormat(aFormat);
        Date date = new Date();
        date.setTime(aSeconds);
        String formatDate = sdf.format(date);
        return formatDate;
    }

    /**
     * 获取年数字
     *
     * @param aSeconds
     * @return
     */
    public int getYear(long aSeconds) {
        Date date = new Date();
        date.setTime(aSeconds * SECOND_MILLISECONDS);

        int year = date.getYear() + YEAR_BASE;

        return year;
    }

    /**
     * 获取月数字
     *
     * @param aSeconds
     * @return
     */
    public int getMonth(long aSeconds) {
        Date date = new Date();
        date.setTime(aSeconds * SECOND_MILLISECONDS);

        int month = date.getMonth() + 1;

        return month;
    }

    /**
     * 获取日数字
     *
     * @param aSeconds
     * @return
     */
    public int getDate(long aSeconds) {
        Date date = new Date();
        date.setTime(aSeconds * SECOND_MILLISECONDS);

        return date.getDate();
    }

    /**
     * 获取小时数字
     *
     * @param aSeconds
     * @return
     */
    public int getHour(long aSeconds) {
        Date date = new Date();
        date.setTime(aSeconds * SECOND_MILLISECONDS);

        return date.getHours();
    }

    /**
     * 获取分钟数字
     *
     * @param aSeconds
     * @return
     */
    public int getMinute(long aSeconds) {
        Date date = new Date();
        date.setTime(aSeconds * SECOND_MILLISECONDS);

        return date.getMinutes();
    }

    /**
     * 获取秒数字
     *
     * @param aSeconds
     * @return
     */
    public int getSecond(long aSeconds) {
        Date date = new Date();
        date.setTime(aSeconds * SECOND_MILLISECONDS);

        return date.getSeconds();
    }

    /**
     * 格式化时间
     *
     * @param aSeconds
     * @return
     */
    public String getFormattedDateString(long aSeconds) {
        Date date = new Date();
        date.setTime(aSeconds * SECOND_MILLISECONDS);
        String formatter = FORMAT_FULL;
        SimpleDateFormat sdf = new SimpleDateFormat(formatter);
        return sdf.format(date);
    }

    /**
     * 获取当前epoch时间
     *
     * @return
     */
    public long getNowTime() {
        Date current = new Date();
        return current.getTime();
    }

    /**
     * 获取当前epoch时间
     *
     * @return
     */
    public long getNowTicks() {
        return getNowTime() / SECOND_MILLISECONDS;
    }

    public String getDayOfWeek(int mWeek) {
        String days[] = {"星期一", "星期二", "星期三", "星期四", "星期五", "星期六", "星期日"};
        String dayOfWeek = "";
        if (mWeek >= 1 && mWeek <= 7) {
            dayOfWeek = days[mWeek - 1];
        } else if (mWeek == 0) {
            dayOfWeek = days[6];
        }
        return dayOfWeek;
    }

    public void sleep(int time) {
        try {
            Thread.sleep(time);
        } catch (Exception e) {
        }
    }

    /**
     * 检验时间是否过期。 nobody 20120328 需要 把设置时间提前两分钟 来做此判断。
     *
     * @return 真，过期； 假，没有过期。
     */
    public boolean isTimeOverdue(Date setDate) {
        Date convertDate = new Date(setDate.getTime() - 2 * 60 * 1000); // 把设置时间提前两分钟来计算。
        Date nowDate = new Date();
        return convertDate.before(nowDate); // 设置时间 比 现在时间 早 ，表示 时间过期。
    }

    public boolean isTimeOverdue(long setDate) {
        Date convertDate = new Date(setDate - 2 * 60 * 1000); // 把设置时间提前两分钟来计算。
        Date nowDate = new Date();
        return convertDate.before(nowDate);
    }

    /*
     * 转换从服务器获取时间为long 服务器返回日期类型 2012-03-06T11:45:00+08:00
     * 2012-03-06T11:45:00+0800 nobody
     */
    public long parseDate(String time) {

        int index = time.lastIndexOf(":");
        String tt = time.substring(index + 1);
        String dd = time.substring(0, index);
        String date = dd + tt;
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ");
        Date d = null;
        try {
            d = format.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
//			LogUtil.debugE("time", e.toString());
            return System.currentTimeMillis();
        }
        return d.getTime();
    }

    /**
     *
     */
    public String formatDate() {
        SimpleDateFormat sf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sf1.format(new Date());
    }

    /**
     * 班级上传附件使用到
     */
    public String formatLocalFileDate(String time) {
        if (null == time) {
            return null;
        }
        SimpleDateFormat sf1 = new SimpleDateFormat("MM-dd HH:mm");
        return sf1.format(Long.parseLong(time));
    }

    public String getMMDDHHMMTime(String time) {
        try {
            String[] sts = time.split(" ");
            String[] str2s = sts[0].split("-");
            String[] strs = sts[1].split(":");
            return str2s[1] + "/" + str2s[2] + "  " + strs[0] + ":" + strs[1];
        } catch (Exception e) {
            return time;
        }

    }

    // "2011-01-05 19:21:00" to long
    public long parseStringTolong1(String s) {
        long result = 0;

        SimpleDateFormat sf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            result = sf1.parse(s).getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static String getMD(Date date) {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat simdf = new SimpleDateFormat("MM-dd");
        cal.setTime(date);
        return simdf.format(cal.getTime());

    }

    public static String getHM(Date date) {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat simdf = new SimpleDateFormat("HH:mm");
        cal.setTime(date);
        return simdf.format(cal.getTime());
    }

    public static String getWeek(Date date) {
        String[] weekDays = {"星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"};

        Calendar cal = Calendar.getInstance();
        cal.setTime(date);

        int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if (w < 0)
            w = 0;
        return weekDays[w];

    }

}
