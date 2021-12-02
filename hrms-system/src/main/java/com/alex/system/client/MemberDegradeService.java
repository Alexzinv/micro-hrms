package com.alex.system.client;

import com.alex.common.bean.UserCompanyTo;
import com.alex.common.bean.UserPersonalInfoTo;
import com.alex.common.consant.ResultCodeEnum;
import com.alex.common.util.R;
import org.springframework.stereotype.Component;

/**
 * @author _Alexzinv_
 * @date 2021/12/2
 * @description 用户RPC降级服务
 */
@Component
public class MemberDegradeService implements MemberClient {
    @Override
    public R deleteUserCompany(Long id) {
        return R.err().result(ResultCodeEnum.SYSTEM_BUSY);
    }

    @Override
    public R deletePersonalInfo(Long id) {
        return R.err().result(ResultCodeEnum.SYSTEM_BUSY);
    }

    @Override
    public R saveUserCompany(UserCompanyTo to) {
        return R.err().result(ResultCodeEnum.SYSTEM_BUSY);
    }

    @Override
    public R savePersonalInfo(UserPersonalInfoTo to) {
        return R.err().result(ResultCodeEnum.SYSTEM_BUSY);
    }
}
