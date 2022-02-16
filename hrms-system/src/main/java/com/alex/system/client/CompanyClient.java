package com.alex.system.client;

import com.alex.common.consant.ServiceNameConstant;
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
@FeignClient(value = ServiceNameConstant.COMPANY_SERVICE, fallback = CompanyDegradeService.class)
@Component
public interface CompanyClient {

    /**
     * 根据公司id获取公司信息
     * @param id 公司id
     * @return 公司信息
     */
    @GetMapping("/company/company/get/{id}")
    R getCompany(@PathVariable("id") Long id);

    /**
     * 根据部门id获取部门信息
     * @param id 部门id
     * @return 部门信息
     */
    @GetMapping("/company/department/get/{id}")
    R getDepartment(@PathVariable("id") Long id);
}
