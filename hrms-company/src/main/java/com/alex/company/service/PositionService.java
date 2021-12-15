package com.alex.company.service;

import com.alex.company.dto.PositionQuery;
import com.alex.company.entity.Position;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * 岗位 服务类
 *
 * @author _Alexzinv_
 * @since 2021-12-15
 */
public interface PositionService extends IService<Position> {

    /**
     * 分页多条件查询
     * @param page 当前页
     * @param limit 每页限制条数
     * @param positionQuery 查询条件
     * @return 分页数据
     */
    Page<Position> listPage(Integer page, Integer limit, PositionQuery positionQuery);
}
