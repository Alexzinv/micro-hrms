package com.alex.system.controller;

import com.alex.common.util.R;
import com.baomidou.kaptcha.Kaptcha;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author _Alexzinv_
 * @date 2021/12/15
 * @description 登录验证码生成
 */

@RestController
@RequestMapping("/admin/acl/kaptcha")
public class KaptchaController {

    private Kaptcha kaptcha;

    @Autowired
    public void setKaptcha(Kaptcha kaptcha) {
        this.kaptcha = kaptcha;
    }

    @GetMapping("/render")
    public R render() {
        kaptcha.render();
        return R.ok();
    }

    @PostMapping("/valid")
    public R validCustomTime(@RequestParam("code") String code) {
        kaptcha.validate(code, 60);
        return R.ok();
    }
}
