package com.alex.system.controller;

import cn.hutool.json.JSONObject;
import com.alex.system.service.UserInfoService;
import com.alex.common.util.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * @Author _Alexzinv_
 */
@RestController
@RequestMapping("/admin/acl/info")
public class UserInfoController {

    @Autowired
    private UserInfoService userInfoService;

    /**
     * 根据token获取用户信息
     */
    @GetMapping("info")
    public R info(){
        //获取当前登录用户账号
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Map<String, Object> userInfo = userInfoService.mapInfo(username);
        return R.ok().data(userInfo);
    }

    /**
     * 获取权限列表
     */
    @GetMapping("menu")
    public R getMenu(){
        //获取当前登录用户用户名
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        /// map封装
        // Map<String, Object> permissionList = userInfoService.mapPermission(username);
        // return R.ok().data(permissionList);
        List<JSONObject> permissions = userInfoService.getMenu(username);
        return R.ok().data("permissionList", permissions);
    }

    @PostMapping("logout")
    public R logout(){
        return R.ok();
    }

}
