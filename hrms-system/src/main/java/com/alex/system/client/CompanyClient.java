package com.alex.system.client;

import com.alex.common.util.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @Author _Alexzinv_
 * @Date 2021/11/29
 * @Description RPC - CompanyController
 */
@FeignClient("hrms-company")
@Component
public interface CompanyClient {

    /**
     * 根据id获取公司信息
     * @param id 公司id
     * @return 公司信息
     */
    @GetMapping("/company/company/get/{id}")
    R get(@PathVariable("id") Long id);
}
