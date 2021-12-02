package com.alex.system.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @Author _Alexzinv_
 * @Date 2021/9/22
 * @Description 权限
 */
@Data
@TableName("sys_permission")
public class Permission implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * ID
	 */
	@TableId
	private Long id;
	/**
	 * 所属上级
	 */
	private Long pid;
	/**
	 * 名称
	 */
	@NotBlank
	private String name;
	/**
	 * 类型(1:菜单,2:按钮,3:Api)
	 */
	private Integer type;
	/**
	 * 权限值
	 */
	@NotBlank
	private String permissionValue;
	/**
	 * 访问路径
	 */
	@NotBlank
	private String path;
	/**
	 * 组件路径
	 */
	private String component;
	/**
	 * 图标
	 */
	private String icon;
	/**
	 * 状态(0:禁止,1:正常)
	 */
	private Integer status;
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

	@ApiModelProperty(value = "层级")
	@TableField(exist = false)
	private Integer level;

	@ApiModelProperty(value = "下级")
	@TableField(exist = false)
	private List<Permission> children;

	// @TableField(exist = false)
	// List<Map<String, Object>> children;

	@ApiModelProperty(value = "是否选中")
	@TableField(exist = false)
	private Boolean select;

}
