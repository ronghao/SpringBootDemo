package com.haohaohu.springbootdemo.util;

import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author haohao(ronghao3508 gmail.com) on 2021/12/1 13:06
 * @version v1.0
 */
public class SnowFlakeUtil {
    private static final Logger logger = LoggerFactory.getLogger(SnowFlakeUtil.class);

    public static String to14Length(String id) {
        if (StrUtil.isBlank(id)) {
            logger.warn("雪花id为空");
            return id;
        }

        if (id.length() <= 14) {
            return id;
        }

        int sub = id.length() - 14;
        return id.substring(sub);
    }

    /**
     * @return 1644289462156529664
     */
    public static String getSnowflakeStr() {
        Snowflake snowflake = IdUtil.getSnowflake(1, 1);
        long id = snowflake.nextId();
        return String.valueOf(id);
    }

    public static void decode(String idStr) {
        long id = Long.valueOf(idStr);
        System.out.println(id);
        Snowflake snowflake = new Snowflake(1, 1);
        long id2 = snowflake.nextId();
        System.out.println(id >> 22);
        System.out.println((id >> 22) + Snowflake.DEFAULT_TWEPOCH);
        System.out.println("转换：" +TimeTools.getTime_yyyyMMddHHmmss((id >> 22) + Snowflake.DEFAULT_TWEPOCH));
        System.out.println(id2);
        System.out.println(id2 >> 22);
        System.out.println((id2 >> 22) + Snowflake.DEFAULT_TWEPOCH);
        System.out.println("当前：" + TimeTools.getTime_yyyyMMddHHmmss((id2 >> 22) + Snowflake.DEFAULT_TWEPOCH));
    }

    public static void main(String[] args) {
        //String snowflakeStr = SnowFlakeUtil.getSnowflakeStr();
        //
        //System.out.println(snowflakeStr);

        decode("1676211477814796290");

        decode("1676213006516449282");
    }
}
