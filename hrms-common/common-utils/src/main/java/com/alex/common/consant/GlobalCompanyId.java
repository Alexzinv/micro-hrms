package com.alex.common.consant;

/**
 * @author _Alexzinv_
 * @date 2021/12/10
 * @description 在用户登录二阶段认证时，获取当前用户关联的公司id，并暂时存储，
 *              提供给当前用户全局业务使用，充当类似于一个抽象的常量的作用
 */
public class GlobalCompanyId {

    private static volatile ThreadLocal<Long> companyId = null;

    private GlobalCompanyId() { }

    public static void setCompanyId(Long id){
        if(companyId == null){
            synchronized (GlobalCompanyId.class){
                if(companyId == null){
                    companyId = new ThreadLocal<>();
                }
            }
        }
        companyId.set(id);
    }

    public static Long getCompanyId() {
        return companyId != null ? companyId.get() : null;
    }

    public static void remove() {
        if (companyId != null) {
            companyId.remove();
        }
    }
}
