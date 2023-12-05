package com.haohaohu.springbootdemo.util;

import cn.hutool.core.util.CharsetUtil;
import cn.hutool.core.util.HexUtil;

import java.io.OutputStream;

/**
 * @author haohao(ronghao3508 gmail.com) on 2023/11/7 11:37
 * @version v1.0
 */
public class CameraTcp {

    public static boolean sendCmd(String cmd) {
        try {
            int len = cmd.getBytes().length;
            byte[] header = {'V', 'Z', 0, 0, 0, 0, 0, 0};
            header[4] += (byte) ((len >> 24) & 0xFF);
            header[5] += (byte) ((len >> 16) & 0xFF);
            header[6] += (byte) ((len >> 8) & 0xFF);
            header[7] += (byte) (len & 0xFF);


            System.out.println(HexUtil.encodeHex(header));
            System.out.println(HexUtil.encodeHex(cmd.getBytes()));
        } catch (Exception e) {
            System.out.println("Error:" + e.getMessage());
            return false;
        }
        return true;
    }

    public static void main(String[] args) {
        sendCmd("{\"cmd\":\"ioctl\",\"io\" :0,\"value\":2,\"delay\":500}");
    }
}
