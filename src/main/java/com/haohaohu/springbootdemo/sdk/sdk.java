package com.haohaohu.springbootdemo.sdk;

import cn.hutool.json.JSONUtil;

/**
 * @author haohao(ronghao3508 gmail.com) on 2024/3/4 18:30
 * @version v1.0
 */
public class sdk {
    public static void main(String[] args) {
        String data = "{\"union_id\":\"200136\",\"park_id\":\"8925846\",\"local_id\":\"08bfb89e3c9d_4.2.0.0_ParkingServer\",\"version\":\"4.2.0.0\",\"set_params\":{\"month_send\":\"1\",\"usb_key\":\"liuyqing43048219860211\",\"prepay_send\":\"0\",\"receive_cloud\":\"1\"}}";
        LoginReq loginReq = JSONUtil.toBean(data, LoginReq.class);
        System.out.println(loginReq);
    }
}
