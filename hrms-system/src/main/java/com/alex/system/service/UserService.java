package com.alex.system.service;

import com.alex.common.base.BaseService;
import com.alex.system.dto.ForgetPasswordVO;
import com.alex.system.dto.RegisterVO;
import com.alex.system.entity.User;

import java.util.List;

/**
 * @Author _Alexzinv_
 * @Date 2021/9/22
 * @Description
 */
public interface UserService extends BaseService<User> {

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
     * 新增用户,并保存信息到关联表，扩展表
     * @param user 用户信息
     */
    void saveUser(User user);

    /**
     * 修改用户
     * @param user 用户信息
     */
    void updateUser(User user);

    /**
     * 删除用户及关联表扩展表
     * @param id 用户id
     */
    void removeUser(Long id);

    /**
     * 重置密码
     * @param vo 忘记密码数据对象
     * @return 操作结果
     */
    boolean restorePassword(ForgetPasswordVO vo);

    /**
     * 根据id列表批量删除
     * @param idList id列表
     */
    void removeByUserIds(List<Long> idList);

    /**
     * 根据日期查询当天注册人数
     * @param date 日期
     * @return 统计数
     */
    Integer registerCount(String date);
}

