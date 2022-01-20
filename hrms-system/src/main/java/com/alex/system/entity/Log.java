package com.alex.system.entity;

import com.alex.common.valid.group.AddGroup;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Date;


/**
 * @Author _Alexzinv_
 * @Date 2022/1/20
 * @Description 系统日志
 */
@Data
@TableName("sys_log")
public class Log implements Serializable {
	private static final long serialVersionUID = 1L;

	/** ID */
	@TableId
	private Long id;
	/** 用户名 */
	@NotBlank(groups = {AddGroup.class})
	private String username;
	/** 用户操作 */
	@NotBlank(groups = {AddGroup.class})
	private String operation;
	/** 请求方法 */
	private String method;
	/** 请求参数 */
	private String params;
	/** 执行时长(毫秒) */
	private Long time;
	/** IP地址 */
	@NotBlank(groups = {AddGroup.class})
	private String ip;
	/** 创建时间 */
	private Date createDate;

}
