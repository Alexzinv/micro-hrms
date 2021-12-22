package com.alex.member.controller;


import com.alex.common.bean.member.UserCompanyDepartmentPositionTo;
import com.alex.common.util.R;
import com.alex.common.valid.group.AddGroup;
import com.alex.member.dto.UserCompanyQuery;
import com.alex.member.dto.UserCompanySaveUpdateTo;
import com.alex.member.dto.struct.UserCompanyUpdateStruct;
import com.alex.member.entity.UserCompany;
import com.alex.member.service.UserCompanyService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * user公司扩展表 前端控制器
 * </p>
 *
 * @author _Alexzinv_
 * @since 2021-12-02
 */
@RestController
@RequestMapping("/member/userCompany")
public class UserCompanyController {

    private final UserCompanyService userCompanyService;

    @Autowired
    UserCompanyController(UserCompanyService userCompanyService){
        this.userCompanyService = userCompanyService;
    }

    @GetMapping("/get/{id}")
    public R getUserCompany(@PathVariable("id") Long id){
        UserCompany userCompany = userCompanyService.getById(id);
        return R.ok().data("userCompany", userCompany);
    }

    @PostMapping("/listPage/{page}/{limit}")
    public R listUserCompanyCondition(@PathVariable Integer page,
                                      @PathVariable Integer limit,
                                      @RequestBody(required = false) UserCompanyQuery query){
        Page<UserCompany> result = userCompanyService.listPage(page, limit, query);
        return R.ok().data("records", result.getRecords()).data("total", result.getTotal());
    }

    @PostMapping("/save")
    public R saveUserCompany(@Validated({AddGroup.class}) @RequestBody UserCompanySaveUpdateTo to){
        UserCompany userCompany = UserCompanyUpdateStruct.INSTANCE.toUserCompany(to);
        userCompanyService.save(userCompany);
        return R.ok();
    }

    @PostMapping("/update")
    public R updateUserCompany(@RequestBody UserCompanySaveUpdateTo to){
        // TODO 用户与公司建立关联，公司管理员操作
        UserCompany userCompany = UserCompanyUpdateStruct.INSTANCE.toUserCompany(to);
        boolean update = userCompanyService.update(userCompany);
        return update ? R.ok() : R.err().message("更新失败");
    }

    @PostMapping("/updateDepartmentPosition")
    public R updateUserCompanyDepartmentPosition(@RequestBody UserCompanyDepartmentPositionTo to){
        userCompanyService.updateUserCompanyDepartmentPosition(to);
        return R.ok();
    }

    @DeleteMapping("/delete/{id}")
    public R deleteUserCompany(@PathVariable("id") Long id){
        userCompanyService.removeById(id);
        return R.ok();
    }
}

