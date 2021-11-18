package com.alex.system.service;

import cn.hutool.json.JSONObject;
import com.alex.system.entity.Permission;
import com.baomidou.mybatisplus.extension.service.IService;
;

import java.util.List;
import java.util.Map;

/**
 * @Author _Alexzinv_
 * @Date 2021/9/22
 * @Description 权限服务
 */
public interface PermissionService extends IService<Permission> {

    /**
     * 用map方式封装所有菜单
     * @return 所有菜单
     */
    Map<String, Object> mapAllPermission();

    /**
     * 查询所有菜单
     * @return 所有菜单
     */
    List<Permission> listAllPermission();

    /**
     * 根据id递归删除菜单
     * @param id 权限id
     */
    void removeChildById(Long id);

    /**
     * 根据角色获取菜单
     * @param roleId 角色
     * @return 菜单
     */
    List<Permission> listPermissionByRoleId(Long roleId);

    /**
     * 根据角色获取菜单
     * @param roleId 角色
     * @return 菜单
     */
    Map<String, Object> mapPermissionByRoleId(Long roleId);

    /**
     * 根据用户Id获取权限值列表
     * @param userId 用户id
     * @return 权限值列表
     */
    List<String> listPermissionValueByUserId(Long userId);

    /**
     * 根据用户Id获取权限值列表
     * @param userId 用户id
     * @return 权限值列表
     */
    Map<String, Object> mapPermissionByUserId(Long userId);

    /**
     * 根据用户Id获取权限值列表
     * @param userId 用户id
     * @return 权限值列表
     */
    List<JSONObject> listPermissionByUserId(Long userId);
}

