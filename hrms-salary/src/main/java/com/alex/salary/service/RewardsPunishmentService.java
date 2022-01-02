package com.alex.salary.service;

import com.alex.salary.dto.RewardsPunishmentQuery;
import com.alex.salary.entity.RewardsPunishment;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * 奖惩 服务类
 *
 * @author _Alexzinv_
 * @since 2022-01-01
 */
public interface RewardsPunishmentService extends IService<RewardsPunishment> {

    /**
     * 分页多条件查询
     * @param page 当前页
     * @param limit 每页限制条数
     * @param query 查询条件
     * @return 分页数据
     */
    Page<RewardsPunishment> listPage(Integer page, Integer limit, RewardsPunishmentQuery query);
}
