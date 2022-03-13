package com.alex.statistic.service.impl;

import com.alex.common.base.AbstractBaseQuery;
import com.alex.statistic.entity.StatisticsCompanyData;
import com.alex.statistic.mapper.StatisticsCompanyDataMapper;
import com.alex.statistic.service.StatisticsCompanyDataService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 月公司人数变动统计 服务实现类
 * </p>
 *
 * @author _Alexzinv_
 * @since 2022-03-13
 */
@Service
public class StatisticsCompanyDataServiceImpl extends ServiceImpl<StatisticsCompanyDataMapper, StatisticsCompanyData> implements StatisticsCompanyDataService {

    @Override
    public void buildCondition(LambdaQueryWrapper<StatisticsCompanyData> wrapper, AbstractBaseQuery query) {

    }
}
