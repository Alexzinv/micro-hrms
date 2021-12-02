package com.alex.member.service;

import com.alex.member.dto.UserCompanyQuery;
import com.alex.member.entity.UserCompany;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * user公司扩展表 服务类
 * </p>
 *
 * @author _Alexzinv_
 * @since 2021-12-02
 */
public interface UserCompanyService extends IService<UserCompany> {

    /**
     * 分页多条件查询
     * @param page 当前页
     * @param limit 每页限制条数
     * @param query 查询条件
     * @return 分页数据
     */
    Page<UserCompany> listPage(Integer page, Integer limit, UserCompanyQuery query);
}
