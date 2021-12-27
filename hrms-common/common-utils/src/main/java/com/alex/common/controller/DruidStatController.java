package com.alex.common.controller;

import com.alibaba.druid.stat.DruidStatManagerFacade;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author _Alexzinv_
 * @date 2021/12/27
 * @description 获取监控数据接口
 */
@RestController
public class DruidStatController {

    @GetMapping("/druid/stat")
    public Object druidStat(){
        // DruidStatManagerFacade#getDataSourceStatDataList
        // 该方法可以获取所有数据源的监控数据
        // 此外 DruidStatManagerFacade 还提供了一些其他方法可按需选择。
        return DruidStatManagerFacade.getInstance().getDataSourceStatDataList();
    }
}