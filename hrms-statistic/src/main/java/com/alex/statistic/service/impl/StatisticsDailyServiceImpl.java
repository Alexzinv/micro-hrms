package com.alex.statistic.service.impl;

import com.alex.common.base.AbstractBaseQuery;
import com.alex.statistic.client.SystemClient;
import com.alex.statistic.entity.StatisticsDaily;
import com.alex.statistic.mapper.StatisticsDailyMapper;
import com.alex.statistic.service.StatisticsDailyService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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

    /**
     * @param date 日期
     * 初始化数据
     */
    @Override
    public void countDaily(String date) {
        StatisticsDaily statisticsDaily = new StatisticsDaily();
        Integer registerCount = systemClient.registerCount(date);
        statisticsDaily.setRegisterCount(registerCount);
        statisticsDaily.setCalculateDate(date);
        statisticsDaily.setLoginCount(0);
        statisticsDaily.setRegisterCount(0);
        try {
            super.save(statisticsDaily);
        } catch (Exception ignored) {}
    }

    @Override
    public void loginCount(String date) {
        LambdaQueryWrapper<StatisticsDaily> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(StatisticsDaily::getCalculateDate, date);
        if(super.count(wrapper) <= 0){
            this.countDaily(date);
        }
        baseMapper.loginCount(date);
    }

    @Override
    public void registerCount(String date) {
        LambdaQueryWrapper<StatisticsDaily> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(StatisticsDaily::getCalculateDate, date);
        if(super.count(wrapper) <= 0){
            this.countDaily(date);
        }
        baseMapper.registerCount(date);
    }

    @Override
    public Map<String, Object> showData(String begin, String end) {
        LambdaQueryWrapper<StatisticsDaily> wrapper = new LambdaQueryWrapper<>();
        if (StringUtils.isNotBlank(begin)) {
            wrapper.ge(StatisticsDaily::getCalculateDate, begin);
        }
        if (StringUtils.isNotBlank(end)) {
            wrapper.le(StatisticsDaily::getCalculateDate, end);
        }
        wrapper.orderByAsc(StatisticsDaily::getCalculateDate);
        List<StatisticsDaily> list = super.list(wrapper);

        // 找出行date数据
        List<String> dates = list.stream().map(StatisticsDaily::getCalculateDate).collect(Collectors.toList());
        List<List<Integer>> counts = new ArrayList<>();
        counts.add(list.stream().map(StatisticsDaily::getRegisterCount).collect(Collectors.toList()));
        counts.add(list.stream().map(StatisticsDaily::getLoginCount).collect(Collectors.toList()));

        // 记录名字
        List<String> names = new ArrayList<>();
        names.add("每日注册人数");
        names.add("每日登陆人数");

        Map<String, Object> map = new HashMap<>();
        map.put("dateList", dates);
        map.put("countList", counts);
        map.put("names", names);
        return map;
    }
}
