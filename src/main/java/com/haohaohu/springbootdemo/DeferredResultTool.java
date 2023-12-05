package com.haohaohu.springbootdemo;

import cn.hutool.core.exceptions.ExceptionUtil;
import cn.hutool.core.thread.ThreadUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.request.async.DeferredResult;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author haohao(ronghao3508 gmail.com) on 2023/8/19 10:21
 * @version v1.0
 */
public class DeferredResultTool {

    private static final Logger logger = LoggerFactory.getLogger(DeferredResultTool.class);

    private static final Long TIME_OUT = 5L;
    DeferredResult<String> task;

    public DeferredResultTool() {
        this.task = new DeferredResult<>(TIME_OUT);
        this.task.onCompletion(() -> {
            logger.info("完成");
            Object result = this.task.getResult();
            logger.info("result:{}", result);
        });
        this.task.onTimeout(() -> {
            logger.info("超时");
        });
        this.task.onError((error) -> {
            logger.error("error:{}", ExceptionUtil.stacktraceToOneLineString(error));
        });
    }

    public static void main(String[] args) {
        logger.info("start");
        DeferredResultTool deferredResultTool = new DeferredResultTool();

        //ThreadUtil.execAsync(new Runnable() {
        //    @Override
        //    public void run() {
        //        while (true) {
        //            deferredResultTool.task.getResult();
        //        }
        //    }
        //});
        logger.info("start1");
        ThreadUtil.safeSleep(3000);
        logger.info("start2");

        ThreadUtil.execAsync(new Runnable() {
            @Override
            public void run() {
                logger.info("start3");
                deferredResultTool.task.setResult("hello");
                //while (true) {
                //    deferredResultTool.task.setResult("hello");
                //}
            }
        });
    }

}
