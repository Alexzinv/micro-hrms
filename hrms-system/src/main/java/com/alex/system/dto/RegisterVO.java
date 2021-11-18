package com.alex.system.dto;


import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

/**
 * @Author _Alexzinv_
 * @Date 2021/9/23
 * @Description 系统注册对象
 */
@Data
public class RegisterVO {

    @NotBlank
    @Pattern(regexp = "^1[34578]{9}$|\\w[-\\w.+]*@([A-Za-z0-9][-A-Za-z0-9]+\\.)+[A-Za-z]{2,14}")
    private String username;

    @NotBlank
    private String nickname;

    @NotEmpty
    @Length(min = 6, max = 20)
    private String password;

    @NotEmpty
    private String validCode;
}
