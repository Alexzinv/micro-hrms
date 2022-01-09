package com.alex.salary.service.impl;

import com.alex.salary.entity.SalaryCommon;
import com.alex.salary.mapper.SalaryCommonMapper;
import com.alex.salary.service.SalaryCommonService;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * 员工薪酬公共表 服务实现类
 *
 * @author _Alexzinv_
 * @since 2022-01-01
 */
@Service
public class SalaryCommonServiceImpl extends ServiceImpl<SalaryCommonMapper, SalaryCommon> implements SalaryCommonService {

    @Override
    public SalaryCommon getByCompanyId(Long companyId) {
        return super.getOne(Wrappers.lambdaQuery(SalaryCommon.class).eq(SalaryCommon::getCompanyId, companyId));
    }
}
