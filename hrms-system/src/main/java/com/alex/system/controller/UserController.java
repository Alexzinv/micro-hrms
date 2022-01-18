package com.alex.system.controller;

import com.alex.common.util.R;
import com.alex.common.valid.group.AddGroup;
import com.alex.common.valid.group.UpdateGroup;
import com.alex.common.valid.group.UpdateStatusGroup;
import com.alex.system.dto.*;
import com.alex.system.dto.stuct.UserStruct;
import com.alex.system.entity.User;
import com.alex.system.service.RoleService;
import com.alex.system.service.UserRoleService;
import com.alex.system.service.UserService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.ApiOperation;
import org.apache.ibatis.annotations.Update;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.annotation.Validated;
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

    private final UserService userService;
    private final RoleService roleService;
    private final UserRoleService userRoleService;

    @Autowired
    UserController(UserService userService, RoleService roleService, UserRoleService userRoleService){
        this.userService = userService;
        this.roleService = roleService;
        this.userRoleService = userRoleService;
    }

    @ApiOperation(value = "获取用户分页列表")
    @GetMapping("{page}/{limit}")
    public R index(@PathVariable Integer page, @PathVariable Integer limit,
                   @RequestBody(required = false) UserQueryVO userQuery) {
        Page<User> result = userService.listPage(page, limit, userQuery);
        return R.ok().data("records", result.getRecords()).data("total", result.getTotal());
    }

    @ApiOperation(value = "忘记密码")
    @PostMapping("forgetPassword")
    public R forgetPassword(@RequestBody ForgetPasswordVO vo){
        boolean isSuccess = userService.restorePassword(vo);
        return isSuccess ? R.ok() : R.err().message("密码修改失败");
    }

    @ApiOperation(value = "用户注册")
    @PostMapping("register")
    public R register(@Validated({AddGroup.class}) @RequestBody RegisterVO register) {
        userService.register(register);
        return R.ok();
    }

    @ApiOperation(value = "管理员新增用户")
    @Transactional(rollbackFor = Exception.class)
    @PostMapping("save")
    public R save(@Validated({AddGroup.class}) @RequestBody UserVO vo) {
        List<Long> roleIds = vo.getRoleIdList();
        User user = UserStruct.INSTANCE.voToEntity(vo);
        userService.saveUser(user);
        if(!CollectionUtils.isEmpty(roleIds)){
            User userByUsername = userService.getUserByUsername(user.getUsername());
            userRoleService.saveUserRoleRelation(userByUsername.getId(), roleIds);
        }
        return R.ok();
    }

    @ApiOperation(value = "获取用户")
    @GetMapping("get/{id}")
    public R get(@PathVariable Long id) {
        User user = userService.getById(id);
        List<Long> roleIds = userRoleService.listRoleIdsByUserId(user.getId());
        UserVO userVO = UserStruct.INSTANCE.entityToVO(user, roleIds);
        return R.ok().data("data", userVO);
    }

    @ApiOperation(value = "修改用户")
    @Transactional(rollbackFor = Exception.class)
    @PutMapping("update")
    public R updateById(@Validated({UpdateGroup.class}) @RequestBody UserVO vo) {
        List<Long> roleIds = vo.getRoleIdList();
        User user = UserStruct.INSTANCE.voToEntity(vo);
        userService.updateUser(user);
        if(!CollectionUtils.isEmpty(roleIds)){
            userRoleService.saveUserRoleRelation(user.getId(), roleIds);
        }
        return R.ok();
    }

    @ApiOperation(value = "根据id列表删除用户")
    @DeleteMapping("batchRemove")
    public R batchRemove(@RequestBody List<Long> idList) {
        userService.removeByUserIds(idList);
        return R.ok();
    }

    @ApiOperation(value = "根据用户ID获取角色数据")
    @GetMapping("toAssign/{userId}")
    public R toAssign(@PathVariable Long userId) {
        Map<String, Object> roleMap = roleService.mapRoleByUserId(userId);
        return R.ok().data(roleMap);
    }

    @ApiOperation(value = "根据用户ID分配角色")
    @PostMapping("doAssign")
    public R doAssign(@RequestParam Long userId,@RequestParam List<Long> roleId) {
        userRoleService.saveUserRoleRelation(userId,roleId);
        return R.ok();
    }

    @ApiOperation(value = "修改账号状态")
    @PostMapping("state")
    public R updateState(@Validated({UpdateStatusGroup.class}) @RequestBody UserStateTo to){
        User user = UserStruct.INSTANCE.userStateToEntity(to);
        userService.updateById(user);
        return R.ok();
    }

}
