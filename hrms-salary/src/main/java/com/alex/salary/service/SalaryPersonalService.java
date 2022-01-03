package com.alex.salary.service;

import com.alex.salary.dto.SalaryPersonalQuery;
import com.alex.salary.entity.SalaryPersonal;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * 员工薪酬个人表 服务类
 *
 * @author _Alexzinv_
 * @since 2022-01-01
 */
public interface SalaryPersonalService extends IService<SalaryPersonal> {

    /**
     * 分页多条件查询
     * @param page 当前页
     * @param limit 每页限制条数
     * @param query 查询条件
     * @return 分页数据
     */
    Page<SalaryPersonal> listPage(Integer page, Integer limit, SalaryPersonalQuery query);
}
