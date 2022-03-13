// package com.alex.common.client;
//
// import com.alex.common.bean.system.Log;
// import com.alex.common.consant.ServiceNameConstant;
// import com.alex.common.util.R;
// import org.springframework.cloud.openfeign.FeignClient;
// import org.springframework.web.bind.annotation.PostMapping;
//
// /**
//  * @author _Alexzinv_
//  * @date 2022/2/16
//  * @description 日志RPC
//  */
// @FeignClient(value = ServiceNameConstant.SYSTEM_SERVICE ,fallbackFactory = LogFallbackFactory.class)
// public interface LogServiceClient {
//
//     /**
//      * 保存日志
//      * @param log 日志
//      * @return 操作结果
//      */
//     @PostMapping("/admin/acl/log/inner/save")
//     R saveLog(Log log);
// }
