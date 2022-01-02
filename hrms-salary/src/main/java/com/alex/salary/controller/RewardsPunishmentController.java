package com.alex.salary.controller;


import com.alex.common.util.R;
import com.alex.common.valid.group.AddGroup;
import com.alex.common.valid.group.QueryGroup;
import com.alex.common.valid.group.UpdateGroup;
import com.alex.salary.dto.RewardsPunishmentQuery;
import com.alex.salary.entity.RewardsPunishment;
import com.alex.salary.service.RewardsPunishmentService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * 奖惩 前端控制器
 *
 * @author _Alexzinv_
 * @date 2022-01-01
 */
@RestController
@RequestMapping("/salary/rewardsPunishment")
public class RewardsPunishmentController {

    private final RewardsPunishmentService rewardsPunishmentService;

    @Autowired
    RewardsPunishmentController(RewardsPunishmentService rewardsPunishmentService){
        this.rewardsPunishmentService = rewardsPunishmentService;
    }

    @GetMapping("get/{id}")
    public R getRewardsPunishment(@PathVariable("id") Long id){
        RewardsPunishment rewardsPunishment = rewardsPunishmentService.getById(id);
        return R.ok().data("rewardsPunishment", rewardsPunishment);
    }

    @PostMapping("/listPage/{page}/{limit}")
    public R listCompanyCondition(@PathVariable Integer page,
                                  @PathVariable Integer limit,
                                  @Validated({QueryGroup.class})
                                  @RequestBody(required = false) RewardsPunishmentQuery query){
        Page<RewardsPunishment> result = rewardsPunishmentService.listPage(page, limit, query);
        return R.ok().data("records", result.getRecords()).data("total", result.getTotal());
    }

    @PostMapping("/save")
    public R saveCompany(@Validated({AddGroup.class}) @RequestBody RewardsPunishment rp){
        rewardsPunishmentService.save(rp);
        return R.ok();
    }

    @PostMapping("/update")
    public R updateCompany(@Validated({UpdateGroup.class}) @RequestBody RewardsPunishment rp){
        rewardsPunishmentService.updateById(rp);
        return R.ok();
    }

    @DeleteMapping("/delete/{id}")
    public R deleteCompany(@PathVariable("id") Long id){
        rewardsPunishmentService.removeById(id);
        return R.ok();
    }
}

