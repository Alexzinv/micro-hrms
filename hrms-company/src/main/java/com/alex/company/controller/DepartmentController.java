package com.alex.company.controller;


import com.alex.common.util.R;
import com.alex.company.dto.DepartmentQuery;
import com.alex.company.dto.DepartmentVO;
import com.alex.company.dto.struct.DepartmentStruct;
import com.alex.company.entity.Company;
import com.alex.company.entity.Department;
import com.alex.company.service.CompanyService;
import com.alex.company.service.DepartmentService;
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
    public R get(@PathVariable("id") Long id){
        Department department = departmentService.getById(id);
        return R.ok().data("data", department);
    }

    @GetMapping("/list")
    public R list(){
        List<Department> departmentList = departmentService.list();
        return R.ok().data("data", departmentList);
    }

    @PostMapping("/list/{page}/{limit}/{companyId}")
    public R listByCompanyId(@PathVariable("page") Integer page,
                             @PathVariable("limit") Integer limit,
                             @PathVariable("companyId") Long companyId){
        Company company = companyService.getById(companyId);
        Page<Department> result = departmentService.listPage(page, limit, companyId);
        DepartmentVO departmentVO = DepartmentStruct.INSTANCE.entity2VO(company, result.getRecords());
        return R.ok().data("data", departmentVO).data("total", result.getTotal());
    }

    @PostMapping("/list/{page}/{limit}")
    public R listCondition(@PathVariable Integer page,
                           @PathVariable Integer limit,
                           @RequestBody(required = false) DepartmentQuery departmentQuery){
        Page<Department> result = departmentService.listPage(page, limit, departmentQuery);
        return R.ok().data("records", result.getRecords()).data("total", result.getTotal());
    }

    @PostMapping("/save")
    public R save(@RequestBody Department department){
        boolean isSave = departmentService.save(department);
        return isSave ? R.ok() : R.err();
    }

    @PostMapping("/update")
    public R update(@RequestBody Department department){
        boolean isUpdate = departmentService.updateById(department);
        return isUpdate ? R.ok() : R.err();
    }

    @DeleteMapping("/delete/{id}")
    public R update(@PathVariable("id") Long id){
        departmentService.removeById(id);
        return R.ok();
    }

}

