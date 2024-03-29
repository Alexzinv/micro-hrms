package com.alex.system.entity;

import com.alex.common.valid.group.AddGroup;
import com.alex.common.valid.group.UpdateGroup;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

/**
 * @Author _Alexzinv_
 * @Date 2021/9/22
 * @Description 权限角色关联表
 */
@Data
@TableName("sys_role_permission")
public class RolePermission implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * ID
	 */
	@TableId
	@NotNull(groups = {UpdateGroup.class})
	private Long id;
	/**
	 * 
	 */
	@NotNull(groups = {AddGroup.class, UpdateGroup.class})
	private Long roleId;
	/**
	 * 
	 */
	@NotNull(groups = {AddGroup.class, UpdateGroup.class})
	private Long permissionId;
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
