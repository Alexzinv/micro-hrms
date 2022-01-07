package com.alex.company.service;

import com.alex.common.base.BaseService;
import com.alex.company.dto.ParentDepartmentVO;
import com.alex.company.entity.Department;

import java.util.List;

/**
 *
 * @author _Alexzinv_
 * @since 2021-11-08
 */
public interface DepartmentService extends BaseService<Department> {

    /**
     * 根据公司id查询部门，提供给选择器
     * @param companyId 公司id
     * @return 部门列表
     */
    List<ParentDepartmentVO> listParentDepartment(Long companyId);
}
