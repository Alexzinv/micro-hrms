package com.alex.system.service;

import com.alex.system.entity.Role;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @Author _Alexzinv_
 * @Date 2021/9/26
 * @Description
 */
@SpringBootTest
public class RoleServiceTest {

    @Autowired
    private RoleService roleService;

    @Test
    public void saveTest(){
        Role role = new Role();
        role.setRoleName("超级管理员");
        roleService.save(role);
    }







}
