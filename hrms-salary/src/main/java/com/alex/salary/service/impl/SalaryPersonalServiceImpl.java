package com.alex.salary.service.impl;

import com.alex.common.base.BaseQuery;
import com.alex.salary.dto.SalaryPersonalQuery;
import com.alex.salary.entity.SalaryPersonal;
import com.alex.salary.mapper.SalaryPersonalMapper;
import com.alex.salary.service.SalaryPersonalService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * 员工薪酬个人表 服务实现类
 *
 * @author _Alexzinv_
 * @since 2022-01-01
 */
@Service
public class SalaryPersonalServiceImpl extends ServiceImpl<SalaryPersonalMapper, SalaryPersonal> implements SalaryPersonalService {

    @Override
    public void buildCondition(LambdaQueryWrapper<SalaryPersonal> wrapper, BaseQuery query) {
        if(query instanceof SalaryPersonalQuery){
            SalaryPersonalQuery sp = (SalaryPersonalQuery) query;

            // TODO query
        }
    }
}