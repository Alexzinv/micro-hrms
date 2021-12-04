package com.alex.member;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.Banner;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

/**
 * @Author _Alexzinv_
 * @Date 2021/9/22
 * @Description 用户信息
 */
@MapperScan({"com.alex.member.mapper"})
@EnableDiscoveryClient
@ComponentScan({"com.alex"})
@EnableFeignClients
@SpringBootApplication
public class MemberApplication {

    public static void main(String[] args) {
        new SpringApplicationBuilder(MemberApplication.class)
                .bannerMode(Banner.Mode.OFF)
                .web(WebApplicationType.SERVLET)
                .run(args);
    }
}
