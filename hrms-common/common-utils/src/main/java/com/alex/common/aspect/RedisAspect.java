package com.alex.common.aspect;

import com.alex.common.consant.ResultCodeEnum;
import com.alex.common.exception.HRMSException;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.context.annotation.Configuration;

/**
 * @author Alex
 * @date 2022-02-16
 */
@Aspect
@Slf4j
@Configuration
public class RedisAspect {

    @Around("execution(* com.alex.common.util.RedisUtils.*(..))")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        Object result;
        try{
            result = point.proceed();
        }catch (Exception e){
            log.error("redis error", e);
            throw new HRMSException(ResultCodeEnum.REDIS_EXCEPTION);
        }
        return result;
    }
}