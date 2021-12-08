package com.alex.company.service;

import com.alex.company.dto.DepartmentQuery;
import com.alex.company.entity.Department;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 *
 * @author _Alexzinv_
 * @since 2021-11-08
 */
public interface DepartmentService extends IService<Department> {

    /**
     * 分页 根据公司id查询部门
     * @param page 当前页
     * @param limit 每页限制条数
     * @param departmentQuery 查询条件
     * @return 分页数据
     */
    Page<Department> listPage(Integer page, Integer limit, DepartmentQuery departmentQuery);
}
