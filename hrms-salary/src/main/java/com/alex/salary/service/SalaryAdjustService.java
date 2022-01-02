package com.alex.salary.service;

import com.alex.salary.dto.SalaryAdjustQuery;
import com.alex.salary.entity.SalaryAdjust;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * 薪酬调整 服务类
 *
 * @author _Alexzinv_
 * @since 2022-01-01
 */
public interface SalaryAdjustService extends IService<SalaryAdjust> {

    /**
     * 分页多条件查询
     * @param page 当前页
     * @param limit 每页限制条数
     * @param query 查询条件
     * @return 分页数据
     */
    Page<SalaryAdjust> listPage(Integer page, Integer limit, SalaryAdjustQuery query);
}
