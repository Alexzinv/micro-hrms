package com.alex.system.dto;

import com.alex.common.valid.group.AddGroup;
import com.alex.common.valid.group.UpdateGroup;
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
public class RoleVO implements Serializable {
	private static final long serialVersionUID = 1L;

	/** ID */
	private Long id;
	/** 角色名称 */
	private String roleName;
}
