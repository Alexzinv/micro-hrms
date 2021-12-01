package com.alex.system.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/**
 * @author _Alexzinv_
 * @date 2021/12/1
 * @description 用于处理程序调用发生异常的时候由于异常被处理以后无法触发事务，而进行的处理，
 *              使之可以正常的触发事务
 */
@Aspect
@Component
@Slf4j
public class SeataAop {

    // @Before("execution(* io.seata.*.service.*.*(..))")
    // public void before(JoinPoint joinPoint) throws TransactionException {
    //     MethodSignature signature = (MethodSignature)joinPoint.getSignature();
    //     Method method = signature.getMethod();
    //     log.info("拦截到需要分布式事务的方法," + method.getName());
    //     // 此处可用redis或者定时任务来获取一个key判断是否需要关闭分布式事务
    //     GlobalTransaction tx = GlobalTransactionContext.getCurrentOrCreate();
    //     tx.begin(300000, "test-client");
    //     log.info("创建分布式事务完毕" + tx.getXid());
    // }
    //
    // @AfterThrowing(throwing = "e", pointcut = "execution(* io.seata.*.service.*.*(..))")
    // public void doRecoveryActions(Throwable e) throws TransactionException {
    //     log.info("方法执行异常:{}", e.getMessage());
    //     if (!StringUtils.isBlank(RootContext.getXID())) {
    //         GlobalTransactionContext.reload(RootContext.getXID()).rollback();
    //     }
    // }

}
