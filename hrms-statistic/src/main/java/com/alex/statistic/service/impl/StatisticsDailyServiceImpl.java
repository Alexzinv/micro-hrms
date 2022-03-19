package com.alex.statistic.service.impl;

import com.alex.common.base.AbstractBaseQuery;
import com.alex.common.util.R;
import com.alex.statistic.client.SystemClient;
import com.alex.statistic.entity.StatisticsDaily;
import com.alex.statistic.mapper.StatisticsDailyMapper;
import com.alex.statistic.service.StatisticsDailyService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;

/**
 * <p>
 * 网站统计日数据 服务实现类
 * </p>
 *
 * @author _Alexzinv_
 * @since 2022-03-13
 */
@Service
public class StatisticsDailyServiceImpl extends ServiceImpl<StatisticsDailyMapper, StatisticsDaily> implements StatisticsDailyService {

    @Autowired
    private SystemClient systemClient;

    @Override
    public void buildCondition(LambdaQueryWrapper<StatisticsDaily> wrapper, AbstractBaseQuery query) {

    }

    @Override
    public void countDaily(String date) {
        StatisticsDaily statisticsDaily = new StatisticsDaily();
        R r = systemClient.registerCount(date);
        Integer registerCount = (Integer) r.getData().get("registerCount");
        statisticsDaily.setRegisterCount(registerCount);
        statisticsDaily.setCalculateDate(Calendar.getInstance().getTime());

    }
}
