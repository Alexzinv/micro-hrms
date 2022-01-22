package com.alex.system.controller;


import com.alex.common.valid.group.AddGroup;
import com.alex.common.valid.group.UpdateGroup;
import com.alex.system.annotation.SysLog;
import com.alex.system.dto.RoleQuery;
import com.alex.system.dto.RoleVO;
import com.alex.system.dto.stuct.RoleStruct;
import com.alex.system.entity.Role;
import com.alex.system.service.RoleService;
import com.alex.common.util.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * @Author _Alexzinv_
 * @Date 2021/9/22
 * @Description 角色
 */
@RestController
@RequestMapping("/admin/acl/role")
public class RoleController {
    @Autowired
    private RoleService roleService;

    @ApiOperation(value = "获取角色分页列表")
    @PostMapping("{page}/{limit}")
    public R index(@PathVariable("page") Integer page, @PathVariable("limit") Integer limit,
                   @RequestBody(required = false) RoleQuery roleQuery) {
        Page<Role> result = roleService.listPage(page, limit, roleQuery);
        return R.ok().data("records", result.getRecords()).data("total", result.getTotal());
    }

    @ApiOperation(value = "获取角色")
    @GetMapping("get/{id}")
    public R get(@PathVariable Long id) {
        Role role = roleService.getById(id);
        return R.ok().data("data", role);
    }


    @ApiOperation(value = "新增角色")
    @SysLog(value = "新增角色")
    @PostMapping("save")
    public R save(@Validated({AddGroup.class}) @RequestBody Role role) {
        roleService.save(role);
        return R.ok();
    }

    @ApiOperation(value = "修改角色")
    @SysLog(value = "修改角色")
    @PutMapping("update")
    public R updateById(@Validated({UpdateGroup.class}) @RequestBody Role role) {
        roleService.updateById(role);
        return R.ok();
    }

    @ApiOperation(value = "删除角色")
    @SysLog(value = "删除角色")
    @DeleteMapping("remove/{id}")
    public R remove(@PathVariable Long id) {
        roleService.removeRole(id);
        return R.ok();
    }

    @ApiOperation(value = "根据id列表删除角色")
    @SysLog(value = "删除角色")
    @DeleteMapping("batchRemove")
    public R batchRemove(@RequestBody List<Long> idList) {
        roleService.removeByIds(idList);
        return R.ok();
    }

    /** 供建立关联查询使用 */
    @GetMapping("listRole")
    public R listRole(){
        List<Role> list = roleService.list();
        List<RoleVO> roleVOList = RoleStruct.INSTANCE.toRoleVOList(list);
        return R.ok().data("list", roleVOList);
    }

}
