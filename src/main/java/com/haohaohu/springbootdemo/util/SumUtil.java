package com.haohaohu.springbootdemo.util;

import cn.hutool.core.util.CharsetUtil;
import cn.hutool.core.util.HexUtil;
import cn.hutool.core.util.StrUtil;
import com.haohaohu.springbootdemo.EndiannessConversion;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author haohao(ronghao3508 gmail.com) on 2023/8/10 18:58
 * @version v1.0
 */
public class SumUtil {
    private static final Logger logger = LoggerFactory.getLogger(SumUtil.class);

    public static String getStringByHexStr(String needCheckStr) {
        String resultByHexStr = CRC16M.getResultByHexStr(needCheckStr);
        return exchange(resultByHexStr).toLowerCase();
    }

    public static String exchange(String str) {
        if (StrUtil.isNotBlank(str) && str.length() == 4) {
            String substring = str.substring(0, 2);
            String substring1 = str.substring(2, 4);
            return substring1 + substring;
        }
        return str;
    }


    public static String makeChecksum(String data) {
        if (data == null || data.equals("")) {
            return "";
        }
        int total = 0;
        int len = data.length();
        int num = 0;
        while (num < len) {
            String s = data.substring(num, num + 2);
            total += Integer.parseInt(s, 16);
            num = num + 2;
        }
        /**
         * 用256求余最大是255，即16进制的FF
         */
        int mod = total % (256 * 256);
        return HexTool.lowHigh2(mod, 2, true);
    }

    public static void main(String[] args) {
        //String data = "0066040103021111111111";
        //String[] cut = StrUtil.cut(data, 1);ISAPI_STATUS_LEN
        //int sum = 0;
        //for (int i = 0; i < cut.length; i++) {
        //    logger.info("data:{}", cut[i]);
        //    Integer i1 = Integer.valueOf(cut[i]);
        //    sum += i1;
        //}
        //logger.info("data1:{}", sum);

        //String hex = "1070010100094b373338323138353511495020434150545552452043414d45524100010931323334353637383940e78b145334d32d7246f32e00f73556680c2df4e7c39fb31fbc0d5fe621dc8b91236944532d324344393532352d45535520323032323130313941494b3733383231383535";
        //String he0 = "1070010100094b373338323138353511495020434150545552452043414d4552410001093132333435363738399cb0c7dd915401f692e9fd966a41629638fedfa112ce949167f885f3ae821725b3236944532d324344393532352d45535520323032323130313941494b3733383231383535";
        //String he1 = "10 70 01 01 00 09 4b3733383231383535 11 495020434150545552452043414d4552410001093132333435363738399cb0c7dd915401f692e9fd966a41629638fedfa112ce949167f885f3ae821725b3236944532d324344393532352d45535520323032323130313941494b3733383231383535";
        //String he2 = "10";
        //System.out.println(HexUtil.decodeHexStr(he0, CharsetUtil.CHARSET_UTF_8));
        //
        //String he3 = "4b3733383231383535";
        //System.out.println(HexUtil.decodeHexStr(he3, CharsetUtil.CHARSET_UTF_8));

        //String s = HexUtil.decodeHexStr("6c696e6b");
        //System.out.println(s);


        //444E5909003B37AB040100819702
        //包头（DNY）	长度	物理ID	消息ID	命令	数据	校验
        //444e59 1a00 0ef9c907 3700 21 cd080c0000000000000000000000001a5a 8904

        //包头（DNY）	长度	物理ID	消息ID	命令	数据	校验
        String data = "444E59 2600 0ef9c907 3701 82 00 02000000 01 01 0000 12345678123456781234567812345678 0000 0000 00 00 ffff 00 00 00 00 01";

        data = "444e591e00c970c606040021980810000000000000000000000000000000004119";//3d04
        data = StrUtil.cleanBlank(data);

        //包头（DNY）	长度	物理ID	消息ID	命令	数据	校验
        //data = "444E59 0900 3B37AB04 0100 81";
        System.out.println(data);
        String stringByHexStr = makeChecksum(data);
        System.out.println(stringByHexStr);
        System.out.println(data + stringByHexStr);

    }
}
