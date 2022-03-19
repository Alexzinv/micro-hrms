package com.alex.statistic.service;

import com.alex.common.base.BaseService;
import com.alex.statistic.entity.StatisticsDaily;

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
}
