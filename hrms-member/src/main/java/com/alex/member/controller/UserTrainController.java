package com.alex.member.controller;


import com.alex.common.util.R;
import com.alex.common.valid.group.AddGroup;
import com.alex.member.dto.UserTrainQuery;
import com.alex.member.entity.UserTrain;
import com.alex.member.service.UserTrainService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 员工培训表 前端控制器
 * </p>
 *
 * @author _Alexzinv_
 * @since 2022-02-27
 */
@RestController
@RequestMapping("/member/userTrain")
public class UserTrainController {

    private final UserTrainService userTrainService;

    @Autowired
    public UserTrainController(UserTrainService userTrainService) {
        this.userTrainService = userTrainService;
    }

    @PostMapping("/listPage/{page}/{limit}")
    public R listCondition(@PathVariable("page") Integer page,
                           @PathVariable("limit") Integer limit,
                           @RequestBody(required = false) UserTrainQuery query) {
        Page<UserTrain> userTrainList = userTrainService.listPage(page, limit, query);
        return R.ok().data("records", userTrainList.getRecords())
                .data("total", userTrainList.getTotal());
    }

    @PostMapping("/save")
    public R savePersonalInfo(@Validated({AddGroup.class}) @RequestBody UserTrain userTrain){
        userTrainService.save(userTrain);
        return R.ok();
    }

    @DeleteMapping("/deleteBatch")
    public R deletePersonalInfo(@RequestBody List<Long> ids){
        userTrainService.removeByIds(ids);
        return R.ok();
    }

}

