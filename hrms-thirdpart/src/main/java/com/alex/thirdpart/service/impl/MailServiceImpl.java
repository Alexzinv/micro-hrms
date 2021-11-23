package com.alex.thirdpart.service.impl;

import com.alex.thirdpart.config.MailConfig;
import com.alex.thirdpart.service.MailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

/**
 * @Author _Alexzinv_
 * @Date 2021/9/16
 * @Description 邮件服务
 */
@Slf4j
@Service
public class MailServiceImpl implements MailService {

    @Autowired
    private JavaMailSender javaMailSender;

    @Override
    public void send(String mail, String code) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(MailConfig.USERNAME);
        message.setTo(mail);
        message.setSubject("验证码");
        message.setText("【人力资源】人力资源系统验证码：" + code + ", 5分种内有效, 请勿向他人泄露！");
        javaMailSender.send(message);
        log.info("邮件发送成功 -> {} : {}", mail, message.getText());
    }
}
