package com.alex.system.service.impl;


import com.alex.common.consant.UserConstant;
import com.alex.common.exception.HRMSException;
import com.alex.system.dto.ForgetPasswordVO;
import com.alex.system.dto.RegisterVO;
import com.alex.system.dto.UserQueryVO;
import com.alex.system.entity.User;
import com.alex.system.entity.UserRole;
import com.alex.system.mapper.UserMapper;
import com.alex.system.service.UserRoleService;
import com.alex.system.service.UserService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.Objects;


/**
 * @Author _Alexzinv_
 * @Date 2021/9/23
 * @Description 用户
 */
@Service("userService")
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private UserRoleService userRoleService;

    @Override
    public User getUserByUsername(String username) {
        return baseMapper.selectOne(new QueryWrapper<User>().eq("username", username));
    }

    @Override
    public void register(RegisterVO register) {

        String code = register.getValidCode();
        String username = register.getUsername();
        String nickname = register.getNickname();
        String password = register.getPassword();

        // 非空判断
        if(!StringUtils.hasText(nickname) || !StringUtils.hasText(code)
                || !StringUtils.hasText(username) || !StringUtils.hasText(password)){
            throw new HRMSException(20001, "注册失败");
        }

        // redis取出验证码
        String redisCode = Objects.requireNonNull(redisTemplate.opsForValue().get(username)).toString();
        if(!Objects.equals(code, redisCode)){
            throw new HRMSException(20001, "验证码错误");
        }

        // 账号是否存在
        if (isExist(username)){
            throw new HRMSException(20001, "账号已存在");
        }

        User user = new User();
        user.setUsername(username);
        user.setNickname(nickname);

        user.setPassword(bCryptPasswordEncoder.encode(password));
        user.setEnableState(UserConstant.EnableState.ENABLE);
        user.setLevel(UserConstant.Level.CO_USER);
        save(user);
    }

    @Override
    public Page<User> listPage(Integer current, Integer limit, UserQueryVO userQuery) {
        Page<User> page = new Page<>(current, limit);
        QueryWrapper<User> wrapper = new QueryWrapper<>();

        if(userQuery != null){
            String username = userQuery.getUsername();
            String nickname = userQuery.getNickname();
            Integer enableState = userQuery.getEnableState();

            // FIXME 后续改进
            Long departmentId = userQuery.getDepartmentId();
            Long companyId = userQuery.getCompanyId();

            wrapper.ge(StringUtils.hasText(nickname), "nickname", nickname)
                    .ge(StringUtils.hasText(username), "username", username)
                    .ge(enableState != null, "enable_status", enableState)
                    .ge(companyId != null, "company_id", companyId)
                    .ge(departmentId != null, "department_id", departmentId);
        }

        return baseMapper.selectPage(page, wrapper);
    }

    @Override
    public void saveUser(User user) {
        String username = user.getUsername();
        if(isExist(username)){
            throw new HRMSException(20001, "账号已存在");
        }
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setEnableState(UserConstant.EnableState.ENABLE);
        baseMapper.insert(user);
    }

    @Override
    public void updateUser(User user) {
        User localUser = super.getById(user.getId());
        // 用户账号不允许更改
        if(localUser != null){
            user.setUsername(localUser.getUsername());
        }
        if(StringUtils.hasText(user.getPassword())){
            user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        }
        baseMapper.updateById(user);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void removeUser(Long id) {
        super.removeById(id);
        userRoleService.remove(new QueryWrapper<UserRole>().eq("user_id", id));
    }

    @Override
    public boolean restorePassword(ForgetPasswordVO vo) {
        String code = vo.getValidCode();
        String username = vo.getUsername();
        String newPassword = vo.getPassword();

        // 非空判断
        if(!StringUtils.hasText(code) || !StringUtils.hasText(username) || !StringUtils.hasText(newPassword)){
            throw new HRMSException(20001, "字段不能为空");
        }

        // redis取出验证码
        String redisCode = Objects.requireNonNull(redisTemplate.opsForValue().get(username)).toString();
        if(!Objects.equals(code, redisCode)){
            throw new HRMSException(20001, "验证码错误");
        }

        User user = this.getUserByUsername(username);
        if(user == null){
            throw new HRMSException(20001, "用户不存在");
        }

        user.setPassword(bCryptPasswordEncoder.encode(newPassword));
        return super.updateById(user);
    }

    private boolean isExist(String username){
        return baseMapper.selectCount(new QueryWrapper<User>().eq("username", username)) > 0;
    }
}