package com.alex.salary.service.impl;

import com.alex.common.base.AbstractBaseQuery;
import com.alex.salary.dto.SalaryPersonalQuery;
import com.alex.salary.entity.SalaryAdjust;
import com.alex.salary.entity.SalaryCommon;
import com.alex.salary.entity.SalaryPersonal;
import com.alex.salary.mapper.SalaryPersonalMapper;
import com.alex.salary.service.SalaryAdjustService;
import com.alex.salary.service.SalaryCommonService;
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
    @Autowired
    private SalaryCommonService salaryCommonService;

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

    @Override
    public boolean save(SalaryPersonal entity) {
        Long salaryCommonId = entity.getSalaryCommonId();
        BigDecimal commonSalary = computeCommonSalary(salaryCommonId);

        return false;
    }

    private BigDecimal computeCommonSalary(Long salaryCommonId){
        SalaryCommon salaryCommon = salaryCommonService.getById(salaryCommonId);
        if(salaryCommon == null){
            return BigDecimal.ZERO;
        }
        BigDecimal total = new BigDecimal("0.00");
        BigDecimal lunchSalary = salaryCommon.getLunchSalary();
        BigDecimal trafficSalary = salaryCommon.getTrafficSalary();


        total.add(lunchSalary).add(trafficSalary);
        return total;
    }
}
