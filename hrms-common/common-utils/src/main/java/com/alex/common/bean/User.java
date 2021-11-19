package com.alex.common.bean;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;


/**
 * @Author _Alexzinv_
 * @Date 2021/9/22
 * @Description 用户
 */
@Data
public class User implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * ID
	 */
	@TableId
	private Long id;
	/**
	 * 账号
	 */
	@NotBlank
	private String username;
	/**
	 * 密码
	 */
	@NotEmpty
	@Length(min = 6)
	private String password;
	/**
	 * 昵称
	 */
	@NotBlank
	private String nickname;
	/**
	 * 启用状态 0是禁用，1是启用
	 */
	private Integer enableState;
	/**
     * 用户级别
	 */
	private Integer level;

}
