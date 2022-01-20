// package com.alex.system.aspect;
//
// import org.springframework.messaging.support.MessageBuilder;
// import org.aspectj.lang.annotation.Aspect;
// import org.aspectj.lang.annotation.Before;
// import org.aspectj.lang.annotation.Pointcut;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.stereotype.Component;
// import org.springframework.cloud.stream.function.StreamBridge;
//
// import java.time.LocalDate;
// import java.util.concurrent.ThreadPoolExecutor;
//
// /**
//  * @author _Alexzinv_
//  * @date 2022/1/12
//  * @description
//  */
// @Aspect
// @Component
// public class LoginCountAspect {
//
//     private final ThreadPoolExecutor threadPoolExecutor;
//     private final StreamBridge streamBridge;
//
//     @Autowired
//     public LoginCountAspect(ThreadPoolExecutor threadPoolExecutor, StreamBridge streamBridge) {
//         this.threadPoolExecutor = threadPoolExecutor;
//         this.streamBridge = streamBridge;
//     }
//
//     @Pointcut("execution(* com.alex.system.controller.UserInfoController.info(..))")
//     public void loginUser() {}
//
//     /** 统计登陆次数+1 */
//     @Before("loginUser()")
//     public void loginCount() {
//         threadPoolExecutor.execute(() -> streamBridge.send("loginCount-out-0",
//                 MessageBuilder.withPayload(LocalDate.now().toString()).build()));
//     }
// }
