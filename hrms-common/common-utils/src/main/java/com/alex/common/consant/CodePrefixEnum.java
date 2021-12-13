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
    DEPARTMENT_CODE_PREFIX("AX000", "department_code"),
    /**
     * 角色编码前缀
     */
    ROLE_CODE_PREFIX("SYS000", "role_code");

    private final String code;
    private final String info;

    CodePrefixEnum(String code, String info) {
        this.code = code;
        this.info = info;
    }

    public String getCode() {
        return code;
    }

    public String getInfo() {
        return info;
    }
}
