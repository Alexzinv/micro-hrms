package com.alex.system.entity;

import com.alex.common.valid.group.AddGroup;
import com.alex.common.valid.group.UpdateGroup;
import com.alex.common.valid.group.UpdateStatusGroup;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.io.Serializable;
import java.util.Date;


/**
 * @Author _Alexzinv_
 * @Date 2021/9/22
 * @Description 角色
 */
@Data
@TableName("sys_role")
public class Role implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * ID
	 */
	@TableId
	@NotNull(groups = {UpdateGroup.class})
	private Long id;
	/**
	 * 角色名称
	 */
	@NotBlank(groups = {AddGroup.class, UpdateGroup.class})
	private String roleName;
	/**
	 * 角色编码
	 */
	@Null(groups = {AddGroup.class, UpdateGroup.class})
	private String roleCode;
	/**
	 * 备注
	 */
	private String notes;
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
