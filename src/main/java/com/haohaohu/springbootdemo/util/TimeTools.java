package com.haohaohu.springbootdemo.util;


import cn.hutool.core.date.DateUtil;
import cn.hutool.core.date.LocalDateTimeUtil;
import cn.hutool.core.util.ObjectUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

public class TimeTools {

    private static final Logger logger = LoggerFactory.getLogger(TimeTools.class);

    private static GregorianCalendar gCalendar = null;

    private static SimpleDateFormat dateFormat = null;

    public static Date getDateFromString(String str, String pattern) throws ParseException {
        return new SimpleDateFormat(pattern).parse(str);
    }

    public static boolean isNowInTimeRange(String s1, String s2) {
        SimpleDateFormat df = new SimpleDateFormat("HH:mm");
        return isInTimeRange(df.format(new Date()), s1, s2);
    }

    public static boolean isInTimeRange(String s, String s1, String s2) {
        int set = Integer.valueOf(s.replaceAll(":", ""));
        int begin = Integer.valueOf(s1.replaceAll(":", ""));
        int end = Integer.valueOf(s2.replaceAll(":", ""));
        if (begin > end) {
            return set < end || set >= begin;
        } else {
            return set >= begin && set < end;
        }
    }

    // 时间格式数组
    private static final String[] formatArray = {"yyyyMMdd", "yyyy-MM-dd", "yyyy-MM-dd HH:mm",
            "yyyy-MM-dd HH:mm:ss", "yy-MM-dd HH:mm", "yyyyMMdd HH:mm", "yyyy-MM-dd HH", "yyyy-MM-dd HH:mm:ss.SSS", "yyyyMMddHHmmss"};

    // 检测一个时间格式是否为合法格式
    private static boolean isRightFormat(String formatStr) {
        boolean isRight = false;
        int j = formatArray.length;
        for (int i = 0; i < j; i++) {
            if (formatArray[i].equalsIgnoreCase(formatStr)) {
                isRight = true;
                break;
            }
        }
        return isRight;
    }

    public static Long yyyyMMddHHmmss2Long(String timeStr) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        Long time = -1L;
        try {
            Date date = sdf.parse(timeStr);
            time = date.getTime() / 1000;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return time;
    }

    public static int long2Str8(Long time) {
        String date = getTime_yyyyMMdd_HHmmss(time * 1000);
        date = date.replace("-", "").substring(0, 8);
        return Integer.valueOf(date);
    }

    public static Long getLongMilliSecondFromStrDate(String strDate, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);

        long millSeconds = new GregorianCalendar().getTimeInMillis();
        try {
            millSeconds = sdf.parse(strDate).getTime();
        } catch (Exception e) {
            // logger.error("---------get seconds error:"+e.getMessage());
        }
        return new Long(millSeconds);
    }

    /**
     * @param strDate
     * @return 根据字符串时间得到相应毫秒数
     */
    public static Long getLongMilliSecondFrom_HHMMDD(String strDate) {
        return getLongMilliSecondFromStrDate(strDate, "yyyy-MM-dd");
    }

    /**
     * @param strDate
     * @return 根据字符串时间得到相应秒数
     */
    public static Long getLongMilliSecondFrom_HHMMDDHHmmss(String strDate) {
        return getLongMilliSecondFromStrDate(strDate, "yyyy-MM-dd HH:mm:ss");
    }

    public static String checkMounth(String endDateSelect) {
        String year = endDateSelect.split("-")[0];
        String mounth = endDateSelect.split("-")[1];
        if (mounth.equals("1") || mounth.equals("3") || mounth.equals("5") || mounth.equals("7")
                || mounth.equals("8") || mounth.equals("10") || mounth.equals("12")) {
            return "-31";
        } else if (mounth.equals("4") || mounth.equals("6") || mounth.equals("9")
                || mounth.equals("11")) {
            return "-30";
        } else {
            Integer yInteger = Integer.parseInt(year);
            // if(year.equals("2012")||year.equals("2016")||year.equals("2020")||
            // year.equals("2024")||year.equals("2028")||year.equals("2008"))
            if ((yInteger % 4 == 0 && yInteger % 100 != 0) || yInteger % 400 == 0)// 润年判断
            {
                return "-29";
            } else {
                return "-28";
            }
        }

    }

    /**
     * @return 得到当前时间的秒数(long型)
     */
    public static long getlongMilliSeconds() {
        return new Date().getTime();
    }

    /**
     * @return 得到当前时间的毫秒数(Long型)
     */
    public static Long getLongMilliSeconds() {
        long d = new Date().getTime();
        return new Long(d / 1000);
    }

    public static Long getLongSeconds() {
        long d = new Date().getTime();
        return new Long(d);
    }

    /**
     * @param seconds 秒
     * @return 格式化后的时间字符串 yyyy-MM-dd HH:mm:ss
     */
    public static String getTimeSecond_yyyyMMdd_HHmmss(Long seconds) {
        if (seconds == null) {
            return "";
        }
        return secondsToDateStr(toMilliSecond(seconds), "yyyy-MM-dd HH:mm:ss");
    }

    /**
     * @param milliSeconds 毫秒数
     * @return 格式化后的时间字符串 yyyy-MM-dd HH:mm:ss
     */
    public static String getTime_yyyyMMdd_HHmmss(Long milliSeconds) {

        return secondsToDateStr(milliSeconds, "yyyy-MM-dd HH:mm:ss");
    }

    public static String getTime_MMdd_HHmmss(Long milliSeconds) {

        return secondsToDateStr(milliSeconds, "MM-dd HH:mm:ss");
    }

    /**
     * @param milliSeconds 毫秒数
     * @return 格式化后的时间字符串 yyyy-MM-dd HH:mm:ss
     */
    public static String getTime_MMdd_HHmm(Long milliSeconds) {

        return secondsToDateStr(milliSeconds, "yyyy-MM-dd HH:mm").substring(5);
    }

    public static String getTime_MMdd_HHmmss_SSS(Long milliSeconds) {
        return secondsToDateStr(milliSeconds, "yyyy-MM-dd HH:mm:ss.SSS");
    }

    /**
     * @param milliSeconds 毫秒数
     * @return 格式化后的时间字符串 yyyy-MM-dd HH:mm:ss
     */
    public static String getTime_yyyyMMdd_HHmm(Long milliSeconds) {
        if (milliSeconds == null) {
            return "";
        }
        return secondsToDateStr(milliSeconds, "yyyy-MM-dd HH:mm");
    }

    /**
     * @param milliSeconds 毫秒数
     * @return 格式化后的时间字符串 yy-MM-dd HH:mm
     */
    public static String getTime_yyMMdd_HHmm(Long milliSeconds) {

        return secondsToDateStr(milliSeconds, "yy-MM-dd HH:mm");
    }

    public static String getTime_yyyyMMdd_HH(Long milliSeconds) {
        return secondsToDateStr(milliSeconds, "yyyy-MM-dd HH");
    }

    /**
     * @param milliSeconds 毫秒数
     * @return 格式化后的时间字符串 yyyy-MM-dd
     */
    public static String getTimeStr_yyyy_MM_dd(Long milliSeconds) {

        return secondsToDateStr(milliSeconds, "yyyy-MM-dd");
    }

    /**
     * @return 当前日期的字符串 yyyy-MM-dd 格式
     */
    public static String getDate_YY_MM_DD() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return dateFormat.format(new Date());

    }

    /**
     * @return 当前日期的字符串 yyyy/M/d 格式
     */
    public static String getDate_YY_M_D() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/M/d");
        return dateFormat.format(new Date());

    }

    public static Date str2Date(String date) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Date ddate = new Date();
        try {
            ddate = df.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return ddate;
    }

    /**
     * @param milliSeconds
     * @param formatStr
     * @return
     * @todo 将数值时间格式化为字符串
     */
    public static String secondsToDateStr(Long milliSeconds, String formatStr) {

        if (milliSeconds == null) {
            return "";
        }
        if (isRightFormat(formatStr) == false) {
            formatStr = "yyyy-MM-dd HH:mm:ss";
        }
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat(formatStr);

            if (milliSeconds.longValue() > 1) {
                GregorianCalendar gCalendar = new GregorianCalendar();
                gCalendar.setTimeInMillis(milliSeconds.longValue());
                return dateFormat.format(gCalendar.getTime());
            } else {
                return "";
            }
        } catch (Exception e) {
            return "";
        }

    }

    // 得到明天时间
    public static String getTomorrowday() {
        Calendar c = Calendar.getInstance();
        c.add(Calendar.DAY_OF_MONTH, +1);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String mDateTime = formatter.format(c.getTime());
        String strStart = mDateTime.substring(0, 19);//
        return strStart;
    }

    public static String getTwoLaterday() {
        Calendar c = Calendar.getInstance();
        c.add(Calendar.DAY_OF_MONTH, +2);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String mDateTime = formatter.format(c.getTime());
        String strStart = mDateTime.substring(0, 19);//
        return strStart;
    }

    public static String getThirdLaterday() {
        Calendar c = Calendar.getInstance();
        c.add(Calendar.DAY_OF_MONTH, +3);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String mDateTime = formatter.format(c.getTime());
        String strStart = mDateTime.substring(0, 19);//
        return strStart;
    }

    public static String getForthLaterday() {
        Calendar c = Calendar.getInstance();
        c.add(Calendar.DAY_OF_MONTH, +4);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String mDateTime = formatter.format(c.getTime());
        String strStart = mDateTime.substring(0, 19);//
        return strStart;
    }

    public static String getFiveLaterday() {
        Calendar c = Calendar.getInstance();
        c.add(Calendar.DAY_OF_MONTH, +5);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String mDateTime = formatter.format(c.getTime());
        String strStart = mDateTime.substring(0, 19);//
        return strStart;
    }

    public static String getSixLaterday() {
        Calendar c = Calendar.getInstance();
        c.add(Calendar.DAY_OF_MONTH, +6);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String mDateTime = formatter.format(c.getTime());
        String strStart = mDateTime.substring(0, 19);//
        return strStart;
    }

    public static String getSevenLaterday() {
        Calendar c = Calendar.getInstance();
        c.add(Calendar.DAY_OF_MONTH, +7);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String mDateTime = formatter.format(c.getTime());
        String strStart = mDateTime.substring(0, 19);//
        return strStart;
    }

    public static String getCoutomday(int days) {
        Calendar c = Calendar.getInstance();
        c.add(Calendar.DAY_OF_MONTH, +days);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String mDateTime = formatter.format(c.getTime());
        String strStart = mDateTime.substring(0, 10);//
        return strStart;
    }

    public static Long getStrDateToSecond(String strDate) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        long millSeconds = (new GregorianCalendar()).getTimeInMillis();
        try {
            millSeconds = sdf.parse(strDate).getTime();
        } catch (Exception e) {

        }
        return new Long(millSeconds / 1000);
    }

    public static Long getStrDateToSecond2(String strDate) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        long millSeconds = (new GregorianCalendar()).getTimeInMillis();
        try {
            millSeconds = sdf.parse(strDate).getTime();
        } catch (Exception e) {

        }
        return new Long(millSeconds);
    }

    // 转换
    public static String secondsToDateStr(Long seconds) {

        String second = "1";
        if (seconds.equals("") && seconds == null) {
            seconds = new Long(1);
        }
        try {

            dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            gCalendar = new GregorianCalendar();
            gCalendar.setTimeInMillis(seconds.longValue() * 1000);
            second = dateFormat.format(gCalendar.getTime());
        } catch (Exception e) {
            second = "1";

        }
        return second;
    }

    public static String MillsecondsToDateStr(Long seconds) {

        String second = "1";
        if (seconds.equals("") && seconds == null) {
            seconds = new Long(1);
        }
        try {

            dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            gCalendar = new GregorianCalendar();
            gCalendar.setTimeInMillis(seconds.longValue());
            second = dateFormat.format(gCalendar.getTime());
        } catch (Exception e) {
            second = "1";

        }
        return second;
    }

    public static Long getDatestart() {
        Calendar c = Calendar.getInstance();
        c.add(Calendar.DAY_OF_MONTH, -2);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String mDateTime = formatter.format(c.getTime());
        String strStart = mDateTime.substring(0, 19);//
        Long State = getLongMilliSecondFrom_HHMMDD(strStart);
        return State;
    }

    public static Long getDateend() {
        Calendar c = Calendar.getInstance();
        c.add(Calendar.DAY_OF_MONTH, +2);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String mDateTime = formatter.format(c.getTime());
        String strStart = mDateTime.substring(0, 19);//
        Long State = getLongMilliSecondFrom_HHMMDD(strStart);
        return State;
    }

    public static Long getToDayBeginTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String today = sdf.format(new Date());
        today = today.substring(0, 10) + " 00:00:00";
        return getStrDateToSecond(today);
    }

    /*
     * 获得传入日期的零点秒值
     * miliseconds毫秒数
     */
    public static Long getBeginTime(Long miliseconds) {
        Date date = new Date(miliseconds);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String time = formatter.format(date);
        String day = time.substring(0, 10) + " 00:00:00";
        return getStrDateToSecond(day);
    }

    public static Long getMonthBeginTime(Long miliseconds) {
        Date date = new Date(miliseconds);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String time = formatter.format(date);
        String day = time.substring(0, 8) + "01 00:00:00";
        return getStrDateToSecond(day);
    }

    /**
     * 计算时长
     *
     * @param seconds （秒）
     * @return HH:mm:ss exp："00:00:00"
     */
    public static String getShiChangString(Long seconds) {
        StringBuffer shichang = null;
        if (seconds != null) {
            if (seconds > 0) {
                shichang = new StringBuffer();
                int hour = (int) (seconds / 3600);
                if (hour > 0) {
                    if (hour > 9) {
                        shichang.append(hour + ":");
                    } else {
                        shichang.append("0" + hour + ":");
                    }
                } else {
                    shichang.append("00:");
                }
                int minute = (int) (seconds % 3600) / 60;
                if (minute > 0) {
                    if (minute > 9) {
                        shichang.append(minute + ":");
                    } else {
                        shichang.append("0" + minute + ":");
                    }
                } else {
                    shichang.append("00:");
                }
                int second = (int) (seconds % 3600) % 60;
                if (second > 0) {
                    if (second > 9) {
                        shichang.append(second);
                    } else {
                        shichang.append("0" + second);
                    }
                } else {
                    shichang.append("00");
                }
            } else {
                return "00:00:00";
            }
        } else {
            return "";
        }
        return shichang.toString();
    }

    /*
     * 返回录音下载的时间格式：2013-03-13_145012
     */
    public static String getRecordTime(long time) {
        String datestr = secondsToDateStr(time);
        datestr = datestr.replace(" ", "_").replaceAll(":", "");
        return datestr;
    }

    private static String dtime = "";

    // 获得精确到日的当前日期
    public static String getdate1() {
        dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        gCalendar = new GregorianCalendar();
        dtime = dateFormat.format(gCalendar.getTime());
        return dtime;
    }

    // 获得精确到秒的当前日期
    public static String getCurrentTime_yyyyMMddHHmmss() {
        dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        gCalendar = new GregorianCalendar();
        dtime = dateFormat.format(gCalendar.getTime());
        return dtime;
    }

    public static long getlongtime()// 获得当前时间的毫秒数
    {
        Date nows = new Date();
        long d = 0;
        d = nows.getTime();
        return d;
    }

    public static long getSeconds() {
        return new Long((new GregorianCalendar().getTimeInMillis()) / 1000);
    }

    public static String getdate()// 获得精确到日的当前日期 用于oracle
    {
        dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        gCalendar = new GregorianCalendar();
        dtime = dateFormat.format(gCalendar.getTime());
        return dtime;
    }

    public static String gettime()// 获得精确到秒的当前日期 用于oracle
    {
        try {
            dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            gCalendar = new GregorianCalendar();
            dtime = dateFormat.format(gCalendar.getTime());
        } catch (Exception ex) {

        }
        return dtime;
    }

    public static String dateFormat(Date myDate) {
        dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        gCalendar = new GregorianCalendar();
        dtime = dateFormat.format(myDate);
        return dtime;

    }

    public static String dateFormat(Date myDate, String strFormat) {
        dateFormat = new SimpleDateFormat(strFormat);
        gCalendar = new GregorianCalendar();
        dtime = dateFormat.format(myDate);
        return dtime;

    }

    public static String dateFormat() {
        dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        gCalendar = new GregorianCalendar();
        dtime = dateFormat.format(gCalendar.getTime());
        return dtime;

    }

    public static String dateFormat_yyyyMMddHHmmss() {
        dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        gCalendar = new GregorianCalendar();
        dtime = dateFormat.format(gCalendar.getTime());
        return dtime;

    }

    /**
     * @param date 毫秒
     * @return
     */
    public static String dateFormat_yyyyMMddHHmmss(long date) {
        long dateMillis = toMilliSecond(date);
        dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        dtime = dateFormat.format(new Date(dateMillis));
        return dtime;

    }

    /**
     * 功能：指定日期的基础上增减日期（年、月、日、时、分、秒）
     *
     * @param millis //指定日期
     * @param amount //增量（正数或负数）
     * @param field  //年、月、日、时、分、秒
     * @return long //返回毫秒级
     */
    public static long getMillis(long millis, int amount, int field) {
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(millis);
        switch (field) {
            case 10:// 加年
                cal.add(Calendar.YEAR, amount);
                break;
            case 20:// 加月
                cal.add(Calendar.MONTH, amount);
                break;
            case 30:// 加日
                cal.add(Calendar.DATE, amount);
                break;
            case 40:// 加时
                cal.add(Calendar.HOUR, amount);
                break;
            case 50:// 加分
                cal.add(Calendar.MINUTE, amount);
                break;
            case 60:// 加秒
                cal.add(Calendar.SECOND, amount);
            default:// 默认加天
                cal.add(Calendar.DATE, amount);
        }
        return cal.getTime().getTime();
    }

    public static boolean compareStringTime(String begindateStr, String enddateStr) {
        if (begindateStr == null || "".equals(begindateStr)) {
            return false;
        }
        if (enddateStr == null || "".equals(enddateStr)) {
            return false;
        }
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
        Date dateA = null;
        Date dateB = null;
        try {
            dateA = sdf1.parse(begindateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        try {
            dateB = sdf1.parse(enddateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        // begindateStr 小于 enddateStr 返回false
        // begindateStr 大于 enddateStr 返回true
        return !dateA.before(dateB);
    }

    public static String formatMs(long ms, int flag) {// 将毫秒数换算成x天x时x分x秒x毫秒
        int ss = 1000;
        int mi = ss * 60;
        int hh = mi * 60;
        int dd = hh * 24;

        long day = 0;
        long hour = 0;
        long minute1 = 0;
        long minute2 = 0;
        long second = 0;
        switch (flag) {
            case 0:// 将毫秒转为秒
                minute1 = (ms / ss) / 60;
                minute2 = (ms / ss) % 60;
                return minute1 + "分" + minute2 + "秒";
            default:
                break;
        }

        return "";
    }

    public static String getDateStrC(Date date) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日");
        return format.format(date);
    }


    public static long dateFormat(String isFlag) {
        dateFormat = new SimpleDateFormat("yyMMddHHmm");
        gCalendar = new GregorianCalendar();
        dtime = dateFormat.format(gCalendar.getTime());
        return (new Long(dtime)).longValue();
    }

    // ***************************************************************************

    public static Date getDateFromStr(String strDate) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        // Calendar   calendar   =   new   GregorianCalendar();
        Date date = null;
        try {
            date = sdf.parse(strDate);
        } catch (Exception e) {

        }

        return date;
    }

    public static Date getDateFromMonthStr(String strDate) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
        Date date = null;
        try {
            date = sdf.parse(strDate);
        } catch (Exception e) {

        }

        return date;
    }


    public static Long getStrDateToSecond(String strDate, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);

        long millSeconds = new GregorianCalendar().getTimeInMillis();
        try {
            millSeconds = sdf.parse(strDate).getTime();
        } catch (Exception e) {
            logger.error("---------get seconds error:" + e.getMessage());
        }
        return new Long(millSeconds / 1000);
    }


    public static Long getSecondsFromStrDate(String strDate) {

        Long seconds = null;
        try {
            Date date = getDateFromStr(strDate);

            seconds = new Long(date.getTime() / 1000);
        } catch (Exception e) {

        }

        return seconds;

    }


    @SuppressWarnings("deprecation")
    public static Long getLongSecond(int hh, int mm, int ss) {
        Date rightNow = new Date();

        if (hh > 0 && hh < 23) {
            rightNow.setHours(hh);
        }
        if (mm > 0 && mm < 60) {
            rightNow.setMinutes(mm);
        }
        if (ss > 0 && ss < 60) {
            rightNow.setSeconds(ss);
        }

        return new Long(rightNow.getTime() / 1000);
    }

    /**
     * @todo 返回当前时间 yyyy-MM-dd HH:mm:ss
     * @return 2007-4-12
     */
    // public static Date getRightDate()//获得精确到秒的当前日期
    // {
    // // dateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    // // gCalendar=new GregorianCalendar();
    // // dtime=dateFormat.format(gCalendar.getTime());
    // // System.out.print(new Date(dtime));
    // return new Date();
    // }


    /**
     * @return 本年开始一天的秒数 2007-4-12
     */
    @SuppressWarnings("deprecation")
    public static long getYearStartSeconds() {
        // Calendar rightNow = Calendar.getInstance();
        Date rightNow = new Date();
        rightNow.setMonth(0);
        rightNow.setDate(1);
        rightNow.setHours(0);
        rightNow.setMinutes(0);
        rightNow.setSeconds(0);
        // System.out.print(rightNow);
        return rightNow.getTime() / 1000;
    }

    /**
     * @返回当前月第一天据1970-01-01 00:00:00 的秒数 2007-4-12
     */
    @SuppressWarnings("deprecation")
    public static long getMonthStartSeconds() {
        // Calendar rightNow = Calendar.getInstance();
        Date rightNow = new Date();
        rightNow.setDate(1);
        rightNow.setHours(0);
        rightNow.setMinutes(0);
        rightNow.setSeconds(0);
        // System.out.println(rightNow);
        // System.out.println(rightNow.getTime()/1000);
        return rightNow.getTime() / 1000;
    }

    /**
     * @返回当上月第一天据1970-01-01 00:00:00 的秒数 2007-4-12
     */
    @SuppressWarnings("deprecation")
    public static long getLastMonthStartSeconds() {
        Calendar rightNow = Calendar.getInstance(TimeZone.getTimeZone("GMT+8"));
        rightNow.setTimeInMillis(getMonthStartSeconds() * 1000);
        rightNow.set(Calendar.MONTH, rightNow.get(Calendar.MONTH) - 1);
        return rightNow.getTimeInMillis() / 1000;
    }

    /**
     * @return 本周一的秒数 2007-4-12
     */
    @SuppressWarnings("deprecation")
    public static long getWeekStartSeconds() {
        Date rightNow = new Date();
        rightNow.setHours(0);
        rightNow.setMinutes(0);
        rightNow.setSeconds(0);

        int days = rightNow.getDay();
        if (days == 0) {
            days = 7;
        }
        long reValue = rightNow.getTime() - (days - 1) * 24 * 60 * 60 * 1000;

        // System.out.println(reValue);
        // System.out.println(new Date(reValue));
        // System.out.println("1176048000");
        // System.out.println(reValue/1000-1176048000);
        // System.out.println(24*60*60*7);
        return reValue / 1000;
    }

    public static String getTimeYYYYMMDDHHMMSS() {
        String s = gettime();
        s = s.replaceAll("-", "").replaceAll(":", "").replaceAll(" ", "").trim();
        return s;
    }

    /**
     * 返回订单入场及离场时间,精确到分钟
     *
     * @param btime 入场时间
     * @return
     */
    public static Long getOrderTime(Long btime) {
        //Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("GMT+8"));
        Long ntime = System.currentTimeMillis() / 1000;
		/*if(btime!=null){//离场时，如果结束时间与入场时间小于60秒，不计时间，否则判断秒是否大于30秒，是就加一分钟
			if(ntime-btime<100){
				return btime;
			}else {
				calendar.setTimeInMillis(ntime*1000);
				if(calendar.get(Calendar.SECOND)>30)
					calendar.set(Calendar.MINUTE, calendar.get(Calendar.MINUTE)+1);
				calendar.set(Calendar.SECOND, 0);
			}
		}else {//入场时，当秒>30时延时1分钟
//			if(calendar.get(Calendar.SECOND)>30)
			calendar.setTimeInMillis(ntime*1000);
//			if(calendar.get(Calendar.SECOND)>30)
//				calendar.set(Calendar.MINUTE, calendar.get(Calendar.MINUTE)+1);
			calendar.set(Calendar.SECOND, 0);
		}
		ntime = calendar.getTimeInMillis()/1000;*/
        return ntime;
    }

    public static String getDateStr(Long time) {
        String t = getTime_yyyyMMdd_HHmmss(time * 1000);
        t = t.replace(" ", "").replace(":", "").replace("-", "");
        return t;
    }

    public static String getDateMillisStr(Long milliSeconds) {
        String t = getTime_MMdd_HHmmss_SSS(milliSeconds);
        t = t.replace(" ", "").replace(":", "").replace("-", "").replace(".", "");
        return t;
    }

//    public static void main(String[] args) {
////		String dateMillisStr = getDateMillisStr(System.currentTimeMillis());
////		System.out.println(dateMillisStr);
////        System.out.println(getLongMilliSecondFrom_HHMMDDTHHmmss(null));
////        System.out.println(isNowInTimeRange( "02:00", "1:59") + "");
//        long time = System.currentTimeMillis();
//        Double date = Double.valueOf(time + "");
//        System.out.println(TimeTools.getTime_yyyyMMdd_HHmmss(date.longValue()));
//    }


    /**
     * @return 当前日期的字符串 yyyymmdd格式
     */
    public static String getDate_YYMMDD(Long milliSeconds) {

        return secondsToDateStr(milliSeconds, "yyyyMMdd");
    }

    /**
     * @param milliSeconds 毫秒数
     * @return 格式化后的时间字符串 yyyy-MM-ddTHH:mm:ss
     */
    public static String getTime_yyyyMMddHHmmss(Long milliSeconds) {
        return secondsToDateStr(milliSeconds, "yyyy-MM-dd HH:mm:ss");
    }

    /**
     * @param milliSeconds 毫秒数
     * @return 格式化后的时间字符串 yyyy-MM-ddTHH:mm:ss
     */
    public static String getTime_yyyyMMddTHHmmss(Long milliSeconds) {
        return secondsToDateStr(milliSeconds, "yyyy-MM-dd HH:mm:ss").replace(" ", "T");
    }

    /**
     * @param strDate
     * @return 根据字符串时间得到相应毫秒数
     */
    public static Long getLongMilliSecondFrom_HHMMDDTHHmmss(String strDate) {
        return getLongMilliSecondFromStrDate(strDate.replace("T", " "), "yyyy-MM-dd HH:mm:ss") / 1000;
    }

    /**
     * 获当前日期
     *
     * @param dateFormat
     * @return String
     */
    public static String getCurrentDate(String dateFormat) {
        return new SimpleDateFormat(dateFormat).format(new Date());
    }


    /**
     * 判断时间戳为秒还是毫秒
     *
     * @param n 时间戳
     */
    public static boolean isSecond(long n) {
        int numberOfDigits = String.valueOf(n).length();
        return numberOfDigits == 10;
    }

    public static boolean isMilliSecond(long n) {
        int numberOfDigits = String.valueOf(n).length();
        return numberOfDigits == 13;
    }

    /**
     * 转换时间戳,不确定是秒还是毫秒
     *
     * @param n 时间戳
     */
    public static long toSecond(long n) {
        if (!isSecond(n)) {
            n = n / 1000;
        }
        return n;
    }

    /**
     * 转换时间毫秒戳,不确定是秒还是毫秒
     *
     * @param n 时间戳
     */
    public static long toMilliSecond(long n) {
        if (isSecond(n)) {
            n = n * 1000;
        }
        return n;
    }

    /**
     * 将时间戳格式化输出
     *
     * @param seconds 秒
     */
    public static String getTimeString(long seconds) {
        return LocalDateTimeUtil.of(TimeTools.toMilliSecond(seconds), TimeZone.getTimeZone("Asia/Shanghai")).toString();
    }

    /**
     * @param beginTime 毫秒
     * @param endTime   毫秒
     * @return
     */
    public static boolean isSameDay(long beginTime, long endTime) {
        Long beginTime1 = TimeTools.getBeginTime(beginTime);
        Long endTime1 = TimeTools.getBeginTime(endTime);
        return ObjectUtil.equal(beginTime1, endTime1);
    }

    public static Long minuteToSecond(int startMinute) {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.DAY_OF_MONTH, 1);
        cal.set(Calendar.HOUR_OF_DAY, startMinute / 60);
        cal.set(Calendar.MINUTE, startMinute % 60);
        cal.set(Calendar.SECOND, 0);
        return cal.getTimeInMillis() / 1000;
    }

    public static Long minuteToSecond(int startMinute, int add) {
        Calendar cal = Calendar.getInstance();
        if (add > 0) {
            cal.set(Calendar.DAY_OF_MONTH, 2);
        } else {
            cal.set(Calendar.DAY_OF_MONTH, 1);
        }
        cal.set(Calendar.HOUR_OF_DAY, startMinute / 60);
        cal.set(Calendar.MINUTE, startMinute % 60);
        cal.set(Calendar.SECOND, 0);
        return cal.getTimeInMillis() / 1000;
    }

    //public static void main(String[] args) {
    //    Long second = minuteToSecond(60);
    //    System.out.println(second + "");
    //    Long second1 = minuteToSecond(60, 1);
    //    System.out.println(second1 + "");
    //}

    /**
     * @param currentTime 秒
     * @param beginTime   秒
     * @param endTime     秒
     * @return
     */
    public static boolean inBetween(Long currentTime, Long beginTime, Long endTime) {
        if (currentTime == null || beginTime == null) {
            return false;
        }
        if (endTime == null) {
            return true;
        }

        return currentTime > beginTime && currentTime < endTime;
    }

    public static boolean inBetweenIgnore(Long currentTime, Long beginTime, Long endTime) {
        if (currentTime == null || beginTime == null) {
            return false;
        }
        if (endTime == null) {
            return true;
        }
        long currentTimeSecond = TimeTools.toSecond(currentTime);
        long beginTimeSecond = TimeTools.toSecond(beginTime);
        long endTimeSecond = TimeTools.toSecond(endTime);

        return currentTimeSecond > beginTimeSecond && currentTimeSecond < endTimeSecond;
    }

    public static String dateDiff(long startTime, long endTime) {
        long nd = 24 * 60 * 60;//一天的秒数
        long nh = 60 * 60;//一小时的秒数
        long nm = 60;//一分钟的秒数
        long ns = 1;//一秒钟的秒数
        long diff;
        //计算两个时间的毫秒时间差异
        diff = endTime - startTime;
        long day = diff / nd;
        long hour = diff % nd / nh;
        long min = diff % nd % nh / nm;
        long sec = diff % nd % nh % nm / ns;
        String show = "";
        if (day != 0) {
            show += "" + day + "天";
        }
        if (hour != 0) {
            show += "" + hour + "小时";
        }
        if (min != 0) {
            show += "" + min + "分";
        }
        if (sec != 0) {
            show += "" + sec + "秒";
        }
        return show;
    }

    public static void main(String[] args) {
        //long time = System.currentTimeMillis() / 1000;
        //long time2 = time + 1024 * 400 + 61;
        //String s = dateDiff(time, time2);
        //System.out.println(s);
        long endDate = 1678094070000L;
        int monthNum = 2;

        long time = DateUtil.endOfDay(DateUtil.endOfMonth(DateUtil.offsetMonth(new Date(endDate), monthNum))).getTime();
        String s = TimeTools.getTime_yyyyMMddHHmmss(time);
        System.out.println("s:" + s);
    }

    public static boolean isLastDayOfMonth(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DATE, (calendar.get(Calendar.DATE) + 1));
        if (calendar.get(Calendar.DAY_OF_MONTH) == 1) {
            return true;
        }
        return false;
    }
}
