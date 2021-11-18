package com.alex.system.service;

import com.alex.system.entity.Permission;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author _Alexzinv_
 * @Date 2021/9/26
 * @Description
 */
@SpringBootTest
public class RolePermissionServiceTest {

    @Autowired
    private RolePermissionService rolePermissionService;

    @Autowired
    private PermissionService permissionService;

    @Test
    public void saveRelationTest(){
        Long roleId = 1L;

        List<Permission> permissionList = permissionService.list();
        List<Long> permissionIds = permissionList.stream().map(Permission::getId).filter(item -> !item.equals(1L)).collect(Collectors.toList());
        // permissionIds.forEach(System.out::println);

        rolePermissionService.saveRolePermissionRelation(roleId, permissionIds);
    }
}
