package com.alex.system.mapper;


import com.alex.system.entity.Role;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @Author _Alexzinv_
 * @Date 2021/9/22
 * @Description 角色
 */
public interface RoleMapper extends BaseMapper<Role> {

    /**
     * 获取最新角色code
     * @return code
     */
    String getLatestCode();
}
