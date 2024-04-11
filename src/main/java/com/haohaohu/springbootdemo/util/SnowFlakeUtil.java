package com.haohaohu.springbootdemo.util;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.util.HexUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.core.util.URLUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Date;
import java.util.UUID;

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
        System.out.println("转换：" + TimeTools.getTime_yyyyMMddHHmmss((id >> 22) + Snowflake.DEFAULT_TWEPOCH));
        System.out.println(id2);
        System.out.println(id2 >> 22);
        System.out.println((id2 >> 22) + Snowflake.DEFAULT_TWEPOCH);
        System.out.println("当前：" + TimeTools.getTime_yyyyMMddHHmmss((id2 >> 22) + Snowflake.DEFAULT_TWEPOCH));
    }

    public static void main(String[] args) throws UnsupportedEncodingException {
        //String snowflakeStr = SnowFlakeUtil.getSnowflakeStr();
        //
        //System.out.println(snowflakeStr);

        //decode("1676517778016530433");//2023-07-25 18:12:51

        //2023-07-26 16:23:02

        //1741748341845069826  2024-01-01 17:08:59
        decode("1722106446760210434");
        //
        ////String data = "33 E8 BF 9B E5 87 BA E7 B1 BB E5 9E 8B E9 94 99 E8 AF AF EF BC 9A E8 BF 9B E5 87 BA E7 B1 BB E5 9E 8B E4 B8 BA EF BC 88 E8 BF 9B EF BC 8C E5 87 BA EF BC 89 E3 80 82";
        ////String s = HexUtil.decodeHexStr(StrUtil.cleanBlank(data));
        ////System.out.println(s);
        //
        //String data = StrUtil.format("{}{}{}","1","1"+StrUtil.toStringOrNull(null));
        //System.out.println(data);

        //Date date = DateUtil.beginOfMonth(DateUtil.offsetMonth(new Date(), 0 - 10)).toJdkDate();
        //System.out.println(DateUtil.formatDate(date));

        //String licence = UUID.randomUUID().toString().replace("-", "").substring(10, 17).toUpperCase();
        //
        //System.out.println(licence);
        //
        //String licence1 = RandomUtil.randomString("0123456789ABCDEF", 7);
        //System.out.println(licence1);

        String s = HexUtil.toHex(Long.valueOf("1770284016428228609"));
        System.out.println(s);




    }
}
