package com.alex.common.consant;

/**
 * @Author _Alexzinv_
 * @Date 2021/11/19
 * @Description 公司常量
 */
public interface CompanyConstant {

    /**
     * 审核状态
     */
    interface AuditState {

        /**
         * 未审核
         */
        int UNREVIEWED = 0;

        /**
         * 审核通过
         */
        int PASS_THE_AUDIT = 1;

        /**
         * 审核未通过
         */
        int AUDIT_FAILED = 2;
    }

    /**
     * 激活状态
     */
    interface State {

        int NOT_ACTIVATED = 0;

        int ACTIVATED = 1;
    }
}
