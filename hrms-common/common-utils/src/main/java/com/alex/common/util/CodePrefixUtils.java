package com.alex.common.util;

import com.alex.common.consant.CodePrefixEnum;

/**
 * @author _Alexzinv_
 * @date 2021/12/13
 * @description 编码生成工具
 */
public interface CodePrefixUtils {

    /**
     * 获取最新编码, 自行实现
     * @return 最新编码
     */
    String getLatestCode();

    /**
     * 根据前缀生成编码
     * @param codePrefixEnum 编码前缀枚举
     * @return 编码
     */
    default String getCode(CodePrefixEnum codePrefixEnum) {
        String codePrefix = codePrefixEnum.getCode();
        String code = getLatestCode();
        // 为空则是第一次添加，初始化，否则按最大值自增
        if(code == null){
            return codePrefix + CustomSerialGenerator.initCode();
        }
        String newCode = code.replace(codePrefix, "");
        long value = Long.parseLong(newCode);
        return codePrefix + ++value;
    }
}
