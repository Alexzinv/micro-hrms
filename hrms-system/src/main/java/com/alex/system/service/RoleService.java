package com.alex.system.service;



import com.alex.system.entity.Role;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
 * @Author _Alexzinv_
 * @Date 2021/9/22
 * @Description
 */
public interface RoleService extends IService<Role> {

    /**
     * 多条件分页查询用户信息
     * @param page 当前页
     * @param limit 每页数量
     * @param role 查询条件
     * @return 符合条件的角色
     */
    Page<Role> listPage(Long page, Long limit, Role role);

    /**
     * 根据用户id获取关联的所有角色
     * @param userId 用户id
     * @return 角色
     */
    List<Role> listRoleByUserId(Long userId);

    /**
     * 根据用户id获取关联的所有角色
     * @param userId 用户id
     * @return 所有关联角色
     */
    Map<String, Object> mapRoleByUserId(Long userId);

    /**
     * 根据roleId删除角色及关联表信息
     * @param id 角色id
     */
    void removeRole(Long id);
}

