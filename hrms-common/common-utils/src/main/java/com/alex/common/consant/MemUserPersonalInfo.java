package com.alex.common.consant;

/**
 * @author _Alexzinv_
 * @date 2021/12/23
 * @description 个人信息常量
 */
public interface MemUserPersonalInfo {

    /**
     * 性别 */
    interface Sex {
        /**
         * 男 */
        int MAN = 1;
        /**
         * 女 */
        int WOMAN = 0;
    }

    /**
     * 婚姻状况 */
    interface Wedlock {
        /**
         * 未婚 */
        int UNMARRIED = 0;
        /**
         * 已婚 */
        int MARRIED = 1;
        /**
         * 离异 */
        int DIVORCE = 2;
    }

    interface PoliticsStatus {
        /**
         * 少先队员 */
        int YOUNG_PIONEERS = 1;
        /**
         * 共青团员 */
        int LEAGUE_MEMBER = 2;
        /**
         * 共产党员 */
        int COMMUNIST = 3;
        /**
         * 其他 */
        int OTHER = 4;
    }

    interface Education {
        /**
         * 初中及以下 */
        int JUNIOR_HIGH_SCHOOL = 1;
        /**
         * 高中 */
        int HIGH_SCHOOL = 2;
        /**
         * 大专 */
        int JUNIOR_COLLEGES = 3;
        /**
         * 本科 */
        int BACHELOR_DEGREE = 4;
        /**
         * 硕士 */
        int MASTER = 5;
        /**
         * 博士 */
        int DOCTOR = 6;
    }


}
