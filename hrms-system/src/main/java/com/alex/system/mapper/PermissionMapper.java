package com.alex.system.mapper;

;

import com.alex.system.entity.Permission;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;
import java.util.Map;

/**
 * @Author _Alexzinv_
 * @Date 2021/9/22
 * @Description 权限
 */
public interface PermissionMapper extends BaseMapper<Permission> {

    /**
     * 系统管理员 获取所有权限值
     * @return 所有权限值
     */
    List<String> listAllPermissionValue();

    /**
     * 根据用户id获取权限值
     * @param userId 用户id
     * @return 权限值列表
     */
    List<String> listPermissionValueByUserId(Long userId);

    /**
     * 根据用户id获取权限菜单
     * @param userId 用户id
     * @return 权限菜单
     */
    List<Map<String, Object>> listPermissionValue(Long userId);

    /**
     * 根据用户id获取权限菜单
     * @param userId 用户id
     * @return 权限菜单
     */
    List<Permission> listPermissionByUserId(Long userId);
}
