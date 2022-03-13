package com.alex.statistic.controller;


import com.alex.common.util.R;
import com.alex.statistic.entity.StatisticsMonthly;
import com.alex.statistic.service.StatisticsMonthlyService;
import com.alex.statistic.vo.StatisticsMonthlyQuery;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 网站统计月数据 前端控制器
 * </p>
 *
 * @author _Alexzinv_
 * @since 2022-03-13
 */
@RestController
@RequestMapping("/statistic/monthly")
public class StatisticsMonthlyController {

    private final StatisticsMonthlyService statisticsMonthlyService;

    @Autowired
    public StatisticsMonthlyController(StatisticsMonthlyService statisticsMonthlyService) {
        this.statisticsMonthlyService = statisticsMonthlyService;
    }

    @GetMapping("/{archiveId}")
    public R getArchiveData(@PathVariable("archiveId") Long archiveId) {
        StatisticsMonthly byId = statisticsMonthlyService.getById(archiveId);
        return R.ok().data("data", byId);
    }

    @PostMapping("/listPage/{page}/{limit}")
    public R listCondition(@PathVariable("page") Integer page,
                           @PathVariable("limit") Integer limit,
                           @RequestBody(required = false) StatisticsMonthlyQuery query) {
        Page<StatisticsMonthly> statistics = statisticsMonthlyService.listPage(page, limit, query);
        return R.ok().data("records", statistics.getRecords())
                .data("total", statistics.getTotal());
    }

    @PostMapping("/save")
    public R saveStatisticsMonthly(@RequestBody StatisticsMonthly statisticsDaily){
        statisticsMonthlyService.save(statisticsDaily);
        return R.ok();
    }

    @DeleteMapping("/deleteBatch")
    public R deleteBatch(@RequestBody List<Long> ids){
        statisticsMonthlyService.removeByIds(ids);
        return R.ok();
    }
}

