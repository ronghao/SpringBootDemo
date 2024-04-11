package com.haohaohu.springbootdemo.sdk;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author haohao(ronghao3508 gmail.com) on 2024/3/4 18:31
 * @version v1.0
 */
@NoArgsConstructor
@Data
class LoginReq {

    private String unionId;
    private String parkId;
    private String localId;
    private String version;
    private SetParamsDTO setParams;

    @NoArgsConstructor
    @Data
    public static class SetParamsDTO {
        private String monthSend;
        private String usbKey;
        private String prepaySend;
        private String receiveCloud;
    }
}
