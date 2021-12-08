package com.alex.company.service.impl;

import com.alex.company.dto.DepartmentQuery;
import com.alex.company.entity.Department;
import com.alex.company.mapper.DepartmentMapper;
import com.alex.company.service.DepartmentService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 *
 * @author _Alexzinv_
 * @since 2021-11-08
 */
@Service
public class DepartmentServiceImpl extends ServiceImpl<DepartmentMapper, Department> implements DepartmentService {

    @Override
    public Page<Department> listPage(Integer page, Integer limit, DepartmentQuery departmentQuery) {
        Page<Department> pageEntity = new Page<>(page, limit);

        QueryWrapper<Department> wrapper = new QueryWrapper<>();

        Long companyId = departmentQuery.getCompanyId();
        if(companyId == null){
            return baseMapper.selectPage(pageEntity, null);
        }
        wrapper.eq("company_id", companyId);

        String keyWord = departmentQuery.getKey();
        String manager = departmentQuery.getManager();
        if(StringUtils.hasText(keyWord)){
            wrapper.like("code", keyWord).or()
                    .like("name", keyWord);
        }
        wrapper.eq(StringUtils.hasText(manager), "manager", manager);

        return baseMapper.selectPage(pageEntity, wrapper);
    }
}
