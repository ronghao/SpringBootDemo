package com.haohaohu.springbootdemo;

import cn.hutool.core.util.HexUtil;
import cn.hutool.core.util.StrUtil;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/**
 * @author haohao(ronghao3508 gmail.com) on 2023/11/29 19:05
 * @version v1.0
 */
public class EndiannessConversion {
    public static void main(String[] args) {
        // 假设要将一个整数转换为字节数组，并在不同字节序之间进行切换
        int value = 13236494;

        // 转换为小端字节序
        byte[] littleEndianBytes = toLittleEndian(value);
        System.out.println("Little Endian Bytes: " + bytesToHexString(littleEndianBytes));

        // 转换为大端字节序
        byte[] bigEndianBytes = toBigEndian(value);
        System.out.println("Big Endian Bytes: " + bytesToHexString(bigEndianBytes));

        // 从小端字节序恢复
        int littleEndianValue = fromLittleEndian(littleEndianBytes);
        System.out.println("Little Endian Value: " + littleEndianValue);

        // 从大端字节序恢复
        int bigEndianValue = fromBigEndian(bigEndianBytes);
        System.out.println("Big Endian Value: " + bigEndianValue);


        String data = "40aace04";
        data = StrUtil.sub(data, 0, -2) + "00";
        // 从小端字节序恢复
        int littleEndianValue1 = fromLittleEndian(HexUtil.decodeHex(data));
        System.out.println("Little Endian Value1: " + littleEndianValue1);
    }

    // 将整数转换为小端字节序的字节数组
    public static byte[] toLittleEndian(int value) {
        ByteBuffer buffer = ByteBuffer.allocate(Integer.BYTES);
        buffer.order(ByteOrder.LITTLE_ENDIAN);
        buffer.putInt(value);
        return buffer.array();
    }

    // 将整数转换为大端字节序的字节数组
    public static byte[] toBigEndian(int value) {
        ByteBuffer buffer = ByteBuffer.allocate(Integer.BYTES);
        buffer.order(ByteOrder.BIG_ENDIAN);
        buffer.putInt(value);
        return buffer.array();
    }

    // 从小端字节序的字节数组恢复整数
    public static int fromLittleEndian(byte[] bytes) {
        ByteBuffer buffer = ByteBuffer.wrap(bytes);
        buffer.order(ByteOrder.LITTLE_ENDIAN);
        return buffer.getInt();
    }

    // 从大端字节序的字节数组恢复整数
    public static int fromBigEndian(byte[] bytes) {
        ByteBuffer buffer = ByteBuffer.wrap(bytes);
        buffer.order(ByteOrder.BIG_ENDIAN);
        return buffer.getInt();
    }

    // 将字节数组转换为十六进制字符串（辅助方法）
    public static String bytesToHexString(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            sb.append(String.format("%02X", b));
        }
        return sb.toString();
    }


}
