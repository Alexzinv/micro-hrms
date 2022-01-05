package com.alex.system.client;

import com.alex.common.bean.member.UserCompanyTo;
import com.alex.common.bean.member.UserPersonalInfoTo;
import com.alex.common.base.BaseClient;
import com.alex.common.util.R;
import org.springframework.stereotype.Component;

/**
 * @author _Alexzinv_
 * @date 2021/12/2
 * @description 用户RPC降级服务
 */
@Component
public class MemberDegradeService extends BaseClient implements MemberClient {
    @Override
    public R deleteUserCompany(Long id) {
        return RESULT;
    }

    @Override
    public R deletePersonalInfo(Long id) {
        return RESULT;
    }

    @Override
    public R saveUserCompany(UserCompanyTo to) {
        return RESULT;
    }

    @Override
    public R savePersonalInfo(UserPersonalInfoTo to) {
        return RESULT;
    }
}
