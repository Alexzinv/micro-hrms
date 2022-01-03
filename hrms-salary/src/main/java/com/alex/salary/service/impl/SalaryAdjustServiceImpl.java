package com.alex.salary.service.impl;

import com.alex.salary.dto.SalaryAdjustQuery;
import com.alex.salary.entity.SalaryAdjust;
import com.alex.salary.mapper.SalaryAdjustMapper;
import com.alex.salary.service.SalaryAdjustService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * 薪酬调整 服务实现类
 *
 * @author _Alexzinv_
 * @since 2022-01-01
 */
@Service
public class SalaryAdjustServiceImpl extends ServiceImpl<SalaryAdjustMapper, SalaryAdjust> implements SalaryAdjustService {

    @Override
    public Page<SalaryAdjust> listPage(Integer page, Integer limit, SalaryAdjustQuery query) {
        Page<SalaryAdjust> pageEntity = new Page<>(page, limit);
        LambdaQueryWrapper<SalaryAdjust> wrapper = Wrappers.lambdaQuery(SalaryAdjust.class);

        // TODO query
        return baseMapper.selectPage(pageEntity, wrapper);
    }
}
