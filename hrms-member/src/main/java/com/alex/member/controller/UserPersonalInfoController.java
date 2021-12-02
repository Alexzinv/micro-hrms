package com.alex.member.controller;


import com.alex.common.util.R;
import com.alex.member.entity.UserPersonalInfo;
import com.alex.member.service.UserPersonalInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 用户个人信息 前端控制器
 * </p>
 *
 * @author _Alexzinv_
 * @since 2021-12-02
 */
@RestController
@RequestMapping("/member/userPersonalInfo")
public class UserPersonalInfoController {

    private final UserPersonalInfoService userPersonalInfoService;

    @Autowired
    UserPersonalInfoController(UserPersonalInfoService userPersonalInfoService){
        this.userPersonalInfoService = userPersonalInfoService;
    }

    @GetMapping("/get/{id}")
    public R getPersonalInfo(@PathVariable("id") Long id){
        UserPersonalInfo userPersonalInfo = userPersonalInfoService.getById(id);
        return R.ok().data("userPersonalInfo", userPersonalInfo);
    }

    @PostMapping("/save")
    public R savePersonalInfo(@RequestBody UserPersonalInfo userPersonalInfo){
        userPersonalInfoService.save(userPersonalInfo);
        return R.ok();
    }

    @PostMapping("/update")
    public R updatePersonalInfo(@RequestBody UserPersonalInfo userPersonalInfo){
        userPersonalInfoService.updateById(userPersonalInfo);
        return R.ok();
    }

    @DeleteMapping("/delete/{id}")
    public R deletePersonalInfo(@PathVariable("id") Long id){
        userPersonalInfoService.removeById(id);
        return R.ok();
    }
}

