package com.alex.thirdpart.service;

/**
 * @Author _Alexzinv_
 * @Date 2021/9/16
 * @Description 邮件服务
 */
public interface MailService {
    /**
     * 发送邮件
     * @param mail 邮箱对象
     * @param code 验证码
     */
    void send(String mail, String code);
}
