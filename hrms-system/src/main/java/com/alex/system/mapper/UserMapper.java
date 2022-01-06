package com.alex.system.mapper;


import com.alex.system.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * @Author _Alexzinv_
 * @Date 2021/9/22
 * @Description 用户
 */
public interface UserMapper extends BaseMapper<User> {

    /**
     * 查询是否存在
     * @param username 用户名
     * @return 是否存在
     */
    Integer isExist(@Param("username") String username);
}
