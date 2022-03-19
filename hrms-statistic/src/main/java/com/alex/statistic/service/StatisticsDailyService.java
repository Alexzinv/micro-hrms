package com.alex.statistic.service;

import com.alex.common.base.BaseService;
import com.alex.statistic.entity.StatisticsDaily;

import java.util.Map;

/**
 * <p>
 * 网站统计日数据 服务类
 * </p>
 *
 * @author _Alexzinv_
 * @since 2022-03-13
 */
public interface StatisticsDailyService extends BaseService<StatisticsDaily> {

    /**
     * 统计某天信息
     * @param date 日期
     */
    void countDaily(String date);

    /**
     * 统计登录某天信息
     * @param date 日期
     */
    void loginCount(String date);

    /**
     * 统计某天注册信息
     * @param date 日期
     */
    void registerCount(String date);

    /**
     * 展示某时间段数据
     */
    Map<String, Object> showData(String begin, String end);
}
