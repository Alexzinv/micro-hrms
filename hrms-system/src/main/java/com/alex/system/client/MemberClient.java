package com.alex.system.client;

import com.alex.common.util.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author _Alexzinv_
 * @date 2021/12/2
 * @description member RPC
 */
@FeignClient(value = "hrms-member", fallback = MemberDegradeService.class)
@Component
public interface MemberClient {

    /**
     * 根据用户id删除公司用户信息
     * @param id 用户id
     * @return 操作结果
     */
    @DeleteMapping("/member/userCompany/delete/{id}")
    R deleteUserCompany(@PathVariable("id") Long id);

    /**
     * 根据用户id删除用户扩展信息
     * @param id 用户id
     * @return 操作结果
     */
    @DeleteMapping("/member/userPersonalInfo/delete/{id}")
    R deletePersonalInfo(@PathVariable("id") Long id);
}
