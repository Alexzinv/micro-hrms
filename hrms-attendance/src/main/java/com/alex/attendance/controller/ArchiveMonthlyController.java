package com.alex.attendance.controller;


import com.alex.attendance.entity.ArchiveMonthly;
import com.alex.attendance.service.ArchiveMonthlyService;
import com.alex.common.util.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 考勤归档 前端控制器
 * </p>
 *
 * @author _Alexzinv_
 * @since 2022-03-13
 */
@RestController
@RequestMapping("/attendance/archiveMonthly")
public class ArchiveMonthlyController {

    private final ArchiveMonthlyService archiveMonthlyService;

    @Autowired
    public ArchiveMonthlyController(ArchiveMonthlyService archiveMonthlyService){
        this.archiveMonthlyService = archiveMonthlyService;
    }

    @GetMapping("/{archiveMonthlyId}")
    public R listArchiveMonthlyById(@PathVariable("archiveMonthlyId") Long archiveMonthlyId) {
        ArchiveMonthly archiveMonthly = archiveMonthlyService.getById(archiveMonthlyId);
        return R.ok().data("records", archiveMonthly);
    }

    @GetMapping
    public R listArchiveMonthly() {
        List<ArchiveMonthly> attendances = archiveMonthlyService.list();
        return R.ok().data("records", attendances);
    }

    @DeleteMapping
    public R deleteBatch(@RequestBody List<Long> ids){
        archiveMonthlyService.removeByIds(ids);
        return R.ok();
    }
}

