package com.alex.common.util.codefactory;

import java.util.concurrent.atomic.AtomicLong;

/**
 * @author _Alexzinv_
 * @date 2022/1/7
 * @description
 *
 * ======================================================
 *  该工厂模式是心血来潮写着玩的，存在严重漏洞，不在任何地方使用
 * ======================================================
 */
public abstract class CodeGeneratorAbstract implements CodeGenerator {

    protected AtomicLong value;

    public void setValue(Long value) {
        this.value = new AtomicLong(value);
    }

    public Long getValue() {
        return value.getAndIncrement();
    }
}
