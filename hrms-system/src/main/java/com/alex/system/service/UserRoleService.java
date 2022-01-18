package com.alex.system.service;



import com.alex.system.entity.UserRole;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * @Author _Alexzinv_
 * @Date 2021/9/22
 * @Description
 */
public interface UserRoleService extends IService<UserRole> {

    /**
     * 给用户分配角色
     * @param userId 用户id
     * @param roleIds 角色列表
     */
    void saveUserRoleRelation(Long userId, List<Long> roleIds);

    /**
     * 根据用户id查询角色id列表
     * @param userId 用户id
     * @return 角色列表
     */
    List<Long> listRoleIdsByUserId(Long userId);
}

