package com.alex.system.service.impl;


import com.alex.common.base.BaseQuery;
import com.alex.common.bean.member.UserCompanyTo;
import com.alex.common.bean.member.UserPersonalInfoTo;
import com.alex.common.consant.UserConstant;
import com.alex.common.exception.HRMSException;
import com.alex.system.client.MemberClient;
import com.alex.system.dto.ForgetPasswordVO;
import com.alex.system.dto.RegisterVO;
import com.alex.system.dto.UserQueryVO;
import com.alex.system.dto.stuct.UserExtensionStruct;
import com.alex.system.entity.User;
import com.alex.system.entity.UserRole;
import com.alex.system.mapper.UserMapper;
import com.alex.system.service.UserRoleService;
import com.alex.system.service.UserService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.seata.spring.annotation.GlobalTransactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Objects;


/**
 * @Author _Alexzinv_
 * @Date 2021/9/23
 * @Description 用户
 */
@Service("userService")
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    private final UserRoleService userRoleService;
    private final RedisTemplate<String, Object> redisTemplate;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final MemberClient memberClient;

    /** 默认头像 */
    private static final String DEFAULT_AVATAR = "https://dev-alex.oss-cn-chengdu.aliyuncs.com" +
            "/2021-11-24/e4178477-0581-49ef-866b-3b4f758d892c_-666b9fd64787c58a.jpg";

    @Autowired
    public UserServiceImpl(RedisTemplate<String, Object> redisTemplate, UserRoleService userRoleService,
                           BCryptPasswordEncoder bCryptPasswordEncoder, MemberClient memberClient) {
        this.redisTemplate = redisTemplate;
        this.userRoleService = userRoleService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.memberClient = memberClient;
    }

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
        user.setAvatar(DEFAULT_AVATAR);
        save(user);
    }

    @Override
    public void buildCondition(LambdaQueryWrapper<User> wrapper, BaseQuery query) {
        if(query == null){
            return;
        }

        if(query instanceof UserQueryVO){
            UserQueryVO userQuery = (UserQueryVO) query;
            String name = userQuery.getName();
            Integer enableState = userQuery.getEnableState();
            Long companyId = userQuery.getCompanyId();
            Integer level = userQuery.getLevel();

            if(StringUtils.hasText(name)){
                wrapper.and(item -> item.eq(User::getUsername, name).or()
                        .like(User::getNickname, name)
                );
            }
            wrapper.ge(enableState != null, User::getEnableState, enableState)
                    .ge(companyId != null, User::getCompanyId, companyId)
                    .ge(level != null, User::getLevel, level);
        }
    }

    @GlobalTransactional(rollbackFor = Exception.class)
    @Override
    public void saveUser(User user) {
        /*
         - 分别保存，用同一id
          1.本地用户表
          2.用户信息扩展表
          3.公司用户信息表
         */
        String username = user.getUsername();
        Integer enableState = user.getEnableState();
        if(isExist(username)){
            throw new HRMSException(20001, "账号已存在");
        }
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        if(enableState == null){
            user.setEnableState(UserConstant.EnableState.ENABLE);
        }
        super.save(user);

        // 查询获取用户id及其他信息
        user = getUserByUsername(username);

        UserCompanyTo userCompanyTo = UserExtensionStruct.INSTANCE.toUserCompany(user);
        memberClient.saveUserCompany(userCompanyTo);

        UserPersonalInfoTo infoTo = new UserPersonalInfoTo();
        infoTo.setId(user.getId());
        infoTo.setName(user.getNickname());
        memberClient.savePersonalInfo(infoTo);
    }

    @Override
    public void updateUser(User user) {
        if(user.getUsername() != null){
            // 用户账号不允许更改
            user.setUsername(null);
        }

        if(StringUtils.hasText(user.getPassword())){
            user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        }
        baseMapper.updateById(user);
    }

    @GlobalTransactional(rollbackFor = Exception.class)
    @Override
    public void removeByUserIds(List<Long> idList) {
        if(!CollectionUtils.isEmpty(idList)){
            idList.forEach(this::removeUser);
        }
    }

    /**
     * 远程调用删除，需要开启分布式事务
     * @param id 用户id
     */
    @GlobalTransactional(rollbackFor = Exception.class)
    @Override
    public void removeUser(Long id) {
        /*
         - 分别删除
          1.用户信息扩展表
          2.公司用户信息表
          3.用户角色关联表
          4.本地用户表
         */
        memberClient.deleteUserCompany(id);
        memberClient.deletePersonalInfo(id);
        userRoleService.remove(new LambdaQueryWrapper<UserRole>().eq(UserRole::getUserId, id));
        super.removeById(id);
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
        return baseMapper.isExist(username) != null;
    }
}