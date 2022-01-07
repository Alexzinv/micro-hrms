package com.alex.common.util.codefactory;

import com.alex.common.consant.CodePrefixEnum;

/**
 * @author _Alexzinv_
 * @date 2022/1/7
 * @description 部门编码生成
 */
public class DepartmentCodeGenerator extends CodeGeneratorAbstract implements CodeGenerator {

    private static final Long DEFAULT_VALUE = CodePrefixEnum.DEPARTMENT_CODE_PREFIX.getValue();
    public static final String PREFIX = CodePrefixEnum.DEPARTMENT_CODE_PREFIX.getPrefix();
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
