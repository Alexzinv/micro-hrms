// package com.alex.common.client;
//
// import com.alex.common.base.AbstractBaseClient;
// import lombok.extern.slf4j.Slf4j;
// import org.springframework.cloud.openfeign.FallbackFactory;
// import org.springframework.stereotype.Component;
//
// /**
//  *
//  * @author _Alexzinv_
//  * @date 2022/2/16
//  * @description 日志服务降级处理
//  */
// @Component
// @Slf4j
// public class LogFallbackFactory extends AbstractBaseClient implements FallbackFactory<LogServiceClient> {
//
//     @Override
//     public LogServiceClient create(Throwable throwable) {
//         log.error("日志服务调用失败:{}", throwable.getMessage());
//         return log -> RESULT;
//     }
// }
