package com.alex.company.service.impl;

import com.alex.common.bean.member.UserCompanyDepartmentPositionTo;
import com.alex.common.consant.CodePrefixEnum;
import com.alex.common.consant.ResultCodeEnum;
import com.alex.common.exception.HRMSException;
import com.alex.common.util.CodePrefixUtils;
import com.alex.company.client.MemberUserCompanyClient;
import com.alex.company.dto.DepartmentQuery;
import com.alex.company.entity.Department;
import com.alex.company.mapper.DepartmentMapper;
import com.alex.company.service.DepartmentService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.seata.spring.annotation.GlobalTransactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 *
 * @author _Alexzinv_
 * @since 2021-11-08
 */
@Service
public class DepartmentServiceImpl extends ServiceImpl<DepartmentMapper, Department> implements DepartmentService {

    private MemberUserCompanyClient memberUserCompanyClient;

    @Autowired
    public void setMemberUserCompanyClient(MemberUserCompanyClient memberUserCompanyClient) {
        this.memberUserCompanyClient = memberUserCompanyClient;
    }

    @Override
    public Page<Department> listPage(Integer page, Integer limit, DepartmentQuery departmentQuery) {
        Page<Department> pageEntity = new Page<>(page, limit);
        QueryWrapper<Department> wrapper = new QueryWrapper<>();

        Long companyId = departmentQuery.getCompanyId();
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
     * 更新本地部门名字需要同步更新关联表冗余字段
     * 部门编码不可更改
     * @param entity department
     * @return save-status */
    @GlobalTransactional(rollbackFor = Exception.class)
    @Override
    public boolean updateById(Department entity) {
        String name = entity.getName();
        if(StringUtils.hasText(name)){
            UserCompanyDepartmentPositionTo to = new UserCompanyDepartmentPositionTo();
            to.setDepartmentId(entity.getId());
            to.setDepartmentName(name);
            memberUserCompanyClient.updateUserCompanyDepartmentPosition(to);
        }
        entity.setCode(null);
        return super.updateById(entity);
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
