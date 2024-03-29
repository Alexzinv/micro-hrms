package com.alex.company.controller;

import com.alex.common.util.R;
import com.alex.common.valid.group.AddGroup;
import com.alex.common.valid.group.QueryGroup;
import com.alex.common.valid.group.UpdateGroup;
import com.alex.common.valid.group.UpdateStatusGroup;
import com.alex.company.dto.CompanyCheckVO;
import com.alex.company.dto.CompanyQuery;
import com.alex.company.dto.CompanySaveOrUpdateVO;
import com.alex.company.dto.CompanyStateVO;
import com.alex.company.dto.struct.CompanyStruct;
import com.alex.company.entity.Company;
import com.alex.company.service.CompanyService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @author _Alexzinv_
 * @since 2021-11-06
 * @description 公司控制器
 */
@Slf4j
@RestController
@RequestMapping("/company/company")
public class CompanyController {

    private final CompanyService companyService;

    @Autowired
    CompanyController(CompanyService companyService){
        this.companyService = companyService;
    }

    @GetMapping("/get/{id}")
    public R getCompany(@PathVariable("id") Long id){
        Company company = companyService.getById(id);
        return R.ok().data("company", company);
    }

    @PostMapping("/listPage/{page}/{limit}")
    public R listCompanyCondition(@PathVariable Integer page,
                           @PathVariable Integer limit,
                           @Validated({QueryGroup.class})
                           @RequestBody(required = false) CompanyQuery companyQuery){

        Page<Company> result = companyService.listPage(page, limit, companyQuery);
        return R.ok().data("records", result.getRecords()).data("total", result.getTotal());
    }

    @PostMapping("/save")
    public R saveCompany(@Validated({AddGroup.class}) @RequestBody CompanySaveOrUpdateVO vo){
        Company company = CompanyStruct.INSTANCE.saveOrUpdateVoToEntity(vo);
        companyService.save(company);
        return R.ok();
    }

    @PostMapping("/update")
    public R updateCompany(@Validated({UpdateGroup.class}) @RequestBody CompanySaveOrUpdateVO vo){
        Company company = CompanyStruct.INSTANCE.saveOrUpdateVoToEntity(vo);
        companyService.updateById(company);
        return R.ok();
    }

    @DeleteMapping("/delete/{id}")
    public R deleteCompany(@PathVariable("id") Long id){
        companyService.removeById(id);
        return R.ok();
    }

    /** 修改审核状态 */
    @PostMapping("/check")
    public R checkCompany(@Validated({UpdateStatusGroup.class}) @RequestBody CompanyCheckVO vo){
        Company company = CompanyStruct.INSTANCE.checkVoToEntity(vo);
        companyService.updateById(company);
        return R.ok();
    }

    /** 修改启用状态 */
    @PostMapping("/state")
    public R stateCompany(@Validated({UpdateStatusGroup.class}) @RequestBody CompanyStateVO vo){
        Company company = CompanyStruct.INSTANCE.stateVoToEntity(vo);
        companyService.updateById(company);
        return R.ok();
    }
}

