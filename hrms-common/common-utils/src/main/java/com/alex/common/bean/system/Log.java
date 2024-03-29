// package com.alex.common.bean.system;
//
// import com.baomidou.mybatisplus.annotation.TableId;
// import com.baomidou.mybatisplus.annotation.TableName;
// import lombok.Data;
//
// import java.io.Serializable;
// import java.util.Date;
//
//
// /**
//  * @Author _Alexzinv_
//  * @Date 2022/1/20
//  * @Description 系统日志
//  */
// @Data
// @TableName("sys_log")
// public class Log implements Serializable {
// 	private static final long serialVersionUID = 1L;
//
// 	/** ID */
// 	@TableId
// 	private Long id;
// 	/** 用户名 */
// 	private String username;
// 	/** 用户操作 */
// 	private String operation;
// 	/** 请求方法 */
// 	private String method;
// 	/** 请求参数 */
// 	private String params;
// 	/** 执行时长(毫秒) */
// 	private Long time;
// 	/** IP地址 */
// 	private String ip;
// 	/** 创建时间 */
// 	private Date createTime;
//
// }
