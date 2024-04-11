package com.haohaohu.springbootdemo.util;

import cn.hutool.cache.impl.TimedCache;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.thread.ThreadUtil;
import cn.hutool.core.util.StrUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;

/**
 * @author haohao(ronghao3508 gmail.com) on 2024/3/12 9:59
 * @version v1.0
 */
public class CacheUtil {

    private static final Logger logger = LoggerFactory.getLogger(CacheUtil.class);
    public TimedCache<String, String> timedCache = cn.hutool.cache.CacheUtil.newTimedCache(60_000);

    /**
     * @param time 过期时间 毫秒
     */
    public void putTimedCache(String key, String value, long time) {
        if (timedCache == null) {
            logger.warn("timedCache为null");
            return;
        }
        timedCache.put(key, value, time);
    }

    /**
     * @param time 指定时间 毫秒
     */
    public void putTimedCacheSpecificTime(String key, String value, long time) {
        if (timedCache == null) {
            logger.warn("timedCache为null");
            return;
        }
        long delayTime = 0;
        if (time > 0) {
            long currentTimeMillis = System.currentTimeMillis();
            if (time <= currentTimeMillis) {
                return;
            }
            delayTime = time - currentTimeMillis;
            logger.info("delayTime：{}", delayTime);
            //delayTime = 3000;
        } else {
            delayTime = time;
        }
        timedCache.put(key, value, delayTime);
    }


    /**
     * 获取今天 缓存
     *
     * @return
     */
    public String getTodayStr() {
        if (timedCache == null) {
            logger.warn("timedCache为null");
            return DateUtil.format(new Date(), "yyyyMMdd");
        }

        String key = "today";
        String todayTime = timedCache.get(key, false);
        if (StrUtil.isBlank(todayTime)) {
            logger.info("缓存中没有今天的数据");
            todayTime = DateUtil.format(new Date(), "yyyyMMdd");
            DateTime dateTime = DateUtil.endOfDay(new Date());
            putTimedCacheSpecificTime(key, todayTime, dateTime.getTime());
        }
        return todayTime;
    }

    public static void main(String[] args) {

        CacheUtil cacheUtil = new CacheUtil();
        ThreadUtil.execute(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    ThreadUtil.safeSleep(2000);
                    logger.info("时间：{}", cacheUtil.getTodayStr());
                }
            }
        });
    }


}
