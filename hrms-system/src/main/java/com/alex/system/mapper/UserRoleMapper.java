package com.alex.system.mapper;

import com.alex.system.entity.UserRole;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author _Alexzinv_
 * @Date 2021/9/22
 * @Description 用户角色关联表
 */
public interface UserRoleMapper extends BaseMapper<UserRole> {

    /**
     * 根据用户id查询角色id列表
     * @param userId 用户id
     * @return 角色列表
     */
    List<Long> selectIdsByUserId(@Param("userId") Long userId);
}
