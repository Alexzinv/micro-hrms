package com.alex.statistic.controller;


import com.alex.common.util.R;
import com.alex.statistic.entity.StatisticsCompanyData;
import com.alex.statistic.service.StatisticsCompanyDataService;
import com.alex.statistic.vo.StatisticsCompanyDataQuery;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 月公司人数变动统计 前端控制器
 * </p>
 *
 * @author _Alexzinv_
 * @since 2022-03-13
 */
@RestController
@RequestMapping("/statistic/companyData")
public class StatisticsCompanyDataController {

    private final StatisticsCompanyDataService statisticsCompanyDataService;

    @Autowired
    public StatisticsCompanyDataController(StatisticsCompanyDataService statisticsCompanyDataService) {
        this.statisticsCompanyDataService = statisticsCompanyDataService;
    }

    @GetMapping("/{archiveId}")
    public R getArchiveData(@PathVariable("archiveId") Long archiveId) {
        StatisticsCompanyData statisticsCompanyData = statisticsCompanyDataService.getById(archiveId);
        return R.ok().data("data", statisticsCompanyData);
    }

    @PostMapping("/listPage/{page}/{limit}")
    public R listCondition(@PathVariable("page") Integer page,
                           @PathVariable("limit") Integer limit,
                           @RequestBody(required = false) StatisticsCompanyDataQuery query) {
        Page<StatisticsCompanyData> companyDataPage = statisticsCompanyDataService.listPage(page, limit, query);
        return R.ok().data("records", companyDataPage.getRecords())
                .data("total", companyDataPage.getTotal());
    }

    @PostMapping("/save")
    public R saveStatisticsCompanyData(@RequestBody StatisticsCompanyData statisticsCompanyData){
        statisticsCompanyDataService.save(statisticsCompanyData);
        return R.ok();
    }

    @DeleteMapping("/deleteBatch")
    public R deleteBatch(@RequestBody List<Long> ids){
        statisticsCompanyDataService.removeByIds(ids);
        return R.ok();
    }
}

