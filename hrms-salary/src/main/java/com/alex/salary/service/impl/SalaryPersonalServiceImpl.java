package com.alex.salary.service.impl;

import com.alex.salary.dto.SalaryPersonalQuery;
import com.alex.salary.entity.SalaryPersonal;
import com.alex.salary.mapper.SalaryPersonalMapper;
import com.alex.salary.service.SalaryPersonalService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
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
    public Page<SalaryPersonal> listPage(Integer page, Integer limit, SalaryPersonalQuery query) {
        Page<SalaryPersonal> pageEntity = new Page<>(page, limit);
        LambdaQueryWrapper<SalaryPersonal> wrapper = Wrappers.lambdaQuery(SalaryPersonal.class);

        // TODO query
        return baseMapper.selectPage(pageEntity, wrapper);
    }
}
