package com.alex.security.entity;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * @Author _Alexzinv_
 */
@EqualsAndHashCode
@Data
@ApiModel(description = "用户类")
public class LoginUser implements Serializable {

	private static final long serialVersionUID = 1L;

	private String username;

	private String password;

	private String nickname;

	private String avatar;

	private String token;

}



