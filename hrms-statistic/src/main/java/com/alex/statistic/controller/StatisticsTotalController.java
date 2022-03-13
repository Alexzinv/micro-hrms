package com.alex.statistic.controller;


import com.alex.common.util.R;
import com.alex.statistic.entity.StatisticsTotal;
import com.alex.statistic.service.StatisticsDailyService;
import com.alex.statistic.service.StatisticsTotalService;
import com.alex.statistic.vo.StatisticsDailyQuery;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 网站总数统计 前端控制器
 * </p>
 *
 * @author _Alexzinv_
 * @since 2022-03-13
 */
@RestController
@RequestMapping("/statistic/total")
public class StatisticsTotalController {

    private final StatisticsTotalService statisticsTotalService;

    @Autowired
    public StatisticsTotalController(StatisticsTotalService statisticsTotalService) {
        this.statisticsTotalService = statisticsTotalService;
    }

    @GetMapping("/{archiveId}")
    public R getArchiveData(@PathVariable("archiveId") Long archiveId) {
        StatisticsTotal byId = statisticsTotalService.getById(archiveId);
        return R.ok().data("data", byId);
    }

    @PostMapping("/listPage/{page}/{limit}")
    public R listCondition(@PathVariable("page") Integer page,
                           @PathVariable("limit") Integer limit,
                           @RequestBody(required = false) StatisticsDailyQuery query) {
        Page<StatisticsTotal> statistics = statisticsTotalService.listPage(page, limit, query);
        return R.ok().data("records", statistics.getRecords())
                .data("total", statistics.getTotal());
    }

    @PostMapping("/save")
    public R saveStatisticsTotal(@RequestBody StatisticsTotal statisticsTotal){
        statisticsTotalService.save(statisticsTotal);
        return R.ok();
    }

    @DeleteMapping("/deleteBatch")
    public R deleteBatch(@RequestBody List<Long> ids){
        statisticsTotalService.removeByIds(ids);
        return R.ok();
    }
}

