package com.alex.salary.service.impl;

import com.alex.common.base.AbstractBaseQuery;
import com.alex.salary.dto.SalaryPersonalQuery;
import com.alex.salary.entity.SalaryAdjust;
import com.alex.salary.entity.SalaryPersonal;
import com.alex.salary.mapper.SalaryPersonalMapper;
import com.alex.salary.service.SalaryAdjustService;
import com.alex.salary.service.SalaryPersonalService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

/**
 * 员工薪酬个人表 服务实现类
 *
 * @author _Alexzinv_
 * @since 2022-01-01
 */
@Service
public class SalaryPersonalServiceImpl extends ServiceImpl<SalaryPersonalMapper, SalaryPersonal> implements SalaryPersonalService {

    @Autowired
    private SalaryAdjustService salaryAdjustService;

    @Override
    public void buildCondition(LambdaQueryWrapper<SalaryPersonal> wrapper, AbstractBaseQuery query) {
        if(query instanceof SalaryPersonalQuery){
            SalaryPersonalQuery sp = (SalaryPersonalQuery) query;

            // TODO query
        }
    }

    @Override
    public void update(SalaryPersonal sp) {
        BigDecimal basicSalary = sp.getBasicSalary();
        if(basicSalary != null){
            Long spId = sp.getId();
            SalaryPersonal salaryPersonal = super.getById(spId);
            SalaryAdjust salaryAdjust = new SalaryAdjust();
            salaryAdjust.setSalaryPersonalId(spId);
            salaryAdjust.setBeforeSalary(salaryPersonal.getBasicSalary());
            salaryAdjust.setAfterSalary(basicSalary);
            salaryAdjustService.save(salaryAdjust);
        }
        super.updateById(sp);
    }


}
