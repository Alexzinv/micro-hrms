package com.alex.system.controller;

import com.alex.common.util.R;
import com.alex.common.valid.group.AddGroup;
import com.alex.system.dto.LogQuery;
import com.alex.system.entity.Log;
import com.alex.system.service.LogService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
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

    @GetMapping("/list/{page}/{limit}")
    public R listLogCondition(@PathVariable("page") Integer page,
                              @PathVariable("limit") Integer limit,
                              @RequestBody(required = false) LogQuery query){
        Page<Log> result = logService.listPage(page, limit, query);
        return R.ok().data("records", result.getRecords()).data("total", result.getTotal());
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
