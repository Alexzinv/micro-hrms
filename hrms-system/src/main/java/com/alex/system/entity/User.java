package com.alex.system.entity;

import com.alex.common.valid.ListValue;
import com.alex.common.valid.group.AddGroup;
import com.alex.common.valid.group.UpdateGroup;
import com.alex.common.valid.group.UpdateStatusGroup;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.*;
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
	@NotNull(groups = {UpdateGroup.class, UpdateStatusGroup.class})
	private Long id;
	/**
	 * 账号
	 */
	@NotBlank(groups = {AddGroup.class})
	private String username;
	/**
	 * 密码
	 */
	@NotEmpty(groups = {AddGroup.class})
	@Length(min = 6, groups = {AddGroup.class, UpdateGroup.class})
	private String password;
	/**
	 * 昵称
	 */
	@NotBlank(groups = {AddGroup.class})
	private String nickname;
	/**
	 * 用户头像
	 */
	@Null(groups = {AddGroup.class})
	private String avatar;
	/**
	 * 用户签名
	 */
	private String token;
	/**
	 * 启用状态 0是禁用，1是启用
	 */
	@NotNull(groups = {UpdateStatusGroup.class})
	@ListValue(values = {0, 1}, groups = {AddGroup.class, UpdateStatusGroup.class})
	private Integer enableState;
	/**
	 * 企业ID
	 */
	private Long companyId;
	/**
	 * 账号级别
	 */
	@ListValue(values = {1, 2, 3}, groups = {AddGroup.class, UpdateGroup.class})
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
