package com.alex.company.service.impl;

import com.alex.common.consant.CompanyConstant;
import com.alex.common.util.CustomSerialGenerator;
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

    @Override
    public boolean save(Department entity) {
        Long companyId = entity.getCompanyId();
        entity.setCode(getCode(companyId));
        return super.save(entity);
    }

    /**
     * 生成部门code
     * @param companyId 公司id
     * @return code
     */
    private String getCode(Long companyId) {
        // 查询出当前公司部门编码列最新值
        String code = baseMapper.getLatestCode(companyId);
        // 为空则是第一次添加，初始化，否则按最大值自增
        if(code == null){
            return CompanyConstant.DEPARTMENT_CODE_PREFIX + CustomSerialGenerator.initCode();
        }
        String newCode = code.replace(CompanyConstant.DEPARTMENT_CODE_PREFIX, "");
        long value = Long.parseLong(newCode);
        ++value;
        return CompanyConstant.DEPARTMENT_CODE_PREFIX + value;
    }
}
