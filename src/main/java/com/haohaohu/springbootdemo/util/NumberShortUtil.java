package com.haohaohu.springbootdemo.util;

/**
 * @author haohao(ronghao3508 gmail.com) on 2024/3/20 11:23
 * @version v1.0
 */
class NumberShortUtil {

    public static void main(String[] args) {

        String data = System.currentTimeMillis() + "";
        Long dataInt = Long.parseLong(data);

        String string = Long.toString(dataInt, 32);
        System.out.println(string);
        Long i = Long.parseLong(string, 32);
        System.out.println(i);

        //1h4aftrvc3801
        //1hpcur4ri

    }
}
