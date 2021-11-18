package com.alex.thirdpart.controller;


import com.alex.common.util.R;
import com.alex.common.util.RandNumUtil;
import com.alex.thirdpart.service.SmsService;
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
 * @Description 短信服务API
 */
@RestController
@RequestMapping("/third-part/sms")
public class SmsController {

    @Autowired
    private SmsService service;

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @GetMapping("/send/{phone}")
    public R sendValidCode(@PathVariable("phone") String phone){
        // 先尝试从redis取
        String code = redisTemplate.opsForValue().get(phone);
        if(StringUtils.hasText(code)){
            return R.ok();
        }
        // 生成随机验证码
        code = RandNumUtil.getFourNumRandom();

        /// FIXME 因管控，短信服务暂时无法开通, 需要手动从redis读取
        // Map<String, String> map = new HashMap<>(16);
        // map.put("code", code);

        redisTemplate.opsForValue().set(phone, code, 5, TimeUnit.MINUTES);

        /// 因管控，短信服务暂时无法开通
        // boolean isSend = service.send(map, phone);
        // if(isSend){
        // 	redisTemplate.opsForValue().set(phone, code, 5, TimeUnit.MINUTES);
        // 	return R.ok();
        // }
        // return R.err();

        return R.ok();
    }
}
