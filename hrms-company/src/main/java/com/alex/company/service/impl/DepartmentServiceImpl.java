package com.alex.company.service.impl;

import com.alex.common.consant.CodePrefixEnum;
import com.alex.common.consant.ResultCodeEnum;
import com.alex.common.exception.HRMSException;
import com.alex.common.util.CodePrefixUtils;
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
        String name = entity.getName();
        if(StringUtils.hasText(name) && !isExist(name)){
            Long companyId = entity.getCompanyId();
            entity.setCode(getCode(companyId));
            return super.save(entity);
        }
        throw new HRMSException(ResultCodeEnum.EXISTS_EXCEPTION);
    }

    /**
     * 生成部门code
     * @param companyId 公司id
     * @return code
     */
    private String getCode(Long companyId) {
        CodePrefixUtils utils = () -> baseMapper.getLatestCode(companyId);
        return utils.getCode(CodePrefixEnum.DEPARTMENT_CODE_PREFIX);
    }

    /**
     * 部门是否已经存在
     * @param name 部门名
     * @return 是否存在
     */
    private boolean isExist(String name){
        int count = super.count(new QueryWrapper<Department>().eq("name", name));
        return count > 0;
    }
}
