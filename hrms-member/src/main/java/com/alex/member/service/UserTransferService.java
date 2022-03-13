package com.alex.member.service;

import com.alex.common.base.BaseService;
import com.alex.member.entity.UserTransfer;

import java.util.List;

/**
 * <p>
 * 员工调职表 服务类
 * </p>
 *
 * @author _Alexzinv_
 * @since 2022-02-27
 */
public interface UserTransferService extends BaseService<UserTransfer> {

    /**
     * 查询用户的所有调职记录
     * @param userCompanyId 用户id
     * @return 调职记录
     */
    List<UserTransfer> listByUserCompanyId(Long userCompanyId);
}
