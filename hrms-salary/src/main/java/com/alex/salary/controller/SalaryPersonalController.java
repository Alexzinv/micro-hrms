package com.alex.salary.controller;


import com.alex.common.util.R;
import com.alex.common.valid.group.AddGroup;
import com.alex.common.valid.group.QueryGroup;
import com.alex.common.valid.group.UpdateGroup;
import com.alex.salary.dto.SalaryPersonalQuery;
import com.alex.salary.entity.SalaryPersonal;
import com.alex.salary.service.SalaryPersonalService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * 员工薪酬个人表 前端控制器
 *
 * @author _Alexzinv_
 * @date 2022-01-01
 */
@RestController
@RequestMapping("/salary/personal")
public class SalaryPersonalController {

    private final SalaryPersonalService salaryPersonalService;

    @Autowired
    SalaryPersonalController(SalaryPersonalService salaryPersonalService){
        this.salaryPersonalService = salaryPersonalService;
    }

    @GetMapping("get/{id}")
    public R getSalaryPersonal(@PathVariable("id") Long id){
        SalaryPersonal salaryPersonal = salaryPersonalService.getById(id);
        return R.ok().data("salaryPersonal", salaryPersonal);
    }

    @PostMapping("/listPage/{page}/{limit}")
    public R listSalaryPersonalCondition(@PathVariable Integer page,
                                            @PathVariable Integer limit,
                                            @Validated({QueryGroup.class})
                                            @RequestBody(required = false) SalaryPersonalQuery query){
        Page<SalaryPersonal> result = salaryPersonalService.listPage(page, limit, query);
        return R.ok().data("records", result.getRecords()).data("total", result.getTotal());
    }

    @PostMapping("/save")
    public R saveSalaryPersonal(@Validated({AddGroup.class}) @RequestBody SalaryPersonal sp){
        salaryPersonalService.save(sp);
        return R.ok();
    }

    @PostMapping("/update")
    public R updateSalaryPersonal(@Validated({UpdateGroup.class}) @RequestBody SalaryPersonal sp){
        salaryPersonalService.updateById(sp);
        return R.ok();
    }

    @DeleteMapping("/delete/{id}")
    public R deleteSalaryPersonal(@PathVariable("id") Long id){
        salaryPersonalService.removeById(id);
        return R.ok();
    }

}

