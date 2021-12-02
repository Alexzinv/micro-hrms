package com.alex.system.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.Date;


/**
 * @Author _Alexzinv_
 * @Date 2021/9/22
 * @Description 用户
 */
@Data
@TableName("sys_user")
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
	 * 用户头像
	 */
	private String avatar;
	/**
	 * 用户签名
	 */
	private String token;
	/**
	 * 启用状态 0是禁用，1是启用
	 */
	private Integer enableState;
	/**
	 * 企业ID
	 */
	private Long companyId;
	/**
	 * 部门ID
	 */
	private Long departmentId;
	/**
	 * 账号级别
	 */
	private Integer level;
	/**
	 * 创建时间
	 */
	@TableField(fill = FieldFill.INSERT)
	private Date createTime;
	/**
	 * 更新时间
	 */
	@TableField(fill = FieldFill.INSERT_UPDATE)
	private Date updateTime;

}
