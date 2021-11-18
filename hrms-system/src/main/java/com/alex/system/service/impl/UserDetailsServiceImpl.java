package com.alex.system.service.impl;


import com.alex.common.exception.HRMSException;
import com.alex.system.entity.User;
import com.alex.system.service.PermissionService;
import com.alex.system.service.UserService;
import com.alex.system.stuct.UserStruct;
import com.alex.serurity.entity.LoginUser;
import com.alex.serurity.entity.SecurityUser;
import org.springframework.beans.factory.annotation.Autowired;
;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * 自定义userDetailsService
 * 查询用户信息
 * @Author _Alexzinv_
 */
@Service("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Autowired
    private PermissionService permissionService;

    /***
     * 根据账号获取用户信息
     * @param username 账号
     * @return 用户信息
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 从数据库中取出用户信息
        User user = userService.getUserByUsername(username);

        // 判断用户是否存在
        if (user == null){
            throw new UsernameNotFoundException("用户名不存在！");
        }

        // 判断账号是否禁用
        if (user.getEnableState() == 0){
            throw new HRMSException(20001, "账号已停用！");
        }
        // 封装UserDetails实现类
        LoginUser loginUser = UserStruct.INSTANCE.toLoginUser(user);

        List<String> authorities = permissionService.listPermissionValueByUserId(user.getId());
        SecurityUser securityUser = new SecurityUser(loginUser);
        securityUser.setPermissionValueList(authorities);
        return securityUser;
    }

}
