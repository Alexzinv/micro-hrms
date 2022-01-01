package com.alex.salary;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/**
 * @author _Alexzinv_
 * @date 2022/1/1
 * @description 允许应用程序在启动时对servlet进行配置，开启支持打WAR包使用外部tomcat
 */
public class StartSupportApplication extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(SalaryApplication.class);
    }
}
