package com.alex.statistic.schedule;

import com.alex.statistic.service.StatisticsDailyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

/**
 * @author _Alexzinv_
 * @date 2022/3/19
 * @description
 */
@Component
public class StatCountSchedule {

    @Autowired
    private StatisticsDailyService statisticsDailyService;

    @Scheduled(cron = "1 0 0 * * ?", zone = "GMT-8")
    public void count() {
        statisticsDailyService.countDaily(LocalDate.now().plusDays(-1L).toString());
    }
}
