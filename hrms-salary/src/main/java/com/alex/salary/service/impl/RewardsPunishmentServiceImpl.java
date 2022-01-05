package com.alex.salary.service.impl;

import com.alex.common.base.BaseQuery;
import com.alex.salary.dto.RewardsPunishmentQuery;
import com.alex.salary.entity.RewardsPunishment;
import com.alex.salary.mapper.RewardsPunishmentMapper;
import com.alex.salary.service.RewardsPunishmentService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.Data;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

/**
 * 奖惩 服务实现类
 *
 * @author _Alexzinv_
 * @since 2022-01-01
 */
@CacheConfig(cacheNames = "sa-rewardsPunishment")
@Service
public class RewardsPunishmentServiceImpl extends ServiceImpl<RewardsPunishmentMapper, RewardsPunishment> implements RewardsPunishmentService {

    @Override
    public void buildCondition(LambdaQueryWrapper<RewardsPunishment> wrapper, BaseQuery query) {
        if(query instanceof RewardsPunishmentQuery){
            RewardsPunishmentQuery rp = (RewardsPunishmentQuery) query;
            Integer type = rp.getRpType();
            Data start = rp.getStartTime();
            Data end = rp.getEndTime();

            wrapper.eq(type != null, RewardsPunishment::getRpType, type)
                    .gt(start != null, RewardsPunishment::getCreateTime, start)
                    .eq(end != null, RewardsPunishment::getCreateTime, end);
        }
    }
}
