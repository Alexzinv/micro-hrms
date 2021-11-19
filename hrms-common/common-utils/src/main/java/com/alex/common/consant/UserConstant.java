package com.alex.common.consant;

/**
 * @Author _Alexzinv_
 * @Date 2021/11/18
 * @Description 用户常量
 */
public interface UserConstant {

    /**
     * 用户账号级别
     */
    interface Level{
        /**
         * 系统管理员
         */
        int SYS_ADMIN = 1;

        /**
         * 企业管理员
         */
        int CO_ADMIN = 2;

        /**
         * 企业用户
         */
        int CO_USER = 3;
    }

    /**
     * 账号状态
     */
    interface EnableState{
        /**
         * 禁用
         */
        int DISABLE = 0;

        /**
         * 可用
         */
        int ENABLE = 1;
    }
}
