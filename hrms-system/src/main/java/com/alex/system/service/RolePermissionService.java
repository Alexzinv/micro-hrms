package com.alex.system.service;


import com.alex.system.entity.RolePermission;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * @Author _Alexzinv_
 * @Date 2021/9/22
 * @Description
 */
public interface RolePermissionService extends IService<RolePermission> {

    /**
     * 给角色分配权限
     * @param roleId 角色id
     * @param permissionId 权限id
     */
    void saveRolePermissionRelation(Long roleId, List<Long> permissionId);
}

