package com.alex.statistic.service.impl;

import com.alex.common.base.AbstractBaseQuery;
import com.alex.statistic.entity.StatisticsTotal;
import com.alex.statistic.mapper.StatisticsTotalMapper;
import com.alex.statistic.service.StatisticsTotalService;
import com.alex.statistic.vo.StatisticsTotalQuery;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 网站总数统计 服务实现类
 * </p>
 *
 * @author _Alexzinv_
 * @since 2022-03-13
 */
@Service
public class StatisticsTotalServiceImpl extends ServiceImpl<StatisticsTotalMapper, StatisticsTotal> implements StatisticsTotalService {

    @Override
    public void buildCondition(LambdaQueryWrapper<StatisticsTotal> wrapper, AbstractBaseQuery query) {
        if(query == null){
            return;
        }
        if(query instanceof StatisticsTotalQuery){
            StatisticsTotalQuery condition = (StatisticsTotalQuery) query;
            wrapper.eq(StatisticsTotal::getCreateTime, condition.getCreateTime());
        }
    }
}
