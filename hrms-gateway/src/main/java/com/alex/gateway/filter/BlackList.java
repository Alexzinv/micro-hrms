package com.alex.gateway.filter;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

import java.util.HashSet;

/**
 * @author _Alexzinv_
 * @date 2022/1/10
 * @description 不可访问名单
 */
@Component
@RefreshScope
@ConfigurationProperties(prefix = "url.forbidden")
public class BlackList {

    private HashSet<String> blackList = new HashSet<>();

    public HashSet<String> getBlackList() {
        return blackList;
    }

    public void setBlackList(HashSet<String> blackList) {
        this.blackList = blackList;
    }
}
