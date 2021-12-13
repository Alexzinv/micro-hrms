package com.alex.common.consant;

/**
 * @author _Alexzinv_
 * @date 2021/12/10
 * @description 在用户登录二阶段认证时，获取当前用户关联的公司id，并暂时存储，
 *              提供给当前用户全局业务使用，充当类似于一个抽象的只针对当前用户对常量的作用
 */
public class GlobalCompanyId {

    private static volatile ThreadLocal<Long> companyId = null;
    private GlobalCompanyId() { }

    /**
     * 放入当前登录用户关联的公司id，只能设置一次，作为全局变量使用
     * @param id 公司id
     */
    public static void setCompanyId(Long id){
        if(companyId == null){
            synchronized (GlobalCompanyId.class){
                if(companyId == null){
                    companyId = new ThreadLocal<>();
                }
            }
        }
        if(companyId.get() != null){
            companyId.set(id);
        }
    }

    /**
     * 获取当前用户全局公司id
     * @return 公司id
     */
    public static Long getCompanyId() {
        return companyId != null ? companyId.get() : null;
    }

    /**
     * 清空当前用户已有值，[用户登出的时候调用]
     */
    public static void remove() {
        if (companyId != null) {
            companyId.remove();
        }
    }
}
