package com.alex.statistic.service.impl;

import com.alex.common.base.AbstractBaseQuery;
import com.alex.statistic.entity.StatisticsMonthly;
import com.alex.statistic.mapper.StatisticsMonthlyMapper;
import com.alex.statistic.service.StatisticsMonthlyService;
import com.alex.statistic.vo.StatisticsMonthlyQuery;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 网站统计月数据 服务实现类
 * </p>
 *
 * @author _Alexzinv_
 * @since 2022-03-13
 */
@Service
public class StatisticsMonthlyServiceImpl extends ServiceImpl<StatisticsMonthlyMapper, StatisticsMonthly> implements StatisticsMonthlyService {

    @Override
    public void buildCondition(LambdaQueryWrapper<StatisticsMonthly> wrapper, AbstractBaseQuery query) {
        if(query instanceof StatisticsMonthlyQuery){
            StatisticsMonthlyQuery condition = (StatisticsMonthlyQuery) query;

        }
    }
}
