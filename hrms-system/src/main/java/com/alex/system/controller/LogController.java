package com.alex.system.controller;

import com.alex.common.util.R;
import com.alex.common.valid.group.AddGroup;
import com.alex.system.entity.Log;
import com.alex.system.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author _Alexzinv_
 * @date 2022/1/20
 */
@RestController
@RequestMapping("/admin/acl/log")
public class LogController {

    private final LogService logService;

    @Autowired
    LogController(LogService logService){
        this.logService = logService;
    }

    @GetMapping("/list")
    public R listLogCondition(){

        return R.ok();
    }

    @PostMapping("save")
    public R saveLog(@Validated({AddGroup.class}) @RequestBody Log log){
        logService.save(log);
        return R.ok();
    }

    @DeleteMapping("delete")
    public R removeLogBatch(List<Long> ids){
        logService.removeByIds(ids);
        return R.ok();
    }
}
