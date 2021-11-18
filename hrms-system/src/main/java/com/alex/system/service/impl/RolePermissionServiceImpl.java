package com.alex.system.service.impl;

import com.alex.system.entity.RolePermission;
import com.alex.system.entity.User;
import com.alex.system.entity.UserRole;
import com.alex.system.mapper.RolePermissionMapper;
import com.alex.system.service.PermissionService;
import com.alex.system.service.RolePermissionService;
import com.alex.system.service.UserRoleService;
import com.alex.system.service.UserService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

;


/**
 * @author Alex
 */
@Service("rolePermissionService")
public class RolePermissionServiceImpl extends ServiceImpl<RolePermissionMapper, RolePermission> implements RolePermissionService {

    @Autowired
    private UserRoleService userRoleService;

    @Autowired
    private UserService userService;

    @Autowired
    private PermissionService permissionService;

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void saveRolePermissionRelation(Long roleId, List<Long> permissionIds) {
        if(CollectionUtils.isEmpty(permissionIds)){
            return;
        }
        super.remove(new QueryWrapper<RolePermission>().eq("role_id", roleId));

        for (Long p : permissionIds) {
            if(p == null || p.equals(0L)) {
                continue;
            }
            RolePermission rolePermission = new RolePermission();
            rolePermission.setRoleId(roleId);
            rolePermission.setPermissionId(p);
            save(rolePermission);
        }

        // 1.查询关联角色的用户
        // 2.刷新所有redis权限缓存
        List<UserRole> userRoleList = userRoleService.list(new QueryWrapper<UserRole>().eq("role_id", roleId));
        List<Long> userIdList = userRoleList.stream().map(UserRole::getUserId).collect(Collectors.toList());
        if(CollectionUtils.isEmpty(userIdList)){
            return;
        }
        for (Long userId : userIdList) {
            User user = userService.getById(userId);
            if(user == null){
                continue;
            }
            // 检查redis是否存在缓存数据
            String username = user.getUsername();
            List<String> permissionValueList = (List<String>) redisTemplate.opsForValue().get(username);
            if(CollectionUtils.isEmpty(permissionValueList)){
                continue;
            }
            // 查询更新后的权限，如果已经过期，则不设置
            List<String> permissions = permissionService.listPermissionValueByUserId(user.getId());
            Long expire = redisTemplate.getExpire(username);
            if(expire != null && expire > 0){
                redisTemplate.opsForValue().set(user.getUsername(), permissions, Duration.ofMillis(expire));
            }
        }
    }

}