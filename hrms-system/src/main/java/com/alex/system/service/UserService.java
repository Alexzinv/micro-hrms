package com.alex.system.service;

import com.alex.system.dto.ForgetPasswordVO;
import com.alex.system.dto.RegisterVO;
import com.alex.system.dto.UserQueryVO;
import com.alex.system.entity.User;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

;

/**
 * @Author _Alexzinv_
 * @Date 2021/9/22
 * @Description
 */
public interface UserService extends IService<User> {

    /**
     * 根据账号查询
     * @param username 账户
     * @return 用户信息
     */
    User getUserByUsername(String username);

    /**
     * 注册用户
     * @param register 注册信息
     */
    void register(RegisterVO register);

    /**
     * 多条件分页查询用户信息
     * @param current 当前页
     * @param limit 每页数量
     * @param userQuery 查询条件
     * @return 符合条件的用户
     */
    Page<User> listPage(Integer current, Integer limit, UserQueryVO userQuery);

    /**
     * 新增用户
     * @param user 用户信息
     */
    void saveUser(User user);

    /**
     * 修改用户
     * @param user 用户信息
     */
    void updateUser(User user);

    /**
     * 删除用户及关联表
     * @param id 用户id
     */
    void removeUser(Long id);

    /**
     * 重置密码
     * @param vo 忘记密码数据对象
     * @return 操作结果
     */
    boolean restorePassword(ForgetPasswordVO vo);
}

