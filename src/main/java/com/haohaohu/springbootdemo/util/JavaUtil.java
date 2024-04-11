package com.haohaohu.springbootdemo.util;

import cn.hutool.core.collection.ListUtil;
import cn.hutool.core.date.LocalDateTimeUtil;
import cn.hutool.core.lang.Opt;
import cn.hutool.core.net.NetUtil;
import cn.hutool.core.thread.ThreadUtil;
import cn.hutool.core.util.NumberUtil;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import cn.hutool.log.Log;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.session.ResultHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author haohao(ronghao3508 gmail.com) on 2023/11/23 10:03
 * @version v1.0
 */
public class JavaUtil {
    private static final Logger logger = LoggerFactory.getLogger(JavaUtil.class);

    public static class Data {
        public String name;
        public int age;
    }

    public void test(String data) {
        data = "2";
    }

    private void test1(Data javaData) {
        javaData.name = "2";
        javaData.age = 2;
    }

    public static boolean isBetween(long timestamp, int beginHours, int endHours) {
        LocalDateTime localDateTime = LocalDateTimeUtil.of(timestamp * 1000, ZoneId.systemDefault());
        LocalDateTime startDateTime = LocalDateTime.of(localDateTime.toLocalDate(), LocalTime.of(beginHours, 0));
        LocalDateTime endDateTime = LocalDateTime.of(localDateTime.toLocalDate(), LocalTime.of(endHours, 0));
        return !localDateTime.isBefore(startDateTime) && !localDateTime.isAfter(endDateTime);
    }

    private static long getPreviousTimestamp(long timestamp, int hours) {
        LocalDateTime currentDateTime = LocalDateTimeUtil.of(timestamp * 1000, ZoneId.systemDefault());
        LocalDateTime previousSevenAMDateTime = LocalDateTime.of(currentDateTime.toLocalDate(), LocalTime.of(hours, 0));
        // 如果当前时间已经是7点或更晚，则返回当天的7点时间戳
        if (currentDateTime.toLocalTime().isAfter(LocalTime.of(hours, 0))) {
            return previousSevenAMDateTime.atZone(ZoneId.systemDefault()).toEpochSecond();
        }
        // 否则，返回前一天的7点时间戳
        return previousSevenAMDateTime.minusDays(1).atZone(ZoneId.systemDefault()).toEpochSecond();
    }

    public static String getLastIp() {
        String localHost = NetUtil.getLocalhost().toString();
        if (StrUtil.isBlank(localHost)) {
            return "00";
        }
        int i = StrUtil.lastIndexOfIgnoreCase(localHost, ".");
        if (i == -1) {
            return "00";
        }
        String sub = StrUtil.sub(localHost, i + 1, localHost.length());
        if (StrUtil.isBlank(sub)) {
            return "00";
        }
        return StrUtil.fillBefore(sub, '0', 2);
    }


    public static void main(String[] args) {
        //JavaUtil javaUtil = new JavaUtil();
        //
        //Data javaData = new Data();
        //javaData.name = "1";
        //javaData.age = 1;
        //
        //String data = "1";
        //Log.get().info("data:{}", data);
        //javaUtil.test(data);
        //Log.get().info("data:{}", data);
        //
        //Log.get().info("data1:{}", JSONUtil.toJsonStr(javaData));
        //javaUtil.test(javaData.name);
        //Log.get().info("data1:{}", JSONUtil.toJsonStr(javaData));
        //
        //Log.get().info("data2:{}", JSONUtil.toJsonStr(javaData));
        //javaUtil.test1(javaData);
        //Log.get().info("data2:{}", JSONUtil.toJsonStr(javaData));
        //
        //
        //javaData = new Data();
        //javaData.name = "11";
        //
        //javaData = null;
        //
        //String rightType = Opt.ofNullable(javaData).map(it -> it.name).orElse("-1");
        //Log.get().info("data3:{}", rightType);


        //int i = (int) (System.currentTimeMillis() / 1000);
        //System.out.println(i);

        //Integer data = null;
        //JSONObject json = new JSONObject();
        //json.put("data", data);
        //
        //String data1 = json.getString("data");
        //if (StrUtil.isBlank(data1)) {
        //    System.out.println("------------");
        //} else {
        //    System.out.println(data1);
        //}
        //
        //String data12 = "1.1#";
        //
        //boolean aLong = NumberUtil.isLong(data12);
        //
        //if (!aLong) {
        //    System.out.println("------------");
        //} else {
        //    System.out.println(data12);
        //}
        //
        //String number11 = null;
        //String data112 = "(" + number11 + ")";
        //System.out.println(data112);

        //boolean between = isBetween(1703943479, 7, 17);
        //System.out.println(between);
        //
        //long previousSevenAMTimestamp = getPreviousTimestamp(1703907695, 21);
        //System.out.println(previousSevenAMTimestamp);

        //String localHost = NetUtil.getLocalhost().toString();
        //System.out.println(localHost);
        //localHost = null;
        //if (StrUtil.isBlank(localHost)) {
        //    return;
        //}
        //int i = StrUtil.lastIndexOfIgnoreCase(localHost, ".");
        //if (i == -1) {
        //    return;
        //}
        //System.out.println(i);
        //String sub = StrUtil.sub(localHost, i + 1, localHost.length());
        //if (StrUtil.isBlank(sub)) {
        //    sub = "00";
        //}
        //
        //sub = StrUtil.fillBefore(sub, '0', 2);
        //System.out.println(sub);
        //String ip = getLastIp();
        //System.out.println(ip);

        //LocalTime currentTime = LocalTime.from(LocalDateTimeUtil.of(new Date(1709217004000L)));
        //
        //// 定义 23:30 和 24:00 的时间
        //LocalTime startTime = LocalTime.of(23, 30);
        //LocalTime endTime = LocalTime.of(0, 0);
        //
        //// 判断当前时间是否在指定区间内
        //if (currentTime.isAfter(startTime) || currentTime.equals(startTime)) {
        //    System.out.println("当前时间在 23:30 到 24:00 之间");
        //} else if (currentTime.isBefore(endTime)) {
        //    System.out.println("当前时间在 23:30 到 24:00 之间");
        //} else {
        //    System.out.println("当前时间不在 23:30 到 24:00 之间");
        //}

        JavaUtil javaUtil = new JavaUtil();
        javaUtil.lock("1", 10, 20, 10);
    }

    /**
     * @param serialNoLock
     * @param lockExpire
     * @param maxCount
     * @param delay        maxCount*delay =最长时间
     */
    public boolean lock(String serialNoLock, long lockExpire, int maxCount, int delay) {
        int index = 1;
        boolean isOut = index > maxCount;
        if (isOut) {
            return true;
        }
        boolean lock = false;
        while (!isOut && !lock) {
            lock = lock(serialNoLock, lockExpire);
            if (!lock) {
                index += 1;
                ThreadUtil.safeSleep(delay);
                logger.info("----------[{}]延迟【{}】 发送消息", serialNoLock, delay);
            }
            isOut = index > maxCount;
        }
        return lock;
    }

    public boolean lock(String key, long lockExpire) {
        List<Integer> integers = ListUtil.toList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11);
        boolean b = RandomUtil.randomEle(integers) > 10;
        if (b) {
            System.out.println("获得锁");
        } else {
            System.out.println("未获得锁");
        }
        return b;
    }
}
