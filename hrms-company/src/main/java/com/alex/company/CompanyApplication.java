package com.alex.company;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

/**
 * @Author _Alexzinv_
 * @Date 2021/9/8
 * @Description 企业服务
 */
@MapperScan({"com.alex.company.mapper"})
@EnableDiscoveryClient
@ComponentScan({"com.alex"})
@SpringBootApplication
public class CompanyApplication {

    public static void main(String[] args) {
        SpringApplication.run(com.alex.company.CompanyApplication.class, args);
    }
}
