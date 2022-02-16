package com.alex.member.client;

import com.alex.common.consant.ServiceNameConstant;
import com.alex.common.util.R;
import com.alex.member.dto.UserCompanyRelationDO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author _Alexzinv_
 * @date 2021/12/22
 * @description RPC userController
 */
@FeignClient(value = ServiceNameConstant.SYSTEM_SERVICE, fallback = UserClientDegradeService.class)
@Component
public interface UserClient {

    /**
     * 用户和公司建立关联
     * @param relationDO 关联数据
     * @return 操作结果
     */
    @PutMapping("/admin/acl/user/update")
    R updateById(@RequestBody UserCompanyRelationDO relationDO);
}
