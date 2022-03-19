package com.alex.statistic.client;

import com.alex.common.consant.ServiceNameConstant;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author _Alexzinv_
 * @date 2022/2/10
 * @description
 */
@FeignClient(value = ServiceNameConstant.SYSTEM_SERVICE, fallback = SystemClientDegradeService.class)
@Component
public interface SystemClient {

    /**
     * 统计当日注册人数
     * @param date 日期
     * @return 总数
     */
    @GetMapping("/admin/acl/user/sta/inner/registerCount/{date}")
    Integer registerCount(@PathVariable("date") String date);
}
