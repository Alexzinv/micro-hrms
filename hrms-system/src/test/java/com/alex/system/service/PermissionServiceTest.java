package com.alex.system.service;

import com.alex.system.entity.Permission;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Map;

/**
 * @Author _Alexzinv_
 * @Date 2021/9/25
 * @Description
 */
@SpringBootTest
public class PermissionServiceTest {

    @Autowired
    private PermissionService permissionService;

    @Test
    public void saveTest(){
        Permission permission = new Permission();
        permission.setPid(206522406338565L);
        permission.setName("分配角色");
        permission.setType(2);
        permission.setPermissionValue("user.assign");
        permission.setPath("usr/role/:id");
        permission.setComponent("/acl/user/roleForm");
        permission.setStatus(1);
        permissionService.save(permission);
    }

    @Test
    public void listAllPermissionTest(){
        Map<String, Object> map = permissionService.mapAllPermission();
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            System.out.println(entry.getKey() + ":" + entry.getValue());
        }
    }
}
