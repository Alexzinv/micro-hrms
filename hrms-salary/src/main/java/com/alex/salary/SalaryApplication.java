package com.alex.salary;

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
 * @Date 2022/1/1
 * @Description 薪酬管理
 */
@MapperScan({"com.alex.salary.mapper"})
@EnableDiscoveryClient
@ComponentScan({"com.alex"})
@EnableFeignClients
@SpringBootApplication
public class SalaryApplication {

    public static void main(String[] args) {
        new SpringApplicationBuilder(SalaryApplication.class)
                .bannerMode(Banner.Mode.OFF)
                .web(WebApplicationType.SERVLET)
                .run(args);
    }
}
