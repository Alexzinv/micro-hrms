package com.alex.system.dto.stuct;

import com.alex.common.bean.UserCompanyTo;
import com.alex.system.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @author _Alexzinv_
 * @date 2021/12/2
 * @description 用户扩展数据映射
 */
@Mapper
public interface UserExtensionStruct {
    UserExtensionStruct INSTANCE = Mappers.getMapper(UserExtensionStruct.class);

    /**
     * 用户转用户扩展信息
     * @param user 用户
     * @return to
     */
    UserCompanyTo toUserCompany(User user);
}
