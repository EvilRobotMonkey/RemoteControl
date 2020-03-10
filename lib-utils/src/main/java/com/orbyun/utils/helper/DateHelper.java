package com.orbyun.utils.helper;


import android.util.Log;

import com.orbyun.utils.LOG;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @discription:日期处理类
 */
public class DateHelper {
    private static final String TAG = "DateHelper";
    private static DateHelper instance = new DateHelper();

    private DateHelper() {
    }

    public static DateHelper getInstance() {
        return instance;
    }

    public Timestamp getCurrentTimestamp() {
        String date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
                .format(Calendar.getInstance().getTime());
        Timestamp time = Timestamp.valueOf(date);
        return time;
    }

    /**
     * 获取当前的时间，精准到毫秒
     */
    public String getCurrentAccurateTime() {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS")
                .format(Calendar.getInstance().getTime());
    }

    /**
     * 将String类型日期转化成java.sql.Date类型"2003-01-01"格式
     *
     * @param str
     * @return Date
     */
    public java.sql.Date stringToSqlDate(String str) {
        if (str == null) {
            return null;
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            date = sdf.parse(str);
        } catch (Exception e) {
            return null;
        }
        return new java.sql.Date(date.getTime());
    }


    public String getCurrentTimestampForString() {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Calendar
                .getInstance().getTime());
    }

    public String getCurrentTimestampByFormat(String format) {
        return new SimpleDateFormat(format).format(Calendar
                .getInstance().getTime());
    }

    /**
     * 将String类型日期转化成java.sql.Timestamp类型"2003-01-01"格式
     *
     * @param str
     * @return Timestamp
     */
    public Timestamp stringToTimestamp(String str) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = null;
        Timestamp timestamp = null;
        try {
            date = sdf.parse(str);
            timestamp = new Timestamp(date.getTime());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            return timestamp;
        }

    }


    public Timestamp stringToTimestamp(String str, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        Date date = null;
        try {
            date = sdf.parse(str);
        } catch (Exception e) {
            return null;
        }
        return new Timestamp(date.getTime());
    }

    /**
     * 将Date类型日期转化成String类型"任意"格式
     * java.sql.Date,java.sql.Timestamp类型是java.util.Date类型的子类
     *
     * @param date
     * @param format String "2003-01-01"格式 "yyyy年M月d日" "yyyy-MM-dd HH:mm:ss"格式
     * @return String
     */
    public String dateToString(Date date, String format) {
        if (date == null || format == null) {
            return null;
        }
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        String str = sdf.format(date);
        return str;
    }

    public Long dateStringtoString(String dateString, String format) {
        Long time = 0l;
        try {
            Date date = new SimpleDateFormat(format).parse(dateString);
            time = date.getTime();
        } catch (Exception e) {
            LOG.i(TAG, e.getMessage());
        } finally {
            return time;
        }
    }

    /**
     * 将String类型日期转化成java.utl.Date类型"2003-01-01"格式
     *
     * @param str
     * @param format
     * @return Date
     */
    public Date stringToUtilDate(String str, String format) {
        if (str == null || format == null) {
            return null;
        }
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        Date date = null;
        try {
            date = sdf.parse(str);
        } catch (Exception e) {
        }
        return date;
    }

    /**
     * 将字符串转化为时间格式 string to string
     *
     * @param str
     * @return String
     */
    public String toDateString(String str, String oldformat,
                               String newformat) {
        return dateToString(stringToUtilDate(str, oldformat), newformat);
    }

    // 是否月初
    public boolean isMonthBegin() {
        String time = getCurrentTimestampForString();
        if ("01".equals(getCurrentDay()))
            return true;
        else
            return false;
    }

    // 当前日
    public String getCurrentDay() {
        String time = getCurrentTimestampForString();
        return time.substring(8, 10);
    }

    // 当前月
    public String getCurrentMonth() {
        String time = getCurrentTimestampForString();
        return time.substring(5, 7);
    }

    // 当前年
    public String getCurrentYear() {
        String time = getCurrentTimestampForString();
        return time.substring(0, 4);
    }

    // 当前日期
    public String getCurrentDate() {
        String time = getCurrentTimestampForString();
        return time.substring(0, 10);
    }

    // 当前时间
    public String getCurrentTime() {
        String time = getCurrentTimestampForString();
        return time.substring(11, 19);
    }

    // 当前小时
    public String getCurrentHour() {
        String time = getCurrentTimestampForString();
        return time.substring(11, 13);
    }

    // 当前分钟
    public String getCurrentMin() {
        String time = getCurrentTimestampForString();
        return time.substring(14, 16);
    }

    // 小时
    public String getHour(String time) {
        return time.substring(11, 13);
    }

    // 分钟
    public String getMin(String time) {
        return time.substring(14, 16);
    }

    public String getYesterdayDate() {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c = Calendar.getInstance();
        int d = c.get(Calendar.DAY_OF_MONTH);
        --d;
        c.set(Calendar.DAY_OF_MONTH, d);
        String value = df.format(c.getTime());
        return value;
    }

    public String getNextDate() {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c = Calendar.getInstance();
        int d = c.get(Calendar.DAY_OF_MONTH);
        ++d;
        c.set(Calendar.DAY_OF_MONTH, d);
        String value = df.format(c.getTime());
        return value;
    }

    public String getLastYearDate() {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c = Calendar.getInstance();
        int y = c.get(Calendar.YEAR);
        --y;
        c.set(Calendar.YEAR, y);
        String value = df.format(c.getTime());
        return value;
    }

    /**
     * 求得从某天开始，过了几年几月几日几时几分几秒后，日期是多少 几年几月几日几时几分几秒可以为负数 特殊用法：当传负数时，可以获取以前的日期 如：
     * modifyDate(Calendar.getInstance().getTime(),0 ,0,-1,0,0,0) 获取昨天的日期值
     *
     * @param date
     * @param year
     * @param month
     * @param day
     * @param hour
     * @param min
     * @param sec
     * @return Date
     */
    public Date modifyDate(Date date, int year,
                           int month, int day, int hour, int min, int sec) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.YEAR, year);
        cal.add(Calendar.MONTH, month);
        cal.add(Calendar.DATE, day);
        cal.add(Calendar.HOUR, hour);
        cal.add(Calendar.MINUTE, min);
        cal.add(Calendar.SECOND, sec);
        return cal.getTime();
    }

    // / <summary>
    // / 获取某日期是星期几
    // / </summary>
    // / <param name="y">年</param>
    // / <param name="m">月</param>
    // / <param name="d">日</param>
    // / <remarks>
    // / 基姆拉尔森计算公式
    // / W= (d+2*m+3*(m+1)/5+y+y/4-y/100+y/400) mod 7
    // / 在公式中d表示日期中的日数，m表示月份数，y表示年数。
    // / 注意：在公式中有个与其他公式不同的地方：
    // / 把一月和二月看成是上一年的十三月和十四月，例：如果是2004-1-10则换算成：2003-13-10来代入公式计算。
    // / 代码如下：
    // / //y－年，m－月，d－日期
    // / </remarks>
    // / <returns>星期X</returns>
    public int CaculateWeekDay(int y, int m, int d) {
        if (m == 1) {
            m = 13;
            y -= 1;
        } else if (m == 2) {
            m = 14;
            y -= 1;
        }

        int week = (d + 2 * m + 3 * (m + 1) / 5 + y + y / 4 - y / 100 + y / 400) % 7;

        // string weekstr = "";
        // switch (week)
        // {
        // case 0: weekstr = "星期一"; break;
        // case 1: weekstr = "星期二"; break;
        // case 2: weekstr = "星期三"; break;
        // case 3: weekstr = "星期四"; break;
        // case 4: weekstr = "星期五"; break;
        // case 5: weekstr = "星期六"; break;
        // case 6: weekstr = "星期日"; break;
        // }

        // return weekstr;
        return week + 1;
    }

    // / <summary>
    // / 获取指定日期是一年中的第几周
    // / </summary>
    // / <param name="year">年</param>
    // / <param name="month">月</param>
    // / <param name="day">日</param>
    // / <param name="cnFlag">中国标志（周一是第一天），外国（周日是第一天）</param>
    // / <returns>周的数值</returns>
    public String GetfirstWeeksunday(int year) {
        // 元月元日星期几
        int d = CaculateWeekDay(year - 1, 13, 1);
        String firssunday = year + "-" + "01" + "-" + "07";
        switch (d) {

            case 1:

                break;
            case 2:
                firssunday = year + "-" + "01" + "-" + "06";
                break;
            case 3:
                firssunday = year + "-" + "01" + "-" + "05";
                break;
            case 4:
                firssunday = year + "-" + "01" + "-" + "04";
                break;
            case 5:

                firssunday = year + "-" + "01" + "-" + "03";
                break;
            case 6:
                firssunday = year + "-" + "01" + "-" + "02";
                break;
            case 7:
                firssunday = year + "-" + "01" + "-" + "01";
                break;

        }

        // 今年的第几天
        // int days = DateTime.Parse(year.ToString() + "-" + month.ToString() +
        // "-" + day.ToString()).DayOfYear;
        // //int days = DateTime.Parse(year.ToString() + "-" + month.ToString()
        // + "-" + day.ToString()).DayOfYear - DateTime.Parse(year.ToString() +
        // "-01-01").DayOfYear;
        //
        // int w = days / 7 + 1; //除数
        //
        // int dd = days % 7; //余数
        //
        // int count = cnFlag == true ? 7 : 6; //中外标志
        //
        // if ((d + dd) > count)
        // {
        // w++; //补值
        // }
        //
        // //MessageBox.Show("元月1日星期" + d + "\r\n" + "几周：" + w + "\r\n" + "余数" +
        // dd);
        //
        return firssunday;
    }

    /**
     * 返回某年某周星期日的日期
     */
    public String getSundayDate(int year, int week, String format) {
        Date date = stringToUtilDate(GetfirstWeeksunday(year), format);
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, 7 * (week - 1));
        // String date2 = new SimpleDateFormat(format).format(cal.getTime());
        return dateToString(cal.getTime(), "yyyy-MM-dd");
    }

    /**
     * 返回某年某周星期一的日期
     *
     * @param year
     * @param week
     * @param format
     * @return
     */
    public String getMondayDate(int year, int week, String format) {
        Date date = stringToUtilDate(GetfirstWeeksunday(year), format);
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, 7 * (week - 1));
        cal.add(Calendar.DATE, -6);
        // System.out.println(dateToString(cal.getTime(),"yyyy-MM-dd"));
        // String date2 = new SimpleDateFormat(format).format(cal.getTime());
        return dateToString(cal.getTime(), "yyyy-MM-dd");
    }

    /**
     * 返回某星期周一到周天的日期以逗号分割
     *
     * @param year
     * @param week
     * @param format
     * @return
     */
    public String getEveryDateOfWeek(int year, int week, String format) {
        Date date = stringToUtilDate(GetfirstWeeksunday(year), format);
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        // cal.add(Calendar.DATE,));
        cal.add(Calendar.DATE, 7 * (week - 1));
        String date7 = new SimpleDateFormat(format).format(cal.getTime());
        cal.add(Calendar.DATE, -6);
        String date1 = new SimpleDateFormat(format).format(cal.getTime());
        cal.add(Calendar.DATE, 1);
        String date2 = new SimpleDateFormat(format).format(cal.getTime());

        cal.add(Calendar.DATE, 1);
        String date3 = new SimpleDateFormat(format).format(cal.getTime());

        cal.add(Calendar.DATE, 1);
        String date4 = new SimpleDateFormat(format).format(cal.getTime());

        cal.add(Calendar.DATE, 1);
        String date5 = new SimpleDateFormat(format).format(cal.getTime());

        cal.add(Calendar.DATE, 1);
        String date6 = new SimpleDateFormat(format).format(cal.getTime());

        // return date2;
        return date1 + "," + date2 + "," + date3 + "," + date4 + "," + date5
                + "," + date6 + "," + date7;
    }

    public String getcurrenYear() {
        String date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
                .format(Calendar.getInstance().getTime());
        return date.substring(0, 4);

    }

    /**
     * 返回当前是一年中的第 几周
     *
     * @return
     */
    public String getCurrentWeeksnum() {

        // String sql = "select to_char(sysdate,'fmww') as dd from dual ";
        // List list = baseJdbcDao.queryForListBySql(sql);
        // String weeknu="1";
        // if(list!=null&&list.size()>0){
        // weeknu = ((Map)list.get(0)).get("dd").toString();
        //
        // }
        String weeknu = "1";
        Calendar rightNow = Calendar.getInstance();
        weeknu = Integer.toString(rightNow.get(Calendar.WEEK_OF_YEAR));
        return weeknu;
    }

    // 获取当月最后一天
    public String getLastDayOfMonth() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar lastDate = Calendar.getInstance();
        lastDate.set(Calendar.DATE, 1);// 设为当前月的1号
        lastDate.add(Calendar.MONTH, 1);// 加一个月，变为下月的1号
        lastDate.add(Calendar.DATE, -1);// 减去一天，变为当月最后一天

        return sdf.format(lastDate.getTime());
    }

    // get the last month
    public String getLastMonth() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar lastDate = Calendar.getInstance();
        lastDate.add(Calendar.MONTH, -1);// 加一个月，变为下月的1号
        return sdf.format(lastDate.getTime());
    }

    public boolean sameDateMinute(Date d1, Date d2) {
        if (null == d1 || null == d2)
            return false;
        try {
            SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
            String format1 = format.format(d1.getTime());
            String format2 = format.format(d2.getTime());
            if (format1.equals(format2)) {
                return true;
            } else {
                return false;
            }

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}