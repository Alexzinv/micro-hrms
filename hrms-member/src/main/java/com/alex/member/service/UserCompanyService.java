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
     * 分页多条件查询公司内的所有员工
     * @param page 当前页
     * @param limit 每页限制条数
     * @param query 查询条件
     * @return 分页数据
     */
    Page<UserCompany> listPage(Integer page, Integer limit, UserCompanyQuery query);

    /**
     * 更新公司员工信息
     * @param userCompany 公司员工
     * @return isUpdate
     */
    boolean update(UserCompany userCompany);
}
