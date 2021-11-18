package com.alex.thirdpart.controller;

import com.alex.common.util.R;
import com.alex.common.util.RandNumUtil;
import com.alex.thirdpart.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

/**
 * @Author _Alexzinv_
 * @Date 2021/9/16
 * @Description 邮件推送API
 */
@RestController
@RequestMapping("/third-part/mail")
public class MailController {

    @Autowired
    private MailService mailService;

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @GetMapping("/send/{mail}")
    public R sendMail(@PathVariable("mail") String mail){
        // 先尝试从redis取 mail:code
        String code = redisTemplate.opsForValue().get(mail);
        if(StringUtils.hasText(code)){
            return R.ok();
        }
        // 生成随机验证码
        code = RandNumUtil.getFourNumRandom();
        // 发送邮件并设置到redis
        mailService.send(mail, code);
        redisTemplate.opsForValue().set(mail, code, 5, TimeUnit.MINUTES);
        return R.ok();
    }
}
