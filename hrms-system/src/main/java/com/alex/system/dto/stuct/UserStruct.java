package com.alex.system.dto.stuct;

import com.alex.system.entity.User;
import com.alex.serurity.entity.LoginUser;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @Author _Alexzinv_
 * @Date 2021/9/23
 * @Description 用户转换类
 */
@Mapper
public interface UserStruct {

    UserStruct INSTANCE = Mappers.getMapper(UserStruct.class);

    /**
     * 用户实体类转登录用户类
     * @param user 用户实体类
     * @return 登录用户类
     */
    LoginUser toLoginUser(User user);
}
