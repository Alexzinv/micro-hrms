package com.alex.attendance;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.Banner;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

/**
 * @Author _Alexzinv_
 * @Date 2021/9/8
 * @Description 企业服务
 */
@MapperScan({"com.alex.attendance.mapper"})
@EnableDiscoveryClient
@ComponentScan({"com.alex"})
@EnableFeignClients
@SpringBootApplication
public class AttendanceApplication {

    public static void main(String[] args) {
        new SpringApplicationBuilder(AttendanceApplication.class)
                .bannerMode(Banner.Mode.OFF)
                .web(WebApplicationType.SERVLET)
                .run(args);
    }
}
