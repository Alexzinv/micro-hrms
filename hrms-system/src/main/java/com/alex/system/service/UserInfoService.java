package com.alex.system.service;

import cn.hutool.json.JSONObject;

import java.util.List;
import java.util.Map;

/**
 * @Author _Alexzinv_
 * @Date 2021/9/22
 * @Description
 */
public interface UserInfoService {

    /**
     * 根据用户名获取用户登录信息
     * @param username 用户名
     * @return 基本显示信息
     */
    Map<String, Object> mapInfo(String username);

    /**
     * 根据用户名获取动态菜单
     * @param username 用户名
     * @return 菜单
     */
    Map<String, Object> mapPermission(String username);

    /**
     * 根据用户名获取动态菜单
     * @param username 用户名
     * @return 菜单
     */
    List<JSONObject> getMenu(String username);
}
