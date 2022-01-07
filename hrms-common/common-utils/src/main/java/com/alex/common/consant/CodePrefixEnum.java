package com.alex.common.consant;

/**
 * @author _Alexzinv_
 * @date 2021/12/13
 * @description 编码前缀常量
 */
public enum CodePrefixEnum {
    /**
     * 部门编码前缀
     */
    DEPARTMENT_CODE_PREFIX("AX000", 1L),
    /**
     * 角色编码前缀
     */
    ROLE_CODE_PREFIX("SYS000", 10000L),
    /**
     * 工号
     */
    WORKER_NUMBER("NO.", 1000000L);

    private final String prefix;
    private final Long value;

    CodePrefixEnum(String prefix, Long value) {
        this.prefix = prefix;
        this.value = value;
    }

    public String getPrefix() {
        return prefix;
    }

    public Long getValue() {
        return value;
    }
}
