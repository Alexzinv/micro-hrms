package com.alex.company.controller;


import com.alex.common.util.R;
import com.alex.company.dto.DepartmentQuery;
import com.alex.company.dto.DepartmentVO;
import com.alex.company.dto.struct.DepartmentStruct;
import com.alex.company.entity.Company;
import com.alex.company.entity.Department;
import com.alex.company.service.CompanyService;
import com.alex.company.service.DepartmentService;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 *
 * @author _Alexzinv_
 * @since 2021-11-08
 * @description 部门控制器
 */
@RestController
@RequestMapping("/company/department")
public class DepartmentController {

    private final CompanyService companyService;
    private final DepartmentService departmentService;

    @Autowired
    DepartmentController(DepartmentService departmentService, CompanyService companyService){
        this.departmentService = departmentService;
        this.companyService = companyService;
    }

    @GetMapping("/get/{id}")
    public R getDepartment(@PathVariable("id") Long id){
        Department department = departmentService.getById(id);
        return R.ok().data("data", department);
    }

    @GetMapping("/list/{companyId}")
    public R listDepartmentByCompanyId(@PathVariable("companyId") Long companyId){
        List<Department> departments = departmentService.list(
                Wrappers.lambdaQuery(Department.class).eq(Department::getCompanyId, companyId)
        );
        return R.ok().data("departments", departments);
    }

    @PostMapping("/list/{page}/{limit}")
    public R listDepartmentPageByCompanyIdCondition(@PathVariable("page") Integer page,
                                           @PathVariable("limit") Integer limit,
                                           @RequestBody(required = true) DepartmentQuery departmentQuery){
        Long companyId = departmentQuery.getCompanyId();
        Page<Department> result = new Page<>();
        DepartmentVO departmentVO = null;
        if(companyId != null){
            Company company = companyService.getById(companyId);
            result = departmentService.listPage(page, limit, departmentQuery);
            departmentVO = DepartmentStruct.INSTANCE.entity2VO(company, result.getRecords());
        }
        return R.ok().data("records", departmentVO).data("total", result.getTotal());
    }

    @PostMapping("/save")
    public R saveDepartment(@RequestBody Department department){
        departmentService.save(department);
        return R.ok();
    }

    @PostMapping("/update")
    public R updateDepartment(@RequestBody Department department){
        departmentService.updateById(department);
        return R.ok();
    }

    @DeleteMapping("/delete/{id}")
    public R deleteDepartment(@PathVariable("id") Long id){
        departmentService.removeById(id);
        return R.ok();
    }
}

