package com.alex.salary.service.impl;

import com.alex.salary.dto.RewardsPunishmentQuery;
import com.alex.salary.entity.RewardsPunishment;
import com.alex.salary.mapper.RewardsPunishmentMapper;
import com.alex.salary.service.RewardsPunishmentService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.Data;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
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

    @Cacheable
    @Override
    public Page<RewardsPunishment> listPage(Integer page, Integer limit, RewardsPunishmentQuery query) {
        Page<RewardsPunishment> pageEntity = new Page<>(page, limit);
        Integer type = query.getRpType();
        Data start = query.getStartTime();
        Data end = query.getEndTime();
        LambdaQueryWrapper<RewardsPunishment> wrapper = Wrappers.lambdaQuery(RewardsPunishment.class);
        wrapper.eq(type != null, RewardsPunishment::getRpType, type)
                .gt(start != null, RewardsPunishment::getCreateTime, start)
                .eq(end != null, RewardsPunishment::getCreateTime, end);
        return baseMapper.selectPage(pageEntity, wrapper);
    }
}
