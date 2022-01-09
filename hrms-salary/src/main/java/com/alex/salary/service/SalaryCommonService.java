package com.alex.salary.service;

import com.alex.salary.entity.SalaryCommon;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * 员工薪酬公共表 服务类
 *
 * @author _Alexzinv_
 * @since 2022-01-01
 */
public interface SalaryCommonService extends IService<SalaryCommon> {

    /**
     * 根据companyId查询
     * @param companyId 公司id
     * @return 对象
     */
    SalaryCommon getByCompanyId(Long companyId);
}
