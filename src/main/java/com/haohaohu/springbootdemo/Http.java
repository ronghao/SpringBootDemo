package com.haohaohu.springbootdemo;


import cn.hutool.core.exceptions.ExceptionUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONUtil;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author haohao(ronghao3508 gmail.com) on 2024/3/5 11:30
 * @version v1.0
 */
public class Http {

    private static final Logger logger = LoggerFactory.getLogger(Http.class);


    private static final String BASE_URL = "https://open.vzicloud.com";
    private static final String key = "KFDnHNG162u95XSpYTiKTdyNY6UdPSQ6";
    private static final String secret = "NVZKZrQlKfaMsSlqWG1mxYYo4sl3A1a9";

    private static String healthCheckWeb(String url) {
        try {
            String result = HttpUtil.get(url, 5000);
            logger.info(result);
            if (StrUtil.isBlank(result)) {
                return "error";
            }
            if (!JSONUtil.isJsonObj(result)) {
                return "error";
            }
            JSONObject jsonObject = JSONObject.parseObject(result);
            if (jsonObject.containsKey("code")) {
                if (jsonObject.getInteger("code") == 200) {
                    return "ok";
                }
            }
        } catch (Exception e) {
            logger.error("{}", ExceptionUtil.stacktraceToOneLineString(e));
            return "error";
        }
        return "ok";
    }

    public static void main(String[] args) {
        String url = "https://test31.zhenbokeji.com/yunpark/test/heartCheck?date=111";
        String s = healthCheckWeb(url);
        logger.info(s);
    }
}
