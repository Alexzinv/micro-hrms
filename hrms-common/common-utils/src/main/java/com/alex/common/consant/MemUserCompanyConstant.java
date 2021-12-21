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

    /**
     * 岗位
     */
    enum Position{
        /**
         * java开发
         */
        JAVA_ENGINEER_1(1, "初级Java工程师"),
        JAVA_ENGINEER_2(2, "中级Java工程师"),
        JAVA_ENGINEER_3(3, "高级Java工程师"),
        C_ENGINEER_1(11, "初级C工程师"),
        C_ENGINEER_2(12, "中级C工程师");

        private final int val;
        private final String name;

        Position(int val, String name) {
            this.val = val;
            this.name = name;
        }
    }
}
