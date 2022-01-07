package com.alex.common.util.codefactory;

/**
 * @author _Alexzinv_
 * @date 2022/1/7
 * @description 编码生成
 *
 *  ======================================================
 *   该工厂模式是心血来潮写着玩的，存在严重漏洞，不在任何地方使用
 *  ======================================================
 */
public interface CodeGenerator {

    /**
     * 初始值
     * @return long
     */
    Long initValue();

    /**
     * 前缀
     * @return 前缀
     */
    String getPrefix();

    /**
     * 后缀
     * @return 后缀
     */
    String getSuffix();


}
