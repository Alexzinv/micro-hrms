package com.alex.common.config;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

/**
 * @author _Alexzinv_
 * @date 2021/12/27
 * @description
 */
@Configuration
public class DruidConfig {

    @Bean
    public DataSource druid(){
        return DruidDataSourceBuilder.create().build();
    }
}
