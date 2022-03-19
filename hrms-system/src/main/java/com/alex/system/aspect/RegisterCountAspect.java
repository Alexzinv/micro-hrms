package com.alex.system.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author _Alexzinv_
 * @date 2022/1/12
 * @description
 */
@Aspect
@Component
public class RegisterCountAspect {

    private final ThreadPoolExecutor threadPoolExecutor;
    private final StreamBridge streamBridge;

    @Autowired
    public RegisterCountAspect(ThreadPoolExecutor threadPoolExecutor, StreamBridge streamBridge) {
        this.threadPoolExecutor = threadPoolExecutor;
        this.streamBridge = streamBridge;
    }

    /** 统计注册人数+1 */
    @Before("execution(* com.alex.system.controller.UserController.register(..))")
    public void countRegister() {
        threadPoolExecutor.execute(() ->
                streamBridge.send("registerCount-out-0",
                MessageBuilder.withPayload(LocalDate.now().toString()).build()));
    }
}