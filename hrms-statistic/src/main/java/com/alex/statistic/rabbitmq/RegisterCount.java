package com.alex.statistic.rabbitmq;

import com.alex.statistic.service.StatisticsDailyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.function.Consumer;

/**
 * @author _Alexzinv_
 * @date 2022/3/19
 * @description
 */
@Component
public class RegisterCount {

    @Autowired
    private StatisticsDailyService statisticsDailyService;

    @Bean
    public Consumer<String> statLoginCount(){
        return date -> statisticsDailyService.registerCount(date);
    }
}
