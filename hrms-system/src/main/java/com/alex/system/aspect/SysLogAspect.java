package com.alex.system.aspect;

import com.alex.common.util.HttpContextUtils;
import com.alex.common.util.IPUtils;
import com.alex.system.annotation.SysLog;
import com.alex.system.entity.Log;
import com.alex.system.service.LogService;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Calendar;

/**
 * @author _Alexzinv_
 * @date 2022/1/21
 */
@Component
@Aspect
public class SysLogAspect {

    private final LogService logService;

    @Autowired
    public SysLogAspect(LogService logService) {
        this.logService = logService;
    }

    @Pointcut("@annotation(com.alex.system.annotation.SysLog)")
    public void logPointCut(){}

    @Around("logPointCut()")
    public Object logAround(ProceedingJoinPoint point) throws Throwable {
        long start = System.currentTimeMillis();
        Object r = point.proceed();
        long time = System.currentTimeMillis() - start;
        saveLog(point, time);
        return r;
    }

    private void saveLog(ProceedingJoinPoint point, long time) {
        MethodSignature signature = (MethodSignature) point.getSignature();
        Method method = signature.getMethod();

        Log log = new Log();
        SysLog sysLog = method.getAnnotation(SysLog.class);
        if(sysLog != null){
            log.setOperation(sysLog.value());
        }

        String className = point.getTarget().getClass().getName();
        String methodName = signature.getName();
        log.setMethod(className + "." + methodName + "()");

        Object[] args = point.getArgs();
        log.setParams(Arrays.toString(args));

        HttpServletRequest request = HttpContextUtils.getHttpServletRequest();
        String ipAddr = IPUtils.getIpAddr(request);
        log.setIp(ipAddr);

        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        log.setUsername(username);

        log.setTime(time);
        log.setCreateTime(Calendar.getInstance().getTime());
        logService.save(log);
    }
}
