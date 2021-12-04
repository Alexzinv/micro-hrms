package com.alex.member;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/**
 * @author _Alexzinv_
 * @date 2021/12/3
 * @description 允许应用程序在启动时对servlet进行配置，开启支持打WAR包使用外部tomcat
 */
public class StartSupportApplication extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(MemberApplication.class);
    }
}
