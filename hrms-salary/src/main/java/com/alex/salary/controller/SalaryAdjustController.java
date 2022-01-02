package com.alex.salary.controller;


import com.alex.common.util.R;
import com.alex.common.valid.group.AddGroup;
import com.alex.common.valid.group.QueryGroup;
import com.alex.common.valid.group.UpdateGroup;
import com.alex.salary.dto.RewardsPunishmentQuery;
import com.alex.salary.dto.SalaryAdjustQuery;
import com.alex.salary.entity.RewardsPunishment;
import com.alex.salary.entity.SalaryAdjust;
import com.alex.salary.service.SalaryAdjustService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * 薪酬调整 前端控制器
 *
 * @author _Alexzinv_
 * @date 2022-01-01
 */
@RestController
@RequestMapping("/salary/adjust")
public class SalaryAdjustController {

    private final SalaryAdjustService salaryAdjustService;

    SalaryAdjustController(SalaryAdjustService salaryAdjustService){
        this.salaryAdjustService = salaryAdjustService;
    }

    @GetMapping("get/{id}")
    public R getSalaryAdjust(@PathVariable("id") Long id){
        SalaryAdjust salaryAdjust = salaryAdjustService.getById(id);
        return R.ok().data("salaryAdjust", salaryAdjust);
    }

    @PostMapping("/listPage/{page}/{limit}")
    public R listSalaryAdjustCondition(@PathVariable Integer page,
                                  @PathVariable Integer limit,
                                  @Validated({QueryGroup.class})
                                  @RequestBody(required = false) SalaryAdjustQuery query){
        Page<SalaryAdjust> result = salaryAdjustService.listPage(page, limit, query);
        return R.ok().data("records", result.getRecords()).data("total", result.getTotal());
    }

    @PostMapping("/save")
    public R saveSalaryAdjust(@Validated({AddGroup.class}) @RequestBody SalaryAdjust sa){
        salaryAdjustService.save(sa);
        return R.ok();
    }

    @PostMapping("/update")
    public R updateSalaryAdjust(@Validated({UpdateGroup.class}) @RequestBody SalaryAdjust sa){
        salaryAdjustService.updateById(sa);
        return R.ok();
    }

    @DeleteMapping("/delete/{id}")
    public R deleteSalaryAdjust(@PathVariable("id") Long id){
        salaryAdjustService.removeById(id);
        return R.ok();
    }

}

