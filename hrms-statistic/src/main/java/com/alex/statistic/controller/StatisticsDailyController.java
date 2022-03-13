package com.alex.statistic.controller;


import com.alex.common.util.R;
import com.alex.statistic.entity.StatisticsDaily;
import com.alex.statistic.service.StatisticsDailyService;
import com.alex.statistic.vo.StatisticsDailyQuery;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 网站统计日数据 前端控制器
 * </p>
 *
 * @author _Alexzinv_
 * @since 2022-03-13
 */
@RestController
@RequestMapping("/statistic/daily")
public class StatisticsDailyController {

    private final StatisticsDailyService statisticsDailyService;

    @Autowired
    public StatisticsDailyController(StatisticsDailyService statisticsDailyService) {
        this.statisticsDailyService = statisticsDailyService;
    }

    @GetMapping("/{archiveId}")
    public R getArchiveData(@PathVariable("archiveId") Long archiveId) {
        StatisticsDaily byId = statisticsDailyService.getById(archiveId);
        return R.ok().data("data", byId);
    }

    @PostMapping("/listPage/{page}/{limit}")
    public R listCondition(@PathVariable("page") Integer page,
                           @PathVariable("limit") Integer limit,
                           @RequestBody(required = false) StatisticsDailyQuery query) {
        Page<StatisticsDaily> statisticsDailyPage = statisticsDailyService.listPage(page, limit, query);
        return R.ok().data("records", statisticsDailyPage.getRecords())
                .data("total", statisticsDailyPage.getTotal());
    }

    @PostMapping("/save")
    public R saveStatisticsDaily(@RequestBody StatisticsDaily statisticsDaily){
        statisticsDailyService.save(statisticsDaily);
        return R.ok();
    }

    @DeleteMapping("/deleteBatch")
    public R deleteBatch(@RequestBody List<Long> ids){
        statisticsDailyService.removeByIds(ids);
        return R.ok();
    }


}

