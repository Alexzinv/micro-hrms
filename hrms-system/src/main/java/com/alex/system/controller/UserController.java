package com.alex.system.controller;

import com.alex.system.dto.ForgetPasswordVO;
import com.alex.system.dto.RegisterVO;
import com.alex.system.dto.UserQueryVO;
import com.alex.system.entity.User;
import com.alex.system.service.RoleService;
import com.alex.system.service.UserRoleService;
import com.alex.system.service.UserService;
import com.alex.common.util.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


/**
 * @Author _Alexzinv_
 * @Date 2021/9/22
 * @Description 用户
 */
@RestController
@RequestMapping("/admin/acl/user")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private UserRoleService userRoleService;

    @ApiOperation(value = "获取用户分页列表")
    @GetMapping("{page}/{limit}")
    public R index(@PathVariable Integer page, @PathVariable Integer limit,
                   @RequestBody(required = false) UserQueryVO userQuery) {
        Page<User> result = userService.listPage(page, limit, userQuery);
        return R.ok().data("records", result.getRecords()).data("total", result.getTotal());
    }

    @PostMapping("forgetPassword")
    public R forgetPassword(@RequestBody ForgetPasswordVO vo){
        boolean isSuccess = userService.restorePassword(vo);
        return isSuccess ? R.ok() : R.err().message("密码修改失败");
    }

    @ApiOperation(value = "用户注册")
    @PostMapping("register")
    public R register(@RequestBody RegisterVO register) {
        userService.register(register);
        return R.ok();
    }

    @ApiOperation(value = "管理员新增用户")
    @PostMapping("save")
    public R save(@RequestBody User user) {
        userService.saveUser(user);
        return R.ok();
    }

    @ApiOperation(value = "获取用户")
    @GetMapping("get/{id}")
    public R get(@PathVariable Long id) {
        User user = userService.getById(id);
        return R.ok().data("data", user);
    }

    @ApiOperation(value = "修改用户")
    @PutMapping("update")
    public R updateById(@RequestBody User user) {
        userService.updateUser(user);
        return R.ok();
    }

    @ApiOperation(value = "删除用户")
    @DeleteMapping("remove/{id}")
    public R remove(@PathVariable Long id) {
        userService.removeUser(id);
        return R.ok();
    }

    @ApiOperation(value = "根据id列表删除用户")
    @DeleteMapping("batchRemove")
    public R batchRemove(@RequestBody List<Long> idList) {
        userService.removeByIds(idList);
        return R.ok();
    }

    @ApiOperation(value = "根据用户获取角色数据")
    @GetMapping("toAssign/{userId}")
    public R toAssign(@PathVariable Long userId) {
        Map<String, Object> roleMap = roleService.mapRoleByUserId(userId);
        return R.ok().data(roleMap);
    }

    @ApiOperation(value = "根据用户分配角色")
    @PostMapping("doAssign")
    public R doAssign(@RequestParam Long userId,@RequestParam List<Long> roleId) {
        userRoleService.saveUserRoleRelation(userId,roleId);
        return R.ok();
    }


}
