package com.alex.gateway.filter;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

import java.util.HashSet;

/**
 * @author _Alexzinv_
 * @date 2022/1/10
 * @description 白名单，无需token
 */
@Component
@RefreshScope
@ConfigurationProperties(prefix = "url.allow")
public class WhiteList {

    private HashSet<String> whiteList = new HashSet<>();

    public HashSet<String> getWhiteList() {
        return whiteList;
    }

    public void setWhiteList(HashSet<String> whiteList) {
        this.whiteList = whiteList;
    }
}
