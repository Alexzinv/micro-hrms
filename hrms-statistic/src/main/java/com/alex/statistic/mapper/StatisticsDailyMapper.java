package com.alex.statistic.mapper;

import com.alex.common.cache.MybatisRedisCacheConfig;
import com.alex.statistic.entity.StatisticsDaily;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.CacheNamespace;

/**
 * <p>
 * 网站统计日数据 Mapper 接口
 * </p>
 *
 * @author _Alexzinv_
 * @since 2022-03-13
 */
@CacheNamespace(implementation = MybatisRedisCacheConfig.class, eviction = MybatisRedisCacheConfig.class)
public interface StatisticsDailyMapper extends BaseMapper<StatisticsDaily> {

    /**
     * 登录统计
     * @param date 日期
     */
    void loginCount(String date);

    /**
     * 注册统计
     * @param date 日期
     */
    void registerCount(String date);
}
