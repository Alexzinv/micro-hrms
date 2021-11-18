package com.alex.system.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;

/**
 * @Author _Alexzinv_
 * @Date 2021/9/26
 * @Description
 */
@SpringBootTest
public class UserRoleServiceTest {

    @Autowired
    private UserRoleService userRoleService;

    @Test
    public void saveRelationTest(){
        ArrayList<Long> roleIds = new ArrayList<>();
        Long roleId = 1L;
        roleIds.add(roleId);

        Long userId = 206463060410373L;
        userRoleService.saveUserRoleRelation(userId, roleIds);
    }
}
