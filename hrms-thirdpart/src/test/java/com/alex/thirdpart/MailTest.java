package com.alex.thirdpart;

;
import com.alex.common.util.RandNumUtil;
import com.alex.thirdpart.service.MailService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.StringUtils;

import java.util.concurrent.TimeUnit;

/**
 * @Author _Alexzinv_
 * @Date 2021/9/16
 * @Description 测试邮件发送
 */
@SpringBootTest
public class MailTest {

    @Autowired
    private MailService mailService;

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Test
    public void sendMailTest(){
        String mail = "1610425335@qq.com";
        // 先尝试从redis取 mail:code
        String code = redisTemplate.opsForValue().get(mail);
        if(StringUtils.hasText(code)){
            System.out.println("已经存在");
            return;
        }
        // 生成随机验证码
        code = RandNumUtil.getFourNumRandom();
        // 发送邮件并设置到redis
        mailService.send(mail, code);
        redisTemplate.opsForValue().set(mail, code, 5, TimeUnit.MINUTES);
        System.out.println("发送成功");
    }
}
