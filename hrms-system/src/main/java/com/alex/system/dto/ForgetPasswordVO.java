package com.alex.system.dto;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

/**
 * @Author _Alexzinv_
 * @Date 2021/11/14
 * @Description 忘记密码数据类
 */
@Data
public class ForgetPasswordVO {

    /**
     * 用户名
     */
    @NotBlank
    @Pattern(regexp = "^1[34578]{9}$|\\w[-\\w.+]*@([A-Za-z0-9][-A-Za-z0-9]+\\.)+[A-Za-z]{2,14}")
    private String username;

    /**
     * 新密码
     */
    @NotBlank
    @Length(min = 6, max = 20)
    private String password;

    /**
     * 验证码
     */
    @NotBlank
    private String validCode;
}
