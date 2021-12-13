package com.alex.system.service.impl;

import com.alex.common.consant.CodePrefixEnum;
import com.alex.common.util.CodePrefixUtils;
import com.alex.system.entity.Role;
import com.alex.system.entity.RolePermission;
import com.alex.system.entity.UserRole;
import com.alex.system.mapper.RoleMapper;
import com.alex.system.service.RolePermissionService;
import com.alex.system.service.RoleService;
import com.alex.system.service.UserRoleService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

;


@Service("roleService")
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {

    @Autowired
    private UserRoleService userRoleService;

    @Autowired
    private RolePermissionService rolePermissionService;

    @Override
    public boolean save(Role entity) {
        entity.setRoleCode(getCode());
        return super.save(entity);
    }

    @Override
    public Page<Role> listPage(Long page, Long limit, Role role) {
        Page<Role> pageParam = new Page<>(page, limit);
        QueryWrapper<Role> wrapper = new QueryWrapper<>();
        if(StringUtils.hasText(role.getRoleName())) {
            wrapper.like("role_name",role.getRoleName());
        }
        return baseMapper.selectPage(pageParam,wrapper);
    }

    @Override
    public List<Role> listRoleByUserId(Long userId) {
        // 根据用户id从中间表查询关联对象再从中得到所有角色id
        List<UserRole> userRoleList = userRoleService.list(new QueryWrapper<UserRole>()
                .eq("user_id", userId).select("role_id"));
        List<Long> roleIdList = userRoleList.stream().map(UserRole::getRoleId).collect(Collectors.toList());
        List<Role> roleList = new ArrayList<>();
        if(!CollectionUtils.isEmpty(roleIdList)) {
            roleList = baseMapper.selectBatchIds(roleIdList);
        }
        return roleList;
    }

    @Override
    public Map<String, Object> mapRoleByUserId(Long userId) {
        //查询所有的角色
        List<Role> allRolesList = baseMapper.selectList(null);

        //根据用户id，查询用户拥有的角色id
        List<UserRole> userRoleList = userRoleService.list(new QueryWrapper<UserRole>()
                .eq("user_id", userId).select("role_id"));
        List<Long> roleList = userRoleList.stream().map(UserRole::getRoleId).collect(Collectors.toList());

        //对角色进行分类
        List<Role> assignRoles = new ArrayList<>();
        for (Role role : allRolesList) {
            //已分配
            if(roleList.contains(role.getId())) {
                assignRoles.add(role);
            }
        }

        Map<String, Object> roleMap = new HashMap<>(32);
        roleMap.put("assignRoles", assignRoles);
        roleMap.put("allRolesList", allRolesList);
        return roleMap;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void removeRole(Long id) {
        super.removeById(id);
        rolePermissionService.remove(new QueryWrapper<RolePermission>().eq("role_id", id));
    }

    private String getCode() {
        CodePrefixUtils utils = () -> baseMapper.getLatestCode();
        return utils.getCode(CodePrefixEnum.ROLE_CODE_PREFIX);
    }
}