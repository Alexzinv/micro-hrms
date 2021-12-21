package com.alex.company.client;

import com.alex.common.bean.member.UserCompanyDepartmentPositionTo;
import com.alex.common.consant.ResultCodeEnum;
import com.alex.common.util.R;

/**
 * @author _Alexzinv_
 * @date 2021/12/21
 * @description
 */
public class MemberUserCompanyClientDegradeService implements MemberUserCompanyClient {

    @Override
    public R updateUserCompanyDepartmentPosition(UserCompanyDepartmentPositionTo to) {
        return R.ok().result(ResultCodeEnum.SYSTEM_BUSY);
    }
}
