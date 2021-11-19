# 基于微服务的人力资源管理系统的设计与实现



## 需求分析

本项目旨在实现一个泛用的、鲁棒性强的面向大型企业或组织在高人力管理成本、分配调度难度大情况下的人力资源管理系统。首先对于一个大型组织在面对较多人数的情况下进行统一的合规管理或是指标统计、以及绩效考核等要能发挥协助的作用，并且要能够降低人力资源管理的难度和投入成本，帮助组织提高运行效率并把关注点放到其他相对关键的事务上，更加合理的配置资源。该人力资源系统应该有的功能，包括但不限于人员信息录入、信息修改及删除、绩效管理、权限控制、统计分析等等；系统架构设计，包括包的依赖传递管理，根据不同功能点进行模块划分，各个业务相关的服务之间可以基于生产者消费者模型进行调用。此外，多个服务可以根据需要部署到多台服务器上实现负载均衡，通过注册中心进行耦合，网关负责转发到单个服务，而单个服务可以集成流量控制，实现在大流量下能够做到高可用，流量保护，服务熔断降级等，保证系统的稳定性，最终实现一个基于微服务的大型的具有广泛应用价值的人力资源管理系统。

+ 用户注册：新用户注册，需要验证手机号或邮箱是否存在
+ 短信发送服务：集成三方服务，根据手机号或者邮箱发送验证码
+ 用户登录：已注册用户的登录，包括根据登录用户获取对应权限
+ 用户密码修改：用户可修改自己的密码
+ 忘记密码：用户忘记密码需要验证找回
+ 禁用账号：对异常账号封禁，使其无法登录
+ 权限管理：单个权限和菜单的增删改查
+ 角色管理：对用户的菜单权限、操作权限、按钮等进行关联
+ 用户管理：用户的增删改查，关联角色
+ 企业管理：企业的增删改查，超级管理员（全部）和企业管理员（所属企业）可操作
+ 部门管理：根据组织部门进行划分管理，部门增删改查，关联用户
+ 岗位管理：管理用户的岗位，增删改查
+ 员工职称：员工职称的增删改查
+ 培训开发管理：员工的培训相关
+ 薪资福利管理：员工的薪资管理，需要基本薪资加一些其他结算项
+ 员工奖惩：分别对员工进行奖励和惩罚
+ 绩效考核：员工的考勤，绩效管理
+ 合同协议：员工合同管理，到期管理，续约管理
+ 统计分析：统计每日使用量，注册用户数



## 项目架构

+ 关键技术: `Spring Boot` + `Spring Cloud` + `Spring Security` + `Nacos` + `Vue`+ `Mybatis-Plus` + `Mysql`  + `redis`
+ <img src="https://markdown-alex.oss-cn-chengdu.aliyuncs.com/HRMS/HRMS系统架构.png" alt="hrms架构" style="zoom: 80%;" />





## 认证过程




```sequence
浏览器 -> 授权:输入账号密码，获取授权token, 并将权限存入缓存
授权 -> 浏览器:返回授权token
浏览器 -> 接口:请求带上授权token，调用接口
接口 -> 浏览器:返回接口数据
```

## 数据库设计

+ 权限管理（sys)

  ```mysql
  -- 系统权限菜单
  CREATE TABLE `sys_permission` (
      `id` bigint unsigned NOT NULL primary key COMMENT 'ID',
      `pid`  bigint unsigned NOT NULL DEFAULT '' COMMENT '所属上级',
      `name` varchar(20) NOT NULL DEFAULT '' COMMENT '名称',
      `type` tinyint unsigned NOT NULL DEFAULT '0' COMMENT '类型(1:菜单,2:按钮,3:Api)',
      `permission_value` varchar(50) COMMENT '权限值',
      `path` varchar(100) COMMENT '访问路径',
      `component` varchar(100) COMMENT '组件路径',
      `icon` varchar(255) COMMENT '图标',
      `status` tinyint unsigned COMMENT '状态(0:禁止,1:正常)',
      `create_time` datetime COMMENT '创建时间',
      `update_time` datetime COMMENT '更新时间',
      KEY `idx_pid` (`pid`)
  ) ENGINE = InnoDB CHARSET=utf8mb4 COMMENT='权限菜单';
  
  -- 角色
  CREATE TABLE `sys_role` (
      `id` bigint unsigned NOT NULL primary key COMMENT 'ID',
      `name` varchar(20) NOT NULL DEFAULT '' COMMENT '角色名称',
      `code` varchar(20) COMMENT '角色编码',
      `notes` varchar(255) DEFAULT NULL COMMENT '备注',
      `create_time` datetime  COMMENT '创建时间',
      `update_time` datetime COMMENT '更新时间'
  ) ENGINE = InnoDB DEFAULT CHARSET=utf8 COMMENT='角色';
  
  -- 角色权限关联表
  CREATE TABLE `sys_role_permission` (
      `id` bigint unsigned NOT NULL primary key COMMENT 'ID',
      `role_id` bigint unsigned NOT NULL DEFAULT '',
      `permission_id` bigint unsigned NOT NULL DEFAULT '',
      `create_time` datetime  COMMENT '创建时间',
      `update_time` datetime COMMENT '更新时间',
      KEY `idx_role_id` (`role_id`),
      KEY `idx_permission_id` (`permission_id`)
  ) ENGINE = InnoDB DEFAULT CHARSET=utf8 COMMENT='角色权限关联表';
  
  -- 系统用户
  CREATE TABLE `sys_user` (
      `id` bigint unsigned NOT NULL primary key COMMENT 'ID',
      `username` varchar(32) NOT NULL DEFAULT '' COMMENT '账号',
      `password` varchar(32) NOT NULL DEFAULT '' COMMENT '密码',
      `nickname` varchar(50) DEFAULT NULL COMMENT '昵称',
      `avatar` varchar(255) DEFAULT NULL COMMENT '用户头像',
      `token` varchar(100) DEFAULT NULL COMMENT '用户签名',
      `enable_state` tinyint unsigned DEFAULT 1 COMMENT '启用状态 0是禁用，1是启用',
      `create_time` datetime  COMMENT '创建时间',
      `update_time` datetime COMMENT '更新时间'
  ) ENGINE = InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户';
  
  -- 用户角色关联表
  CREATE TABLE `sys_user_role` (
      `id` bigint unsigned NOT NULL primary key COMMENT 'ID',
      `role_id` bigint unsigned NOT NULL DEFAULT 0 COMMENT '角色id',
      `user_id` bigint unsigned NOT NULL DEFAULT 0 COMMENT '用户id',
      `create_time` datetime  COMMENT '创建时间',
      `update_time` datetime COMMENT '更新时间',
      KEY `idx_role_id` (`role_id`),
      KEY `idx_user_id` (`user_id`)
  ) ENGINE = InnoDB DEFAULT CHARSET=utf8 COMMENT='用户角色关联表';
  ```
  
  <br>
  
+ 企业管理（en）

  ```mysql
  -- 企业
  CREATE TABLE `en_enterprise`  (
      `id` bigint unsigned NOT NULL primary key COMMENT 'ID',
      `name` varchar(255) COMMENT '企业名称',
      `user_id` bigint unsigned COMMENT '企业登录账号ID',
      `renewal_date` datetime COMMENT '续期时间',
      `expiration_date` datetime COMMENT '到期时间',
      `address` text COMMENT '公司地址',
      `business_license_id` bigint unsigned COMMENT '营业执照-图片ID',
      `legal_person` varchar(255) COMMENT '法人代表',
      `phone` varchar(24) COMMENT '公司电话',
      `mail` varchar(255) COMMENT '邮箱',
      `industry` varchar(255) COMMENT '所属行业',
      `notes` text  COMMENT '备注',
      `audit_state` tinyint DEFAULT 0 COMMENT '审核状态',
      `enable_state` tinyint DEFAULT 1 COMMENT '启用状态',
      `balance` decimal(14, 6) DEFAULT 0 COMMENT '当前余额',
      `create_time` datetime COMMENT '创建时间',
      `update_time` datetime COMMENT '修改时间',
      KEY `idx_user_id` (`user_id`)
  ) ENGINE = InnoDB CHARACTER SET = utf8mb4 COMMENT='企业';
  
  -- 部门
  CREATE TABLE `en_department`  (
      `id` bigint unsigned NOT NULL primary key COMMENT 'ID',
      `enterprise_id` bigint unsigned COMMENT '企业ID',
      `pid` bigint unsigned COMMENT '父级部门ID',
      `name` varchar(255)  COMMENT '部门名称',
      `code` varchar(255) COMMENT '部门编码',
      `manager` varchar(32)  COMMENT '部门负责人',
      `manager_id` bigint unsigned COMMENT '负责人ID',
      `introduce` text  COMMENT '介绍',
      `create_time` datetime  COMMENT '创建时间',
      `update_time` datetime COMMENT '更新时间',
      KEY `idx_enterprise_id` (`enterprise_id`)
  ) ENGINE = InnoDB CHARACTER SET = utf8mb4 COMMENT='部门';
  
  -- 成员
  CREATE TABLE `en_member` (
      `id` bigint unsigned NOT NULL primary key COMMENT 'ID',
      `enterprise_id` bigint unsigned COMMENT '企业ID',
      `department_id` bigint unsigned COMMENT '部门ID',
      `username` varchar(32) NOT NULL DEFAULT '' COMMENT '账号',
      `password` varchar(32) NOT NULL DEFAULT '' COMMENT '密码',
      `nickname` varchar(50) DEFAULT NULL COMMENT '昵称',
      `avatar` varchar(255) DEFAULT NULL COMMENT '用户头像',
      `token` varchar(100) DEFAULT NULL COMMENT '用户签名',
      `enable_state` tinyint unsigned DEFAULT 1 COMMENT '启用状态 0是禁用，1是启用',
      `join_time` datetime COMMENT '入职时间',
      `resign_time` datetime COMMENT '离职时间',
      `employ_form` tinyint unsigned COMMENT '聘用形式',
      `work_number` varchar(20) COMMENT '工号',
      `manage_form` varchar(8) COMMENT '管理形式',
      `working_city` varchar(16) COMMENT '工作城市',
      `correction_time` datetime COMMENT '转正时间',
      `job_status` tinyint unsigned COMMENT '在职状态 1.在职  2.离职',
      `staff_photo` varchar(255) COMMENT '员工照片', 
      `create_time` datetime  COMMENT '创建时间',
      `update_time` datetime COMMENT '更新时间',
      KEY `idx_enterprise_id` (`enterprise_id`),
      KEY `idx_department_id` (`department_id`)
      KEY `idx_member_info_id` (`member_info_id`)
  ) ENGINE = InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='成员';
  
  -- 成员履历
  CREATE TABLE `en_member` (
      `id` bigint unsigned NOT NULL primary key COMMENT 'ID',
      `member_id` bigint unsigned DEFAULT NULL COMMENT '员工编号',
      `name` varchar(32) NOT NULL DEFAULT '' COMMENT '姓名',
      `gender` tinyint unsigned COMMENT '性别 0.女 1.男',
      `birthday` date  COMMENT '出生日期',
      `id_card` char(18) COMMENT '身份证号',
      `wedlock` tinyint unsigned COMMENT '婚姻状况 1：已婚，2：未婚 ，3：离异',
      `nation` varchar(8) COMMENT '民族',
      `native_place` varchar(64) COMMENT '籍贯',
      `politics_status` varchar(16) COMMENT '政治面貌',
      `email` varchar(32) COMMENT '邮箱',
      `phone` varchar(11) COMMENT '电话',
      `address` varchar(64) COMMENT '联系地址',
      `position` varchar(16) COMMENT '职位',
      `education` varchar(16) COMMENT '学历',
      `school` varchar(32) COMMENT '毕业院校',
      `create_time` datetime  COMMENT '创建时间',
      `update_time` datetime COMMENT '更新时间',
      KEY `idx_member_id` (`member_id`),
      KEY `idx_enterprise_id` (`enterprise_id`),
      KEY `idx_department_id` (`department_id`)，
  ) ENGINE = InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='成员履历';
  ```

  <br>

+ 薪酬管理（sa)

  ```mysql
  -- 薪酬
  CREATE TABLE `sa_salary` (
      `id` bigint unsigned NOT NULL primary key COMMENT 'ID',
      `basic_salary` decimal(11, 6) DEFAULT NULL COMMENT '基本工资',
      `bonus` decimal(11, 6) DEFAULT NULL COMMENT '奖金',
      `lunch_salary` decimal(11, 6) DEFAULT NULL COMMENT '午餐补助',
      `traffic_salary` decimal(11, 6) DEFAULT NULL COMMENT '交通补助',
      `all_salary` decimal(11, 6) DEFAULT NULL COMMENT '应发工资',
      `pension_base` decimal(11, 6) DEFAULT NULL COMMENT '养老金基数',
      `pension_rate` decimal(11, 6) DEFAULT NULL COMMENT '养老金比率',
      `start_date` datetime COMMENT '启用时间',
      `medical_base` decimal(11, 6) DEFAULT NULL COMMENT '医疗基数',
      `medical_rate` decimal(11, 6) DEFAULT NULL COMMENT '医疗保险比率',
      `accumulation_fund_base` decimal(11, 6) DEFAULT NULL COMMENT '公积金基数',
      `accumulation_fund_rate` decimal(11, 6) DEFAULT NULL COMMENT '公积金比率',
      `name` varchar(32) DEFAULT NULL,
      `create_time` datetime  COMMENT '创建时间',
      `update_time` datetime COMMENT '更新时间',
  ) ENGINE = InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='薪酬';
  
  -- 薪酬调整
  CREATE TABLE `sa_adjust` (
      `id` bigint unsigned NOT NULL primary key COMMENT 'ID',
      `member_id` bigint unsigned DEFAULT NULL COMMENT '员工编号',
      `adjust_date` date DEFAULT NULL COMMENT '调薪日期',
      `before_salary` decimal(11, 6) DEFAULT NULL COMMENT '调前薪资',
      `after_salary` decimal(11, 6) DEFAULT NULL COMMENT '调后薪资',
      `reason` varchar(255) DEFAULT NULL COMMENT '调薪原因',
      `notes` varchar(255) DEFAULT NULL COMMENT '备注',
      `create_time` datetime  COMMENT '创建时间',
      `update_time` datetime COMMENT '更新时间',
      KEY `pid` (`mid`)
  ) ENGINE = InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='薪酬调整';
  
  -- 奖惩
  CREATE TABLE `sa_rewards_punishment` (
      `id` bigint unsigned NOT NULL primary key COMMENT 'ID',
      `member_id` bigint unsigned DEFAULT NULL COMMENT '员工编号',
      `rp_date` date DEFAULT NULL COMMENT '奖罚日期',
      `rp_reason` varchar(255) DEFAULT NULL COMMENT '奖罚原因',
      `rp_point` int(11) DEFAULT NULL COMMENT '奖罚分',
      `rp_type` tinyint unsigned DEFAULT NULL COMMENT '奖罚类别，0：奖，1：罚',
      `notes` varchar(255) DEFAULT NULL COMMENT '备注',
      KEY `pid` (`mid`)
  ) ENGINE = InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='奖惩';
  ```

  <br>

+ 培训管理

  ```mysql
  -- 培训
  CREATE TABLE `em_train` (
      `id` bigint unsigned NOT NULL primary key COMMENT 'ID',
      `member_id` bigint unsigned DEFAULT NULL COMMENT '员工编号',
      `train_date` date DEFAULT NULL COMMENT '培训日期',
      `train_content` varchar(255) DEFAULT NULL COMMENT '培训内容',
      `notes` varchar(255) DEFAULT NULL COMMENT '备注',
      `create_time` datetime  COMMENT '创建时间',
      `update_time` datetime COMMENT '更新时间',
      KEY `pid` (`mid`)
  ) ENGINE = InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='培训';
  
  -- 员工调动
  CREATE TABLE `em_transfer` (
      `id` bigint unsigned NOT NULL primary key COMMENT 'ID',
      `member_id` bigint unsigned DEFAULT NULL COMMENT '员工编号',
      `after_dep_id` bigint unsigned DEFAULT NULL COMMENT '调动后部门',
      `after_job_id` bigint unsigned DEFAULT NULL COMMENT '调动后职位',
      `transfer_date` date DEFAULT NULL COMMENT '调动日期',
      `reason` varchar(255) DEFAULT NULL COMMENT '调动原因',
      `notes` varchar(255) DEFAULT NULL,
      `create_time` datetime  COMMENT '创建时间',
      `update_time` datetime COMMENT '更新时间',
      KEY `pid` (`mid`)
  ) ENGINE = InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='人员变动';
  ```
  
  <br>
  
  
  
+ 考勤

  > 1. 员工基本信息管理，增加，删除，修改
  >
  > 2. 员工的出勤记录，上下班打卡时间，缺勤记录。
  >
  > 3. 员工的请假记录，请假开始时间和结束时间，请假类型。
  >
  > 4. 员工的加班记录，加班开始时间和结束时间，加班类型。
  >
  > 5. 员工的出差记录，出差起始时间和结束时间，出差类型。
  
  ```mysql
  -- 考勤
  /*
  CREATE TABLE `atte_attendance`  (
      `id` bigint unsigned NOT NULL primary key COMMENT 'ID',
      `member_id` bigint unsigned DEFAULT NULL COMMENT '员工编号',
      `department_id` bigint unsigned COMMENT '部门ID',
      `atd_statu` tinyint unsigned DEFAULT NULL COMMENT '考情状态 1正常2旷工3迟到4早退5外出6年假7事假8病假9产假10调休11补签',
      `job_statu` tinyint unsigned NULL DEFAULT NULL COMMENT '职位状态 1在职2离职',
      `atd_in_time` datetime DEFAULT NULL COMMENT '上班考勤时间',
      `atd_in_place` varchar(30) DEFAULT NULL COMMENT '考勤地点',
      `atd_out_time` datetime NULL DEFAULT NULL COMMENT '下班考勤时间',
      `atd_out_place` varchar(30) DEFAULT NULL COMMENT '下班考情地点',
      `create_by` varchar(64) DEFAULT NULL COMMENT '创建人',
      `update_by` varchar(64) DEFAULT NULL COMMENT '更新人',
      `notes` varchar(255) DEFAULT NULL,
      `create_time` datetime  COMMENT '创建时间',
      `update_time` datetime COMMENT '更新时间',
      `username` varchar(40) DEFAULT NULL,
      `mobile` varchar(255) DEFAULT NULL,
      `department_name` varchar(40) DEFAULT NULL,
      `day` varchar(40) DEFAULT NULL,
  ) ENGINE = InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='出勤';
  */
  
  -- 考勤
  CREATE TABLE `atte_attendance`  (
      `id` bigint unsigned NOT NULL primary key COMMENT 'ID',
      `member_id` bigint unsigned DEFAULT NULL COMMENT '员工编号',
      `atd_in_time` datetime DEFAULT NULL COMMENT '上班考勤时间',
      `atd_in_place` varchar(30) DEFAULT NULL COMMENT '考勤地点',
      `atd_out_time` datetime NULL DEFAULT NULL COMMENT '下班考勤时间',
      `atd_out_place` varchar(30) DEFAULT NULL COMMENT '下班考勤地点',
      `notes` varchar(255) DEFAULT NULL COMMENT '备注',
      `create_time` datetime  COMMENT '创建时间',
      `update_time` datetime COMMENT '更新时间',
  ) ENGINE = InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='出勤';
  
  -- 考勤统计
  CREATE TABLE `atte_archive_monthly`  (
      `id` bigint unsigned NOT NULL primary key COMMENT 'ID',
      `enterprise_id` bigint unsigned COMMENT '企业ID',
      `department_id` bigint unsigned COMMENT '部门ID',
      `archive_year` varchar(36) DEFAULT NULL COMMENT '归档年份',
      `archive_month` varchar(36) DEFAULT NULL COMMENT '归档月份',
      `total_member_num` int(36) DEFAULT NULL COMMENT '总人数',
      `full_atte_member_num` int(36) DEFAULT NULL COMMENT '全勤人数',
      `is_archived` int(20) DEFAULT NULL COMMENT '是否归档(0已经归档1没有归档)',
      `notes` varchar(255) DEFAULT NULL,
      UNIQUE INDEX `enterprise_id`(`enterprise_id`, `department_id`, `archive_year`, `archive_month`) USING BTREE
  ) ENGINE = InnoDB DEFAULT CHARSET=utf8mb4;
  ```
  
  <br>
  
+ 统计

  ```mysql
  -- 日常统计
  CREATE TABLE `statistics_daily` (
      `id` bigint unsigned NOT NULL primary key COMMENT 'ID',
      `date_calculated` varchar(20) NOT NULL COMMENT '统计日期',
      `register_num` int(11) NOT NULL DEFAULT '0' COMMENT '注册人数',
      `login_num` int(11) NOT NULL DEFAULT '0' COMMENT '登录人数',
      `create_time` datetime  COMMENT '创建时间',
      `update_time` datetime COMMENT '更新时间',
      PRIMARY KEY (`id`),
      KEY `idx_statistics_day` (`date_calculated`)
  ) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='网站统计日数据';
  ```

  

## 核心代码

+ 统一数据返回

  ```ruby
  @Data
  public class R {
  	private Boolean success;
  	private Integer code;
  	private String message;
  	private Map<String, Object> data;
  	private static volatile R r = null;
      
  	private R(){}
  	private static R getR(){
  		if(null == r){
  			synchronized (R.class){
  				if(null == r){
  					r = new R();
  				}
  			}
  		}
  		return r;
  	}
  	/**
  	 * 成功静态方法
  	 * @return R
  	 */
  	public static R ok(){
  		R r = getR();
  		r.setSuccess(true);
  		r.setCode(ResultCodeEnum.SUCCESS.getCode());
  		r.setMessage(ResultCodeEnum.SUCCESS.getMessage());
  		r.setData(new HashMap<>(16));
  		return r;
  	}
  	/**
  	 * 失败静态方法
  	 * @return R
  	 */
  	public static R err(){
  		R r = getR();
  		r.setSuccess(false);
  		r.setCode(ResultCodeEnum.ERROR.getCode());
  		r.setMessage(ResultCodeEnum.ERROR.getMessage());
  		r.setData(new HashMap<>(16));
  		return r;
  	}
  	public R message(String message){
  		this.setMessage(message);
  		return this;
  	}
  	public R code(Integer code){
  		this.setCode(code);
  		return this;
  	}
  	public R data(String key, Object value){
  		this.data.put(key, value);
  		return this;
  	}
  	public R data(Map<String, Object> map) {
  		this.setData(map);
  		return this;
  	}
  }
  ```

  

+ 全局异常处理

  ```ruby
  @Slf4j
  @RestControllerAdvice
  public class GlobalExceptionHandler {
  	/**
  	 * 全局参数校验
  	 * @param e 异常类型
  	 * @return 异常信息
  	 */
  	@ExceptionHandler(value = MethodArgumentNotValidException.class)
  	public R handleValidException(MethodArgumentNotValidException e){
  		log.error("数据校验出现问题{}, 异常类型{}", e.getMessage(), e.getStackTrace());
  		BindingResult bindingResult = e.getBindingResult();
  		Map<String, String> map = new HashMap<>(16);
  	    bindingResult.getFieldErrors().forEach(r -> {
              map.put(r.getDefaultMessage(), r.getField());
  	    });
  		return R.err().code(ResultCodeEnum.INVALID_EXCEPTION.getCode())
  				.message(ResultCodeEnum.INVALID_EXCEPTION.getMessage()).data("data", map);
  	}
  	/**
  	 * 自定义异常
  	 */
  	@ExceptionHandler(HRMSException.class)
  	public R hrmsException(HRMSException e){
  		log.error("自定义异常 -> ", e);
  		return R.err().code(e.getResultCodeEnum().getCode())
  				.message(e.getResultCodeEnum().getMessage());
  	}
  	/**
  	 * 全局异常处理
  	 */
  	@ExceptionHandler(Exception.class)
  	public R globalException(Exception e){
  		log.error("全局异常 -> ", e);
  		return R.err().code(ResultCodeEnum.UNKNOWN_EXCEPTION.getCode())
  				.message(ResultCodeEnum.UNKNOWN_EXCEPTION.getMessage());
  	}
  }
  ```

  

## 功能截图



