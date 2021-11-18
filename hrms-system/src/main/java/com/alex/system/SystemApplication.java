package com.alex.system;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

/**
 * @Author _Alexzinv_
 * @Date 2021/9/22
 * @Description 用户权限
 */
@MapperScan({"com.alex.system.mapper"})
@EnableDiscoveryClient
@ComponentScan({"com.alex"})
@SpringBootApplication
public class SystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(SystemApplication.class, args);
    }
}
