package com.alex.company.service;

import com.alex.company.dto.CompanyQuery;
import com.alex.company.entity.Company;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 *
 * @author _Alexzinv_
 * @since 2021-11-06
 */
public interface CompanyService extends IService<Company> {

    /**
     * 分页多条件查询
     * @param page 当前页
     * @param limit 每页限制条数
     * @param companyQuery 查询条件
     * @return 分页数据
     */
    Page<Company> listPage(Integer page, Integer limit, CompanyQuery companyQuery);

}
