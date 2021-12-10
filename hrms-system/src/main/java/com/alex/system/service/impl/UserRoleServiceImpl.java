package com.alex.system.service.impl;

import com.alex.system.entity.UserRole;
import com.alex.system.mapper.UserRoleMapper;
import com.alex.system.service.UserRoleService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;


/**
 * @Author _Alexzinv_
 * @Date 2021/9/22
 * @Description 用户角色关联服务
 */
@Service("userRoleService")
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRole> implements UserRoleService {

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void saveUserRoleRelation(Long userId, List<Long> roleIds) {
        if(CollectionUtils.isEmpty(roleIds)){
            return;
        }
        baseMapper.delete(new QueryWrapper<UserRole>().eq("user_id", userId));

        List<UserRole> userRoleList = new ArrayList<>();
        for(Long roleId : roleIds) {
            if(roleId == null || roleId.equals(0L)) {
                continue;
            }
            UserRole userRole = new UserRole();
            userRole.setUserId(userId);
            userRole.setRoleId(roleId);
            userRoleList.add(userRole);
        }
        saveBatch(userRoleList);
    }
}