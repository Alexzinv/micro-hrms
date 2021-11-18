package com.alex.system.service;

import com.alex.system.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @Author _Alexzinv_
 * @Date 2021/9/24
 * @Description
 */
@SpringBootTest
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @Test
    public void saveTest(){
        User user = new User();
        user.setUsername("228722771@qq.com");
        user.setPassword("123456");
        user.setNickname("admin");
        userService.saveUser(user);
    }

    @Test
    public void updateTest(){
        User user = new User();
        user.setId(206463060410373L);
        user.setPassword("123456");
        userService.updateUser(user);
    }
}
