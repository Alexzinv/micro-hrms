package com.alex.common.util.codefactory;

import com.alex.common.consant.CodePrefixEnum;

/**
 * @author _Alexzinv_
 * @date 2022/1/7
 * @description
 */
public class RoleCodeGenerator extends CodeGeneratorAbstract implements CodeGenerator {
    private static final Long DEFAULT_VALUE = CodePrefixEnum.ROLE_CODE_PREFIX.getValue();
    public static final String PREFIX = CodePrefixEnum.ROLE_CODE_PREFIX.getPrefix();
    public static final String SUFFIX = "";

    @Override
    public Long initValue() {
        return value == null ? DEFAULT_VALUE : value.getAndIncrement();
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
