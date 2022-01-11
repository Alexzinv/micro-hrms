package com.alex.gateway.util;

import org.apache.commons.lang.StringUtils;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.CollectionUtils;

import java.util.Set;

/**
 * @author _Alexzinv_
 * @date 2022/1/11
 * @description 访问url过滤器
 */
public class AccessFilterUtils {

    private static final AntPathMatcher ANTPATHMATCHER = new AntPathMatcher();

    /**
     * 匹配url是否满足规则里的一项
     * @param url 地址
     * @param patterns 匹配项
     * @return 匹配结果
     */
    public static boolean match(String url, Set<String> patterns){
        boolean isBlank = StringUtils.isBlank(url) || CollectionUtils.isEmpty(patterns);
        if(isBlank){
            return false;
        }
        for (String p : patterns) {
            if(ANTPATHMATCHER.match(p, url)){
                return true;
            }
        }
        return false;
    }
}
