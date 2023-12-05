package com.haohaohu.springbootdemo.util;

import cn.hutool.core.lang.Opt;
import cn.hutool.json.JSON;
import cn.hutool.json.JSONUtil;
import cn.hutool.log.Log;

/**
 * @author haohao(ronghao3508 gmail.com) on 2023/11/23 10:03
 * @version v1.0
 */
public class JavaUtil {

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


    public static void main(String[] args) {
        JavaUtil javaUtil = new JavaUtil();

        Data javaData = new Data();
        javaData.name = "1";
        javaData.age = 1;

        String data = "1";
        Log.get().info("data:{}", data);
        javaUtil.test(data);
        Log.get().info("data:{}", data);

        Log.get().info("data1:{}", JSONUtil.toJsonStr(javaData));
        javaUtil.test(javaData.name);
        Log.get().info("data1:{}", JSONUtil.toJsonStr(javaData));

        Log.get().info("data2:{}", JSONUtil.toJsonStr(javaData));
        javaUtil.test1(javaData);
        Log.get().info("data2:{}", JSONUtil.toJsonStr(javaData));


        javaData = new Data();
        javaData.name = "11";

        javaData = null;

        String rightType = Opt.ofNullable(javaData).map(it -> it.name).orElse("-1");
        Log.get().info("data3:{}", rightType);

    }
}
