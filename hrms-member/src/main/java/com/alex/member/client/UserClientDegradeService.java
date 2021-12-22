package com.alex.member.client;

import com.alex.common.util.BaseClient;
import com.alex.common.util.R;
import com.alex.member.dto.UserCompanyRelationDO;
import org.springframework.stereotype.Component;

/**
 * @author _Alexzinv_
 * @date 2021/12/22
 * @description
 */
@Component
public class UserClientDegradeService extends BaseClient implements UserClient {
    @Override
    public R updateById(UserCompanyRelationDO relationDO) {
        return RESULT;
    }
}
