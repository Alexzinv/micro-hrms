package com.alex.common.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * @author _Alexzinv_
 * @date 2021/12/21
 * @description 调用方法Aop，输出调用方法和参数
 */
@Slf4j
@Aspect
@Component
public class ControllerAspect {

    @Pointcut("execution(* com.alex.*.controller.*.*(..))")
    public void controllerMethod(){ }

    @Before("controllerMethod()")
    public void before(JoinPoint joinPoint){
        String className = joinPoint.getTarget().getClass().getName();
        String methodName = joinPoint.getSignature().getName();
        Object[] args = joinPoint.getArgs();
        log.info("\n AspectJ --> className:" + className + " \n --> methodName: " +
                methodName + " \n --> args: " + Arrays.toString(args));
    }

}
