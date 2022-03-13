// package com.alex.common.aspect;
//
// import com.alex.common.annotation.SysLog;
// import com.alex.common.bean.system.Log;
// import com.alex.common.client.LogServiceClient;
// import com.alex.common.util.HttpContextUtils;
// import com.alex.common.util.IPUtils;
// import com.google.gson.Gson;
// import org.aspectj.lang.ProceedingJoinPoint;
// import org.aspectj.lang.annotation.Around;
// import org.aspectj.lang.annotation.Aspect;
// import org.aspectj.lang.annotation.Pointcut;
// import org.aspectj.lang.reflect.MethodSignature;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.stereotype.Component;
//
// import javax.servlet.http.HttpServletRequest;
// import java.lang.reflect.Method;
// import java.util.Calendar;
//
// /**
//  * @author _Alexzinv_
//  * @date 2022/1/21
//  */
// @Component
// @Aspect
// public class SysLogAspect {
//
//     // private final LogServiceClient logService;
//     //
//     // @Autowired
//     // public SysLogAspect(LogServiceClient logService) {
//     //     this.logService = logService;
//     // }
//     @Autowired
//     private LogServiceClient logService;
//
//     @Pointcut("@annotation(com.alex.common.annotation.SysLog)")
//     public void logPointCut(){}
//
//     @Around("logPointCut()")
//     public Object logAround(ProceedingJoinPoint point) throws Throwable {
//         long start = System.currentTimeMillis();
//         Object r = point.proceed();
//         long time = System.currentTimeMillis() - start;
//         saveLog(point, time);
//         return r;
//     }
//
//     private void saveLog(ProceedingJoinPoint point, long time) {
//         MethodSignature signature = (MethodSignature) point.getSignature();
//         Method method = signature.getMethod();
//
//         Log log = new Log();
//         SysLog sysLog = method.getAnnotation(SysLog.class);
//         if(sysLog != null){
//             log.setOperation(sysLog.value());
//         }
//
//         String className = point.getTarget().getClass().getName();
//         String methodName = signature.getName();
//         log.setMethod(className + "." + methodName + "()");
//
//         Object[] args = point.getArgs();
//         String params = new Gson().toJson(args);
//         log.setParams(params);
//
//         HttpServletRequest request = HttpContextUtils.getHttpServletRequest();
//         String ipAddr = IPUtils.getIpAddr(request);
//         log.setIp(ipAddr);
//
//         // String username = SecurityContextHolder.getContext().getAuthentication().getName();
//         // log.setUsername(username);
//
//         log.setTime(time);
//         log.setCreateTime(Calendar.getInstance().getTime());
//         logService.saveLog(log);
//     }
// }
