package com.alex.common.util.codefactory;

import com.alex.common.consant.CodePrefixEnum;

/**
 * @author _Alexzinv_
 * @date 2022/1/7
 * @description 工号生成
 */
public class WorkerNumberGenerator extends CodeGeneratorAbstract implements CodeGenerator {
    private static final Long DEFAULT_VALUE = CodePrefixEnum.WORKER_NUMBER.getValue();
    public static final String PREFIX = CodePrefixEnum.WORKER_NUMBER.getPrefix();
    public static final String SUFFIX = "";

    @Override
    public Long initValue() {
        return value == null ? DEFAULT_VALUE : value;
    }

    @Override
    public String getPrefix() {
        return PREFIX;
    }

    @Override
    public String getSuffix() {
        return SUFFIX;
    }
}
