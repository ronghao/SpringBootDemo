package com.haohaohu.springbootdemo.util;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.HexUtil;

import java.util.Calendar;
import java.util.Date;

/**
 * @author haohao(ronghao3508 gmail.com) on 2022/5/10 9:42
 * @version v1.0
 */
public class HexTool {

    /**
     * int 转 十六进制
     *
     * @param n   数字
     * @param len 长度
     * @return
     */
    public static String intToHex(int n, int len) {
        if (n == 0) {
            return replenish0("", len);
        }
        StringBuffer s = new StringBuffer();
        String a;
        char[] b = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
        while (n != 0) {
            s = s.append(b[n % 16]);
            n = n / 16;
        }
        s = replenish0(s, len);
        a = s.reverse().toString();
        return a;
    }

    /**
     * int 转 十六进制
     *
     * @param n   数字
     * @param len 长度
     * @return
     */
    public static String intToHexBig(int n, int len) {
        if (n == 0) {
            return replenish0("", len);
        }
        StringBuffer s = new StringBuffer();
        String a;
        char[] b = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
        while (n != 0) {
            s = s.append(b[n % 16]);
            n = n / 16;
        }
        s = replenish0(s, len);
        a = s.reverse().toString();
        return a;
    }

    public static StringBuffer replenish0(StringBuffer data, int length) {
        length = length * 2;
        int length1 = data.length();
        int need = length - length1;
        if (need > 0) {
            for (int i = 0; i < need; i++) {
                data.append("0", 0, 1);
            }
        }

        return data;
    }

    public static String replenish0(String data, int length) {
        return replenish0(data, length, false);
    }

    public static String replenish0(String data, int length, boolean isEnd) {
        length = length * 2;
        int length1 = data.length();
        int need = length - length1;
        if (need > 0) {
            for (int i = 0; i < need; i++) {
                if (isEnd) {
                    data = data + "0";
                } else {
                    data = "0" + data;
                }
            }
        }

        return data;
    }

    /**
     * 10进制转16进制高位在前低位在后
     *
     * @param number
     * @return
     */
    public static String lowHigh(int number, int length, boolean isEnd) {
        String lowHigh = lowHigh1(number, length);
        return replenish0(lowHigh, length, isEnd);
    }

    public static String lowHigh2(int number, int length, boolean isEnd) {
        String lowHigh = lowHigh2(number, length);
        return replenish0(lowHigh, length, isEnd);
    }

    /**
     * 10进制转16进制高位在前地位在后
     *
     * @param number
     * @return
     */
    public static String lowHigh1(int number, int length) {
        if (length == 4) {
            byte[] bytes2 = unlong2H4bytes(number);
            return byte2Hex(bytes2);
        } else {
            return String.format("%0" + (length * 2) + "x", number).toUpperCase();
        }
    }

    public static String lowHigh2(int number, int length) {
        if (length == 2) {
            byte[] bytes2 = unlong2H2bytes(number);
            return byte2Hex(bytes2);
        } else {
            return String.format("%0" + (length * 2) + "x", number).toUpperCase();
        }
    }

    /**
     * 4字节 10进制转16进制高位在前地位在后
     * 低位在前，高位在后
     *
     * @param n
     * @return
     */
    public static byte[] unlong2H4bytes(long n) {
        byte[] b = new byte[4];
        b[0] = (byte) (n & 0xff);
        b[1] = (byte) (n >> 8 & 0xff);
        b[2] = (byte) (n >> 16 & 0xff);
        b[3] = (byte) (n >> 24 & 0xff);
        return b;
    }

    /**
     * 2字节 10进制转16进制高位在前地位在后
     * 低位在前，高位在后
     *
     * @param n
     * @return
     */
    public static byte[] unlong2H2bytes(long n) {
        byte[] b = new byte[2];
        b[0] = (byte) (n & 0xff);
        b[1] = (byte) (n >> 8 & 0xff);
        return b;
    }

    /**
     * byte数组转为十六进制字符串
     *
     * @param bytes
     * @return
     */
    public static String byte2Hex(byte[] bytes) {
        StringBuffer hexString = new StringBuffer();
        for (int i = 0; i < bytes.length; i++) {
            String hex = Integer.toHexString(0xff & bytes[i]);
            if (hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }
        return hexString.toString();
    }

    /**
     * 10进制转16进制高位在前地位在后
     *
     * @param number
     * @return
     */
    public static String lowHigh(int number) {
        int var1 = 1;
        int var2 = number >> 8;
        int var3 = number & 255;
        String var4 = Integer.toHexString(var2);
        String var5 = Integer.toHexString(var3);
        if (var4.length() > 2) {
            do {
                if (var1 > 1) {
                    var2 >>= 8;
                }
                var4 = Integer.toHexString(var2 >> 8);
                var5 = var5 + Integer.toHexString(var2 & 255);
                ++var1;
            } while (var4.length() > 2);
        }
        if (var4.length() < 2) {
            var4 = "0" + var4;
        }
        if (var5.length() < 2) {
            var5 = "0" + var5;
        }
        return var5 + var4;
    }

    public static int hexToInt(String index) {
        int length = index.length();
        if (length % 2 != 0) {
            index = "0" + index;
            length += 1;
        }
        String tmpIndex = "";
        for (int i = 0; i < length / 2; i++) {
            tmpIndex = index.substring(i * 2, i * 2 + 2) + tmpIndex;
        }
        return HexUtil.hexToInt(tmpIndex);
    }

    public static Long toDateInt(String string) {
        return toDate(string).getTime() / 1000;
    }

    public static Date toDate(String string) {
        int milliseconds1 = HexUtil.hexToInt(string.substring(0, 2));
        int milliseconds2 = HexUtil.hexToInt(string.substring(2, 4));
        int milliseconds = milliseconds1 + milliseconds2 * 256;
        int seconds = 0;
        int minutes = HexUtil.hexToInt(string.substring(4, 6)) & 0x3F;
        int hours = HexUtil.hexToInt(string.substring(6, 8)) & 0x1F;
        int days = HexUtil.hexToInt(string.substring(8, 10)) & 0x1F;
        int months = (HexUtil.hexToInt(string.substring(10, 12)) - 1) & 0x0F;
        int year = HexUtil.hexToInt(string.substring(12, 14)) & 0x7F;
        return toDate(year, months, days, hours, minutes, seconds, milliseconds);
    }

    public static Date toDate(int years, int months, int days, int hours, int minutes, int seconds, int milliseconds) {
        final Calendar aTime = Calendar.getInstance();
        aTime.set(Calendar.MILLISECOND, milliseconds);
        aTime.set(Calendar.SECOND, seconds);
        aTime.set(Calendar.MINUTE, minutes);
        aTime.set(Calendar.HOUR_OF_DAY, hours);
        aTime.set(Calendar.DAY_OF_MONTH, days);
        aTime.set(Calendar.MONTH, months);
        aTime.set(Calendar.YEAR, years + 2000);
        return aTime.getTime();
    }

    public static String getTimeStr(long seconds) {
        Date data = new Date(seconds * 1000);
        return DateUtil.format(data, "yyyyMMddHHmm");
    }

    @Deprecated
    public static String toCP56Time2a1(long milliSeconds) {
        Date data = new Date(milliSeconds);
        Calendar aTime = Calendar.getInstance();
        aTime.setTime(data);

        int milliSecond = aTime.get(Calendar.MILLISECOND);
        int second = aTime.get(Calendar.SECOND);
        milliSecond = second * 1000 + milliSecond;
        int minute = aTime.get(Calendar.MINUTE);
        int hours = aTime.get(Calendar.HOUR_OF_DAY);
        int day = aTime.get(Calendar.DAY_OF_MONTH);
        int month = aTime.get(Calendar.MONTH) + 1;
        int year = aTime.get(Calendar.YEAR);

        String milliSecondStr = HexUtil.toHex(milliSecond % 256);
        String secondStr = HexUtil.toHex(milliSecond / 256);
        String minuteStr = HexUtil.toHex(minute);
        String hoursStr = HexUtil.toHex(hours);
        String dayStr = HexUtil.toHex(day);
        String monthStr = HexUtil.toHex(month);
        String yearStr = HexUtil.toHex(year - 2000);

        String totalTime = HexTool.replenish0(milliSecondStr, 1)
                + HexTool.replenish0(secondStr, 1)
                + HexTool.replenish0(minuteStr, 1)
                + HexTool.replenish0(hoursStr, 1)
                + HexTool.replenish0(dayStr, 1)
                + HexTool.replenish0(monthStr, 1)
                + HexTool.replenish0(yearStr, 1);
        return totalTime.toUpperCase();
    }

    public static String toCP56Time2a(long milliSeconds) {
        Date date = new Date(milliSeconds);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        StringBuilder builder = new StringBuilder();
        String milliSecond = String.format("%04X", (calendar.get(Calendar.SECOND) * 1000) + calendar.get(Calendar.MILLISECOND));
        builder.append(milliSecond.substring(2, 4));
        builder.append(milliSecond.substring(0, 2));
        builder.append(String.format("%02X", calendar.get(Calendar.MINUTE) & 0x3F));
        builder.append(String.format("%02X", calendar.get(Calendar.HOUR_OF_DAY) & 0x1F));
        int week = calendar.get(Calendar.DAY_OF_WEEK);
        if (week == Calendar.SUNDAY) {
            week = 7;
        } else {
            week--;
        }
        builder.append(String.format("%02X", (week << 5) + (calendar.get(Calendar.DAY_OF_MONTH) & 0x1F)));
        builder.append(String.format("%02X", calendar.get(Calendar.MONTH) + 1));
        builder.append(String.format("%02X", calendar.get(Calendar.YEAR) - 2000));
        return builder.toString();
    }

    public static void main(String[] args) {
        //String s = HexTool.intToHex(100000, 4);
        //System.out.println(s);
        //s = lowHigh(100000, 4, true);
        //System.out.println(s);
        //String s = HexTool.intToHex(17, 1);
        //System.out.println(s);
        //int i = hexToInt("4903");
        //System.out.println(i);
        //String index = HexTool.lowHigh(i + 1, 2, false);
        //System.out.println(index);

        String index = "889008139d0617";
        Date date1 = toDate(index);
        System.out.println(date1.getTime());

        System.out.println(DateUtil.format(date1, "yyyy-MM-dd HH:mm:ss"));

        //long l = HexUtil.hexToLong("98B70E11100314");
        //String s = TimeScale(l.get);
        //System.out.println(s);

        //String timeStr = HexTool.getTimeStr(System.currentTimeMillis() / 1000);
        //System.out.println(timeStr);

        Long time = 1687936637551L;
        String timeStr1 = HexTool.toCP56Time2a(time);
        System.out.println(timeStr1);

        //int ffff = HexTool.hexToInt("ffff");
        //System.out.println(ffff);

        //String ss = HexTool.intToHex(300000, 4);
        //System.out.println(ss);
        //
        //String ssss = HexTool.lowHigh(300000, 4, true);
        //System.out.println(ssss);
        //
        //int f05500001 = HexTool.hexToInt("DOFB0100");
        //int f0550000 = HexUtil.hexToInt("0c");
    }
}
