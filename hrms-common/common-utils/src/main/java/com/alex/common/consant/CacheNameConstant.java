package com.alex.common.consant;

/**
 * @author _Alexzinv_
 * @date 2022/2/16
 * @description 缓存命名 服务 -> Controller + {手动功能命名}
 */
public interface CacheNameConstant {

    /**
     * 系统服务
     */
    interface System {

        String USER = "system:user:";

        String PERMISSION = "system:permission:";

        String ROLE = "system:role:";

        String LOG = "system:log:";
    }

    /**
     * 公司服务
     */
    interface Company {

        String COMPANY = "company:company:";

        String DEPARTMENT = "company:department:";

        String POSITION = "company:position:";
    }

    /**
     * 成员服务
     */
    interface Member {

        String USER_COMPANY = "member:user_company:";

        String USER_PERSONAL_INFO = "member:user_personal_info:";
    }

    /**
     * 薪酬服务
     */
    interface Salary {

        String REWARDS_PUNISHMENT = "salary:rewards_punishment";

        String SALARY_ADJUST = "salary:salary_adjust";
    }

}
