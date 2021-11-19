package com.alex.company.controller;

import com.alex.common.util.R;
import com.alex.company.dto.CompanyQuery;
import com.alex.company.entity.Company;
import com.alex.company.service.CompanyService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author _Alexzinv_
 * @since 2021-11-06
 * @description 公司控制器
 */
@RestController
@RequestMapping("/company/company")
public class CompanyController {

    private final CompanyService companyService;

    @Autowired
    CompanyController(CompanyService companyService){
        this.companyService = companyService;
    }

    @GetMapping("/get/{id}")
    public R get(@PathVariable("id") Long id){
        Company company = companyService.getById(id);
        return R.ok().data("data", company);
    }

    @GetMapping("/list")
    public R list(){
        List<Company> companyList = companyService.list();
        return R.ok().data("data", companyList);
    }

    @PostMapping("/list/{page}/{limit}")
    public R listCondition(@PathVariable Integer page,
                           @PathVariable Integer limit,
                           @RequestBody(required = false) CompanyQuery companyQuery){

        Page<Company> result = companyService.listPage(page, limit, companyQuery);
        return R.ok().data("records", result.getRecords()).data("total", result.getTotal());
    }

    @PostMapping("/save")
    public R save(@RequestBody Company company){
        boolean isSave = companyService.save(company);
        return isSave ? R.ok() : R.err();
    }

    @PostMapping("/update")
    public R update(@RequestBody Company company){
        boolean isUpdate = companyService.updateById(company);
        return isUpdate ? R.ok() : R.err();
    }

    @DeleteMapping("/delete/{id}")
    public R update(@PathVariable("id") Long id){
        companyService.removeById(id);
        return R.ok();
    }
}

