package com.alex.salary.controller;


import com.alex.common.util.R;
import com.alex.common.valid.group.AddGroup;
import com.alex.common.valid.group.UpdateGroup;
import com.alex.salary.entity.SalaryCommon;
import com.alex.salary.service.SalaryCommonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * 员工薪酬公共表 前端控制器
 *
 * @author _Alexzinv_
 * @date 2022-01-01
 */
@RestController
@RequestMapping("/salary/common")
public class SalaryCommonController {

    private final SalaryCommonService salaryCommonService;

    @Autowired
    SalaryCommonController(SalaryCommonService salaryCommonService) {
        this.salaryCommonService = salaryCommonService;
    }

    @GetMapping("get/{id}")
    public R getSalaryCommon(@PathVariable("id") Long id){
        SalaryCommon salaryCommon = salaryCommonService.getById(id);
        return R.ok().data("salaryCommon", salaryCommon);
    }

    @GetMapping("getByCompany/{companyId}")
    public R getSalaryCommonByCompanyId(@PathVariable("companyId") Long companyId){
        SalaryCommon salaryCommon = salaryCommonService.getByCompanyId(companyId);
        return R.ok().data("salaryCommon", salaryCommon);
    }

    @PostMapping("/save")
    public R saveSalaryCommon(@Validated({AddGroup.class}) @RequestBody SalaryCommon sc){
        salaryCommonService.save(sc);
        return R.ok();
    }

    @PostMapping("/update")
    public R updateSalaryCommon(@Validated({UpdateGroup.class}) @RequestBody SalaryCommon sc){
        salaryCommonService.updateById(sc);
        return R.ok();
    }

    @DeleteMapping("/delete/{id}")
    public R deleteSalaryCommon(@PathVariable("id") Long id){
        salaryCommonService.removeById(id);
        return R.ok();
    }


}

