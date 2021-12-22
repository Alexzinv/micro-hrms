package com.alex.common.consant;

/**
 * @author _Alexzinv_
 * @date 2021/12/2
 * @description
 */
public interface MemUserCompanyConstant {

    /**
     * 在职状态
     */
    interface JobStatus {
        /**
         * 在职
         */
        int IN_ACTIVE_SERVICE = 0;

        /**
         * 离职
         */
        int LEAVE = 1;
    }

    /**
     * 聘用形式
     */
    interface EmployForm {
        /**
         * 正式工
         */
        int FULL_TIME_WORKERS = 0;
        /**
         * 兼职工
         */
        int PART_TIME_WORKERS = 1;
        /**
         * 外包
         */
        int OUTSOURCING = 2;
    }
}
