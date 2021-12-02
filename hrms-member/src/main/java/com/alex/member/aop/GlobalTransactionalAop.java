package com.alex.member.aop;

import io.seata.core.exception.TransactionException;
import io.seata.tm.api.GlobalTransaction;
import io.seata.tm.api.GlobalTransactionContext;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * @author _Alexzinv_
 * @date 2021/12/1
 * @description 避免Seata全局事务异常被捕获导致回滚失效
 */
@Slf4j
@Aspect
@Component
public class GlobalTransactionalAop {

    @Pointcut("@annotation(io.seata.spring.annotation.GlobalTransactional)")
    public void txAnnotation(){ }

    @AfterThrowing(throwing = "throwable", pointcut = "txAnnotation()")
    public void doAfterReturning(Throwable throwable) {
        GlobalTransaction globalTransaction = GlobalTransactionContext.getCurrentOrCreate();
        if (globalTransaction == null) {
            return;
        }

        log.info("AOP------- 全局事务回滚-- ---xid:{}------》", globalTransaction.getXid());
        try {
            globalTransaction.rollback();
        } catch (TransactionException e) {
            e.printStackTrace();
        }
    }
}