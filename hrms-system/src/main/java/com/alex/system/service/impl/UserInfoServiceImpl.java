package com.alex.system.service.impl;

import cn.hutool.json.JSONObject;
import com.alex.common.consant.ResultCodeEnum;
import com.alex.common.exception.HRMSException;
import com.alex.system.entity.Role;
import com.alex.system.entity.User;
import com.alex.system.service.PermissionService;
import com.alex.system.service.RoleService;
import com.alex.system.service.UserInfoService;
import com.alex.system.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @Author _Alexzinv_
 * @Date 2021/9/22
 * @Description
 */
@Service
public class UserInfoServiceImpl implements UserInfoService {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private PermissionService permissionService;

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Override
    public User userInfo(String username) {
        User user = userService.getUserByUsername(username);
        if (user == null) {
            throw new HRMSException(ResultCodeEnum.UNKNOWN_EXCEPTION);
        }
        return user;
    }

    @Override
    public Map<String, Object> mapInfo(String username) {
        Map<String, Object> result = new HashMap<>(32);
        User user = userService.getUserByUsername(username);
        if (null == user) {
            throw new HRMSException(ResultCodeEnum.UNKNOWN_EXCEPTION);
        }

        //根据用户id获取角色
        List<Role> roleList = roleService.listRoleByUserId(user.getId());
        List<String> roleNameList = roleList.stream().map(Role::getRoleName).collect(Collectors.toList());
        if (roleNameList.isEmpty()) {
            //前端框架必须返回一个角色，否则报错，如果没有角色，返回一个空角色
            roleNameList.add("");
        }

        //根据用户id获取操作权限值
        List<String> permissionValueList = permissionService.listPermissionValueByUserId(user.getId());
        redisTemplate.opsForValue().set(username, permissionValueList);

        result.put("companyId", user.getCompanyId());
        result.put("name", user.getUsername());
        result.put("avatar", user.getAvatar());
        result.put("roles", roleNameList);
        result.put("permissionValueList", permissionValueList);
        return result;
    }

    @Override
    public Map<String, Object> mapPermission(String username) {
        User user = userService.getUserByUsername(username);
        return permissionService.mapPermissionByUserId(user.getId());
    }

    @Override
    public List<JSONObject> getMenu(String username) {
        User user = userService.getUserByUsername(username);

        return permissionService.listPermissionByUserId(user.getId());
    }
}