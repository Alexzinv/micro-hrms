package com.alex.thirdpart.controller;

import com.alex.common.util.R;
import com.alex.common.util.RandNumUtil;
import com.alex.thirdpart.service.MailService;
import com.alex.thirdpart.service.SmsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;

;

/**
 * @Author _Alexzinv_
 * @Date 2021/9/17
 * @Description 短信或邮件
 */
@RestController
@RequestMapping("/third-part/account")
public class MessageController {

    @Autowired
    private MailService mailService;

    @Autowired
    private SmsService smsService;

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    private static final Pattern P_EMAIL = Pattern.compile("\\w[-\\w.+]*@([A-Za-z0-9][-A-Za-z0-9]+\\.)+[A-Za-z]{2,14}");
    private static final Pattern P_MOBILE = Pattern.compile("0?(13|14|15|18|17)[0-9]{9}");

    @GetMapping("/send/{username}")
    public R sendValidCode(@PathVariable("username") String username){
        if(!StringUtils.hasText(username)){
            return R.err().message("账号不能为空");
        }

        // 判断是否手机号或邮箱
        boolean isAccount = P_EMAIL.matcher(username).matches() || P_MOBILE.matcher(username).matches();
        if(!isAccount){
            return R.err().message("不是手机号或邮箱");
        }

        // 先尝试从redis取
        String code = redisTemplate.opsForValue().get(username);
        if(StringUtils.hasText(code)){
            return R.ok();
        }

        // 生成随机验证码
        code = RandNumUtil.getFourNumRandom();
        redisTemplate.opsForValue().set(username, code, 5, TimeUnit.MINUTES);
        // 判断是否邮箱
        boolean isEmail = P_EMAIL.matcher(username).matches();
        if(isEmail){
            mailService.send(username, code);
        }else {
            /// FIXME 发短信（因管控，短信服务暂时无法开通, 将直接放入redis，需要手动从redis读取）
            // HashMap<String, Object> map = new HashMap<>(16);
            // map.put("code", code);
            // smsService.send(map, username);
            // return R.ok().message("发送成功");
        }
        return R.ok().message("发送成功");
    }
}
