package com.alex.system.controller;

import com.alex.common.consant.CacheNameConstant;
import com.alex.common.consant.PermissionConstant;
import com.alex.common.util.R;
import com.alex.common.valid.group.AddGroup;
import com.alex.common.valid.group.UpdateGroup;
import com.alex.common.valid.group.UpdateStatusGroup;
import com.alex.system.annotation.SysLog;
import com.alex.system.dto.PermissionStatusVo;
import com.alex.system.dto.stuct.PermissionStruct;
import com.alex.system.entity.Permission;
import com.alex.system.service.PermissionService;
import com.alex.system.service.RolePermissionService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * @Author _Alexzinv_
 * @Date 2021/9/22
 * @Description 权限接口
 */
@CacheConfig(cacheNames = {CacheNameConstant.System.PERMISSION})
@RestController
@RequestMapping("/admin/acl/permission")
public class PermissionController {
    @Autowired
    private PermissionService permissionService;

    @Autowired
    private RolePermissionService rolePermissionService;

    @ApiOperation(value = "查询所有权限")
    // @Cacheable(key = "'list'")
    @GetMapping("listAll")
    public R listPermissions() {
        /// map方式封装
        // Map<String, Object> list =  permissionService.mapAllPermission();
        // return R.ok().data("children",list);
        List<Permission> permissions = permissionService.listAllPermission();
        return R.ok().data("children", permissions);
    }

    @ApiOperation(value = "递归删除权限")
    @SysLog("删除权限")
    @DeleteMapping("remove/{id}")
    public R remove(@PathVariable("id") Long id) {
        permissionService.removeChildById(id);
        return R.ok();
    }

    @ApiOperation(value = "给角色分配权限")
    @SysLog("分配权限")
    @PostMapping("doAssign")
    public R doAssign(Long roleId, @RequestParam List<Long> permissionId) {
        rolePermissionService.saveRolePermissionRelation(roleId,permissionId);
        return R.ok();
    }

    @ApiOperation(value = "根据角色获取权限")
    @GetMapping("toAssign/{roleId}")
    public R toAssign(@PathVariable Long roleId) {
        /// map方式封装
        // Map<String, Object> map = permissionService.mapPermissionByRoleId(roleId);
        // return R.ok().data(map);
        List<Permission> permissions = permissionService.listPermissionByRoleId(roleId);
        return R.ok().data("children", permissions);
    }

    @ApiOperation(value = "新增")
    @SysLog("新增权限")
    @PostMapping("save")
    public R save(@Validated({AddGroup.class}) @RequestBody Permission permission) {
        permission.setStatus(PermissionConstant.Status.ENABLE);
        permissionService.save(permission);
        return R.ok();
    }

    @ApiOperation(value = "修改")
    @SysLog("修改权限")
    @PutMapping("update")
    public R updateById(@Validated({UpdateGroup.class}) @RequestBody Permission permission) {
        permissionService.updateById(permission);
        return R.ok();
    }

    /**
     * 修改启用状态
     */
    @PostMapping("status")
    public R stateCompany(@Validated({UpdateStatusGroup.class}) @RequestBody PermissionStatusVo vo){
        Permission permission = PermissionStruct.INSTANCE.toPermission(vo);
        permissionService.updateById(permission);
        return R.ok();
    }
}
