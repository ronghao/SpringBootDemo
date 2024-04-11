package com.haohaohu.springbootdemo.util;

import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.symmetric.AES;
import com.alibaba.fastjson.JSON;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.util.Base64Utils;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author haohao(ronghao3508 gmail.com) on 2023/9/5 20:58
 * @version v1.0
 */
public class Encode {

    /**
     * aes加密
     *
     * @param param
     * @return
     */
    public static String aesEncode(String param) {
        String encodingAesKey = "AD42F6697B035B7580E4FEF93BE20B4D";
        String replyMsg = param;
        Cipher cipher;
        try {
            cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            SecretKeySpec keySpec = new SecretKeySpec(encodingAesKey.getBytes(), "AES");
            IvParameterSpec iv = new IvParameterSpec(encodingAesKey.getBytes(), 0, 16);
            cipher.init(Cipher.ENCRYPT_MODE, keySpec, iv);

            // 加密
            byte[] encrypted = cipher.doFinal(replyMsg.getBytes("utf-8"));
            // 使用BASE64对加密后的字符串进行编码
            String base64Encrypted = new Base64().encodeToString(encrypted);
            return base64Encrypted;
        } catch (NoSuchAlgorithmException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

    /**
     * aes解密
     */
    public static String aesDecode(String param) {
        byte[] bs = new Base64().decode(param);
        Cipher cipher;
        String ret = "";
        try {
            cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            String encodingAesKey = "AD42F6697B035B7580E4FEF93BE20B4D";
            SecretKeySpec keySpec = new SecretKeySpec(encodingAesKey.getBytes(), "AES");
            IvParameterSpec iv = new IvParameterSpec(encodingAesKey.getBytes(), 0, 16);
            cipher.init(Cipher.DECRYPT_MODE, keySpec, iv);
            bs = cipher.doFinal(bs);
            ret = new String(bs, "utf8");
        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (InvalidAlgorithmParameterException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (BadPaddingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return ret;
    }

    /**
     * 生成md5
     *
     * @param message
     * @return
     */
    public static String getMD5(String message) {
        String md5str = "";
        try {
            // 1 创建一个提供信息摘要算法的对象，初始化为md5算法对象
            MessageDigest md = MessageDigest.getInstance("MD5");

            // 2 将消息变成byte数组
            byte[] input = message.getBytes();

            // 3 计算后获得字节数组,这就是那128位了
            byte[] buff = md.digest(input);

            // 4 把数组每一字节（一个字节占八位）换成16进制连成md5字符串
            md5str = bytesToHex(buff);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return md5str;
    }

    /**
     * 二进制转十六进制
     *
     * @param bytes
     * @return
     */
    public static String bytesToHex(byte[] bytes) {
        StringBuffer md5str = new StringBuffer();
        // 把数组每一字节换成16进制连成md5字符串
        int digital;
        for (int i = 0; i < bytes.length; i++) {
            digital = bytes[i];

            if (digital < 0) {
                digital += 256;
            }
            if (digital < 16) {
                md5str.append("0");
            }
            md5str.append(Integer.toHexString(digital));
        }
        return md5str.toString().toUpperCase();
    }

    public static boolean checkLength(String scanContent) {
        int length = scanContent.length();
        if (length == 18 || length == 19) {
            return StrUtil.startWith(scanContent, "9") || StrUtil.startWith(scanContent, "8") || StrUtil.startWith(scanContent, "3");
        } else {
            return false;
        }
    }


    public static void main(String[] args) {

        boolean b = checkLength("35095211170987258511");
        System.out.println(b);
        //String data = "{\"body\":\"\\u6d4b\\u8bd5\\u5185\\u5bb9\",\"out_trade_no\":\"29152154523069546710\",\"total_fee\":\"1\",\"user_no\":\"123456798\",\"user_name\":\"test\",\"return_url\":null,\"notify_url\":\"https:\\/\\/notify.zhizhixao.com\\/return.html\",\"pay_type\":1}";
        //String s = aesEncode(data);
        //System.out.println(s);
        //String data1 = "partner1" + s + "weiweixiao";
        //String md5 = getMD5(data1);
        //System.out.println(md5);
        //
        //String data3 = "U9qTuhXZ7nrQtNkfNPb6KpozO38WQ8oBBhNbaHfJSbfh7pqn4IYYoeUQC4ujAcuDmyTTA5PF0XV963qkM0MbhhwRXAymJfCWo5MYN5No/dW2FmK+bf1cLfS3lD5+gPURF7U1yTtS3VrCJrmSdcdMqmJy5yepLTCTkefBbhcKqZy54+xWIasdZ+BlqDO2PutSQOyDjXeGGfYso2FLvUb7Bv/EFSlTJdpeCobxGo7Aw8vkM93Fe7YuKtnBRS7IHOAnmx97J3PEeBgieGh047yv3U5ouKy/t+a+a5hTftkxnDE=";
        //String s1 = aesDecode(data3);
        //System.out.println(s1);

        //String s2 = cn.hutool.core.codec.Base64.decodeStr("eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIyIiwiZXhwIjoxNzAxODU2MzQ1LCJzdWIiOiJ0ZXN0IiwiaWF0IjoxNzAxODUyNzQ1fQ.7lwYRODAKnsf_jb_37J9eO5LbitVIkzXvqwLs4qqKK8");
        //System.out.println(s2);
        //
        //String s = Base64Utils.encodeToString("云A1K79T".getBytes());
        //System.out.println(s);

        //String sn = "1111";
        //String topic = StrUtil.format("device/{}/message/down/reboot_dev", sn);
        //String snowflakeStr = SnowFlakeUtil.getSnowflakeStr();
        //String content = StrUtil.format("{\"id\":\"{}\",\"sn\":\"{}\",\"name\":\"reboot_dev\",\"version\":\"1.0\",\"timestamp\":{},\"payload\":{\"type\":\"reboot_dev\",\"body\":{}}}", snowflakeStr, sn, System.currentTimeMillis() / 1000);
        //System.out.println(topic);
        //System.out.println(content);
    }
}
