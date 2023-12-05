package com.haohaohu.springbootdemo;

import cn.hutool.core.exceptions.ExceptionUtil;
import cn.hutool.core.thread.ExecutorBuilder;
import cn.hutool.core.thread.ThreadUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author haohao(ronghao3508 gmail.com) on 2023/12/2 18:26
 * @version v1.0
 */
public class ExecutorServiceTool {


    private static final Logger logger = LoggerFactory.getLogger(ExecutorServiceTool.class);

    ExecutorService executor = ExecutorBuilder.create()
            .setCorePoolSize(2)
            .setMaxPoolSize(2)
            .setWorkQueue(new LinkedBlockingQueue<>(2))
            .setHandler(new ThreadPoolExecutor.DiscardPolicy() {
                @Override
                public void rejectedExecution(Runnable r, ThreadPoolExecutor e) {
                    logger.error("error:::::::::::::::::::::" + e.toString());
                    throw new RejectedExecutionException("泊链同步线程池队列已满");
                }
            })
            .build();

    ExecutorService executor1 = ExecutorBuilder.create()
            .setCorePoolSize(2)
            .setMaxPoolSize(2)
            .setWorkQueue(new LinkedBlockingQueue<>(2))
            .setHandler(new ThreadPoolExecutor.AbortPolicy() {
                @Override
                public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
                    logger.error("队列已满，无法处理:::::::::::::::::::::" + executor.toString());
                }
            })
            .build();

    private ExecutorService executor2 = ExecutorBuilder.create()
            .setCorePoolSize(2)
            .setMaxPoolSize(2)
            .setWorkQueue(new LinkedBlockingQueue<>(2))
            .setHandler((r, executor) -> logger.error("队列已满，无法处理"))
            .build();

    public static void main(String[] args) {
        try {
            ExecutorServiceTool executorServiceTool = new ExecutorServiceTool();
            for (int i = 0; i < 10; i++) {
                int finalI = i;
                executorServiceTool.executor.execute(() -> {
                    logger.info(Thread.currentThread().getName() + ":" + finalI);
                    ThreadUtil.safeSleep(1000);
                });
                logger.info(Thread.currentThread().getName() + ":" + i);
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("error:::::::::::::::::::::" + e.getMessage());
        }
    }
}
