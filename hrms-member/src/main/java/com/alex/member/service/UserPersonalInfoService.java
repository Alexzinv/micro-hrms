package com.alex.member.service;

import com.alex.member.dto.UserCompanyQuery;
import com.alex.member.entity.UserPersonalInfo;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 用户个人信息 服务类
 * </p>
 *
 * @author _Alexzinv_
 * @since 2021-12-02
 */
public interface UserPersonalInfoService extends IService<UserPersonalInfo> {

    Page<UserPersonalInfo> listPage(Integer page, Integer limit, UserCompanyQuery query);
}
