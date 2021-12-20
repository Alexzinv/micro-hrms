package com.alex.statistic;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.Banner;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author _Alexzinv_
 * @date 2021/12/20
 * @description 统计
 */
@MapperScan({"com.alex.statistic.mapper"})
@EnableDiscoveryClient
@ComponentScan({"com.alex"})
@EnableFeignClients
@SpringBootApplication
public class StatisticApplication {

    public static void main(String[] args) {
        new SpringApplicationBuilder(StatisticApplication.class)
                .bannerMode(Banner.Mode.OFF)
                .web(WebApplicationType.SERVLET)
                .run(args);
    }
}
