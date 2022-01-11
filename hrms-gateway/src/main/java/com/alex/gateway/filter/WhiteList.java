package com.alex.gateway.filter;

import java.util.HashSet;

/**
 * @author _Alexzinv_
 * @date 2022/1/10
 * @description 白名单，无需token
 */
public abstract class WhiteList {

    public static final HashSet<String> WHITELIST = new HashSet<>();

    static {
        WHITELIST.add("/admin/acl/login");
        WHITELIST.add("/admin/acl/info/logout");
        WHITELIST.add("/api/**");
        WHITELIST.add("/swagger-resources/**");
        WHITELIST.add("/webjars/**");
        WHITELIST.add("/v2/**");
        WHITELIST.add("/swagger-ui.html/**");
        WHITELIST.add("/admin/acl/kaptcha/**");
        WHITELIST.add("/druid/stat");
    }
}
