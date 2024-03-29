package com.alex.system.dto.stuct;

import com.alex.security.entity.LoginUser;
import com.alex.system.dto.UserStateTo;
import com.alex.system.dto.UserVO;
import com.alex.system.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

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

    /**
     * 用户状态数据对象转实体
     * @param to 数据对象
     * @return 实体类
     */
    User userStateToEntity(UserStateTo to);

    /**
     * 用户vo对象转实体类
     * @param vo 视图对象
     * @return 实体类
     */
    User voToEntity(UserVO vo);

    /**
     * 实体类转vo
     * @param user 用户实体类
     * @param roleIdList 关联角色列表
     * @return vo
     */
    UserVO entityToVO(User user, List<Long> roleIdList);
}
