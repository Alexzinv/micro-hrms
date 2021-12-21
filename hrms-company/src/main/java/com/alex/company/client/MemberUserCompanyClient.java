package com.alex.company.client;

import com.alex.common.bean.member.UserCompanyDepartmentPositionTo;
import com.alex.common.bean.member.UserCompanyTo;
import com.alex.common.util.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author _Alexzinv_
 * @date 2021/12/21
 * @description RPC
 */
@FeignClient(value = "hrms-member", fallback = MemberUserCompanyClientDegradeService.class)
@Component
public interface MemberUserCompanyClient {

    @PostMapping("/update")
    R updateUserCompany(@RequestBody UserCompanyDepartmentPositionTo to);
}
