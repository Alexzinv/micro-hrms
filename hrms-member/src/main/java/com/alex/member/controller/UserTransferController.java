package com.alex.member.controller;


import com.alex.common.util.R;
import com.alex.common.valid.group.AddGroup;
import com.alex.common.valid.group.QueryGroup;
import com.alex.member.dto.UserTransferQuery;
import com.alex.member.entity.UserTransfer;
import com.alex.member.service.UserTransferService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 员工调职表 前端控制器
 * </p>
 *
 * @author _Alexzinv_
 * @since 2022-02-27
 */
@RestController
@RequestMapping("/member/userTransfer")
public class UserTransferController {

    private final UserTransferService userTransferService;

    @Autowired
    public UserTransferController(UserTransferService userTransferService) {
        this.userTransferService = userTransferService;
    }

    @GetMapping("/get/{id}")
    public R getUserTransfer(@PathVariable("id") Long id){
        UserTransfer userTransfer = userTransferService.getById(id);
        return R.ok().data("data", userTransfer);
    }

    @GetMapping("/listUserTransfer/{userCompanyId}")
    public R listUserTransfer(@PathVariable("userCompanyId") Long userCompanyId){
        List<UserTransfer> userTransfer = userTransferService.listByUserCompanyId(userCompanyId);
        return R.ok().data("data", userTransfer);
    }

    @PostMapping("/listPage/{page}/{limit}")
    public R listUserTransferCondition(@PathVariable Integer page,
                                       @PathVariable Integer limit,
                                       @Validated({QueryGroup.class})
                                      @RequestBody(required = false)UserTransferQuery query){
        Page<UserTransfer> result = userTransferService.listPage(page, limit, query);
        return R.ok().data("records", result.getRecords()).data("total", result.getTotal());
    }

    @PostMapping("/save")
    public R saveUserTransfer(@Validated({AddGroup.class}) @RequestBody UserTransfer userTransfer){
        userTransferService.save(userTransfer);
        return R.ok();
    }

    @DeleteMapping("/deleteBatch")
    public R deleteUserTransfer(@RequestBody List<Long> ids){
        userTransferService.removeByIds(ids);
        return R.ok();
    }
}

