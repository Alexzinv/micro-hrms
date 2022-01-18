package com.alex.system.client;

import com.alex.common.bean.member.UserCompanyTo;
import com.alex.common.bean.member.UserPersonalInfoTo;
import com.alex.common.util.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

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
     * @return 操作状态
     */
    @DeleteMapping("/member/userCompany/delete/{id}")
    R deleteUserCompany(@PathVariable("id") Long id);

    /**
     * 根据用户id删除用户扩展信息
     * @param id 用户id
     * @return 操作状态
     */
    @DeleteMapping("/member/userPersonalInfo/delete/{id}")
    R deletePersonalInfo(@PathVariable("id") Long id);

    /**
     * 保存用户公司岗位相关信息
     * @param to 数据对象
     * @return 操作状态
     */
    @PostMapping("/member/userCompany/save")
    R saveUserCompany(@RequestBody UserCompanyTo to);

    /**
     * 保存用户个人资历信息
     * @param to 数据对象
     * @return 操作状态
     */
    @PostMapping("/member/userPersonalInfo/save")
    R savePersonalInfo(@RequestBody UserPersonalInfoTo to);


    /**
     * 更新用户公司岗位相关信息
     * @param to 数据对象
     * @return 操作状态
     */
    @PostMapping("/member/userCompany/update")
    R updateUserCompany(@RequestBody UserCompanyTo to);

    /**
     * 更新用户个人资历信息
     * @param to 数据对象
     * @return 操作状态
     */
    @PostMapping("/member/userPersonalInfo/update")
    R updatePersonalInfo(@RequestBody UserPersonalInfoTo to);
}
