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

+ 技术: `Spring Boot` + `Spring Cloud` + `Spring Security` + `Nacos` + `Vue`+ `Mybatis-Plus` + `Mysql`  + `redis` + `zipkin` + `druid` + `sentinel`
+ <img src="https://markdown-alex.oss-cn-chengdu.aliyuncs.com/HRMS/HRMS系统架构.png" alt="hrms架构" style="zoom: 80%;" />

+ ```shell
  micro-hrms
  ├─db  项目SQL语句
  │
  ├─hrms-common 公共模块
  │  ├─aspect 系统日志
  │  ├─exception 异常处理
  │  ├─validator 后台校验
  │  └─xss XSS过滤
  │ 
  ├─hrms-system 配置信息
  │ 
  ├─hrms-thirdpart 功能模块
  │  ├─app API接口模块(APP调用)
  │  ├─job 定时任务模块
  │  ├─oss 文件服务模块
  │  └─sys 权限模块
  │ 
  ├─RenrenApplication 项目启动类
  │  
  ├──resources 
  │  ├─mapper SQL对应的XML文件
  │  └─static 静态资源
  ```



## 认证和校验

+ Security认证和授权
  ```sequence
    浏览器 -> 授权:输入账号密码，根据账号生成token, 并将{账号:权限}存入redis缓存
    授权 -> 浏览器:返回授权token
    浏览器 -> 网关:请求带上授权token
    网关 -> 浏览器:校验token失败，返回错误信息
    网关 -> 接口:校验token成功，调用接口
    接口 -> 浏览器:返回接口数据
  ```
  
+ 网关全局过滤器校验流程

  ```flow
  s=>start: 前端请求
  e=>end: 无需认证的接口
  e1=>end: 认证后访问的接口
  err=>end: 接口不允许外部访问
  err1=>end: 没有token，校验失败
  err2=>end: token无效或未登录
  conw=>condition: 是否在白名单?
  conb=>condition: 是否在黑名单?
  contoken=>condition: 是否包含token?
  opparse=>operation: 解析token得到key
  conhaskey=>condition: redis是否有对应key
  
  s->conb
  conb(yes)->err
  conb(no)->conw
  conw(yes)->e
  conw(no)->contoken
  contoken(yes)->opparse->conhaskey
  contoken(no)->err1
  conhaskey(yes)->e1
  conhaskey(no)->err2
  ```
  
  






## 数据库设计

+ 权限管理（sys)

  ```mysql
  -- 系统权限菜单
  CREATE TABLE `sys_permission`
  (
      `id`               bigint                 not null comment 'ID' primary key,
      `pid`              bigint      default 0  not null comment '所属上级',
      `name`             varchar(20) default '' not null comment '名称',
      `type`             tinyint     default 1  not null comment '类型 [1:菜单,2:按钮,3:Api]',
      `permission_value` varchar(50)            null comment '权限值',
      `path`             varchar(100)           null comment '访问路径',
      `component`        varchar(100)           null comment '组件路径',
      `icon`             varchar(255)           null comment '图标',
      `status`           tinyint                null comment '状态 [0:禁止,1:正常]',
      `create_time`      datetime               null comment '创建时间',
      `update_time`      datetime               null comment '更新时间',
      KEY `idx_pid` (`pid`)
  ) ENGINE = InnoDB CHARSET=utf8mb4 COMMENT='权限菜单';
  
  -- 角色
  CREATE TABLE `sys_role` (
      `id`               bigint                 not null comment 'ID' primary key,
      `role_name`        varchar(20) default '' not null comment '角色名称',
      `role_code`   	   varchar(20)            null comment '角色编码',
      `notes`            varchar(255)           null comment '备注',
      `create_time`      datetime               null comment '创建时间',
      `update_time`      datetime               null comment '更新时间'
  ) ENGINE = InnoDB DEFAULT CHARSET=utf8 COMMENT='角色';
  
  -- 角色权限关联表
  CREATE TABLE `sys_role_permission` (
      `id`               bigint                 not null comment 'ID' primary key,
      `role_id`          bigint                 not null,
      `permission_id`    bigint                 not null,
      `create_time`      datetime               null comment '创建时间',
      `update_time`      datetime               null comment '更新时间',
      KEY `idx_role_id` (`role_id`),
      KEY `idx_permission_id` (`permission_id`)
  ) ENGINE = InnoDB DEFAULT CHARSET=utf8 COMMENT='角色权限关联表';
  
  -- 系统用户
  CREATE TABLE `sys_user` (
      `id`               bigint                  not null comment 'ID' primary key,
      `username`         varchar(32)  default '' not null comment '账号',
      `password`         varchar(255) default '' not null comment '密码',
      `nickname`         varchar(50)             null comment '昵称',
      `avatar`           varchar(255)            null comment '用户头像',
      `enable_state`     tinyint      default 1  null comment '启用状态 0是禁用，1是启用',
      `company_id`       bigint                  null comment '企业ID',
      `create_time`      datetime                null comment '创建时间',
      `update_time`      datetime                null comment '更新时间',
      `level`            tinyint                 null comment '账户级别',
      `token`            varchar(255)            null comment '账号token',
      KEY `idx_company_id` (`company_id`)
  ) ENGINE = InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户';
  
  -- 用户角色关联表
  CREATE TABLE `sys_user_role` (
      `id`               bigint                  not null comment 'ID' primary key,
      `role_id`          bigint                  not null comment '角色id',
      `user_id`          bigint                  not null comment '用户id',
      `create_time`      datetime                null comment '创建时间',
      `update_time`      datetime                null comment '更新时间',
      KEY `idx_role_id` (`role_id`),
      KEY `idx_user_id` (`user_id`)
  ) ENGINE = InnoDB DEFAULT CHARSET=utf8 COMMENT='用户角色关联表';
  ```
  
  <br>
  
+ 公司管理（co）

  ```mysql
  -- 公司信息表
  CREATE TABLE `co_company`
  (
      `id`                   bigint unsigned       not null comment 'ID' primary key,
      `name`                 varchar(255)          null comment '公司名称',
      `manager_id`           bigint unsigned       null comment '企业登录账号ID',
      `version`              varchar(255)          null comment '当前版本',
      `renewal_date`         datetime              null comment '续期时间',
      `expiration_date`      datetime              null comment '到期时间',
      `company_address`      text                  null comment '公司地址',
      `business_license_url` varchar(255)          null comment '营业执照-图片ID',
      `legal_representative` varchar(255)          null comment '法人代表',
      `company_phone`        varchar(255)          null comment '公司电话',
      `mailbox`              varchar(255)          null comment '邮箱',
      `company_size`         varchar(255)          null comment '公司规模',
      `industry`             varchar(255)          null comment '所属行业',
      `remarks`              text                  null comment '备注',
      `audit_state`          tinyint unsigned      null comment '审核状态 0:未审核 1:已审核',
      `state`                tinyint unsigned      null comment '状态 0:未激活 1:已激活',
      `balance`              decimal(18, 4)        null comment '当前余额',
      `create_time`          datetime              null comment '创建时间',
      `update_time`          datetime              null comment '更新时间',
      KEY `idx_manager_id` (`manager_id`)
  )  ENGINE = InnoDB DEFAULT CHARSET=utf8 COMMENT='公司信息表'
  
  -- 部门表
  CREATE TABLE `co_department`  (
      `id`          bigint       not null primary key,
      `company_id`  bigint       null comment '企业ID',
      `pid`         bigint       null comment '父级部门ID',
      `name`        varchar(255) null comment '部门名称',
      `code`        varchar(255) null comment '部门编码',
      `manager`     varchar(32)  null comment '部门负责人',
      `manager_id`  bigint       null comment '负责人ID',
      `introduce`   text         null comment '介绍',
      `create_time` datetime     null comment '创建时间',
      `update_time` datetime     null comment '更新时间',
      KEY `idx_company_id` (`company_id`),
      KEY `idx_pid` (`pid`),
      KEY `idx_manager_id` (`manager_id`)
  ) ENGINE = InnoDB CHARACTER SET = utf8mb4 COMMENT='部门';
  
  -- 岗位表
  CREATE TABLE `co_position`  (
      `id`          bigint           not null primary key,
      `company_id`  bigint           null comment '企业ID',
      `name`        varchar(32)      null comment '岗位名称',
      `status`      tinyint unsigned null comment '启用状态 0:禁用 1:启用',
      `sort`        int              null comment '显示顺序',
      `create_time` datetime         null comment '创建时间',
      `update_time` datetime         null comment '更新时间',
      KEY `idx_company_id` (`company_id`)
  ) ENGINE = InnoDB CHARACTER SET = utf8mb4 COMMENT='岗位';
  ```

  <br>

+ 用户管理（mem)

  ```mysql
  -- sys_user扩展表 => 用户入职信息表
  CREATE TABLE `mem_user_company`
  (
      `id`              bigint                 not null comment 'ID'primary key,
      `username`        varchar(32) default '' not null comment '账号',
      `work_number`     bigint                 null comment '工号',
      `nickname`        varchar(32)            null comment '昵称',
      `company_id`      bigint                 null comment '企业ID',
      `department_id`   bigint                 null comment '部门ID',
      `department_name` varchar(64)            null comment '部门名称',
      `position_id`     bigint                 null comment '岗位id',
      `position`        varchar(32)            null comment '岗位名称',
      `join_time`       datetime               null comment '入职时间',
      `resign_time`     datetime               null comment '离职时间',
      `employ_form`     tinyint                null comment '聘用形式',
      `working_city`    varchar(16)            null comment '工作城市',
      `correction_time` datetime               null comment '转正时间',
      `job_status`      tinyint                null comment '在职状态 1.在职  2.离职',
      `staff_photo`     varchar(255)           null comment '员工照片',
      `create_time`     datetime               null comment '创建时间',
      `update_time`     datetime               null comment '更新时间',
      KEY `idx_work_number` (`work_number`),
      KEY `idx_company_id` (`company_id`),
      KEY `idx_department_id` (`department_id`),
      KEY `idx_position_id` (`position_id`)
  ) ENGINE = InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='user公司扩展表';
  
  -- sys_user扩展表 => 个人履历
  CREATE TABLE `mem_user_personal_info`
  (
      `id`              bigint unsigned        not null comment 'ID' primary key,
      `name`            varchar(32)            null comment '姓名',
      `gender`          tinyint unsigned       null comment '性别  0：女，1：男',
      `birthday`        date                   null comment '出生日期',
      `id_card`         char(18)               null comment '身份证号',
      `wedlock`         tinyint unsigned       null comment '婚姻状况  1：已婚，2：未婚 ，3：离异',
      `nation`          varchar(8)             null comment '民族',
      `native_place`    varchar(64)            null comment '籍贯',
      `politics_status` tinyint unsigned       null comment '政治面貌',
      `email`           varchar(32)            null comment '邮箱',
      `phone`           varchar(11)            null comment '电话',
      `address`         varchar(64)            null comment '联系地址',
      `education`       tinyint unsigned       null comment '学历',
      `school`          varchar(32)            null comment '毕业院校',
      `create_time`     datetime               null comment '创建时间',
      `update_time`     datetime               null comment '更新时间'
  )  ENGINE = InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户个人信息';
  ```

  <br>

+ 薪酬管理（sa)

  ```mysql
  -- 薪酬
  CREATE TABLE `sa_salary_common` (
      `id`              bigint unsigned        not null primary key comment '企ID',
      `company_id`      bigint unsigned        null comment '企业ID',
      `lunch_salary`    decimal(11, 6)         null comment '午餐补助',
      `traffic_salary`  decimal(11, 6)         null comment '交通补助',
      `pension_base`    decimal(11, 6)         null comment '养老金基数',
      `pension_rate`    decimal(11, 6)         null comment '养老金比率',
      `medical_base`    decimal(11, 6)         null comment '医疗基数',
      `medical_rate`    decimal(11, 6)         null comment '医疗保险比率',
      `accumulation_fund_base` decimal(11, 6)  null comment '公积金基数',
      `accumulation_fund_rate` decimal(11, 6)  null comment '公积金比率',
      `start_date`      datetime               null comment '启用时间',
      `create_time`     datetime               null comment '创建时间',
      `update_time`     datetime               null comment '更新时间',
      KEY `idx_company_id` (`company_id`)
  ) ENGINE = InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='员工薪酬公共表';
  
  CREATE TABLE `sa_salary_personal` (
      `id`               bigint unsigned       not null primary key comment '工号',
      `salary_common_id` bigint unsigned       null comment '工资公共部分id',
      `basic_salary`     decimal(11, 6)        null comment '基本工资',
      `bonus`            decimal(11, 6)        null comment '奖金',
      `total_salary`     decimal(11, 6)        null comment '应发工资',
      `create_time`      datetime              null comment '创建时间',
      `update_time`      datetime              null comment '更新时间',
      KEY `idx_salary_common_id` (`salary_common_id`)
  ) ENGINE = InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='员工薪酬个人表';
  
  -- 薪酬调整
  CREATE TABLE `sa_salary_adjust` (
      `id`               bigint unsigned       not null primary key comment 'id',
      `salary_personal_id`  bigint unsigned    null comment '工号',
      `before_salary`    decimal(11, 6)        null comment '调前薪资',
      `after_salary`     decimal(11, 6)        null comment '调后薪资',
      `reason`           varchar(255)          null comment '调薪原因',
      `notes`            varchar(255)          null comment '备注',
      `create_time`      datetime              null comment '创建时间',
      `update_time`      datetime              null comment '更新时间',
      KEY `idx_salary_personal_id` (`salary_personal_id`)
  ) ENGINE = InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='薪酬调整';
  
  -- 奖惩
  CREATE TABLE `sa_rewards_punishment` (
      `id`               bigint unsigned       not null primary key comment 'id',
      `salary_personal_id`  bigint unsigned    null comment '工号',
      `rp_reason`        varchar(255)          null comment '奖罚原因',
      `rp_point`         int(6)                null comment '奖罚分',
      `rp_money`         decimal(11, 6)        null comment '奖罚金额',
      `rp_type`          tinyint unsigned      null comment '奖罚类别，0：奖，1：罚',
      `notes`            varchar(255)          null comment '备注',
      `create_time`      datetime              null comment '创建时间',
      `update_time`      datetime              null comment '更新时间',
       KEY `idx_salary_personal_id` (`salary_personal_id`)
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
  @Getter
  @Setter
  public class R {
  	/** 成功 */
  	private static final int SUCCESS = 20000;
  	private static final String SUCCESS_MESSAGE = "操作成功";
  	/** 失败 */
  	private static final int ERROR = 20001;
  	private static final String ERROR_MESSAGE = "操作成功";
      
  	private boolean success;
  	private Integer code;
  	private String message;
  	private Map<String, Object> data = new HashMap<>(32);
  
  	private R(){}
  	/** 成功静态方法 @return R */
  	public static R ok(){
  		R r = new R();
  		r.success = true;
  		r.code(SUCCESS);
  		r.message(SUCCESS_MESSAGE);
  		return r;
  	}
  	/** 失败静态方法 @return R */
  	public static R err(){
  		R r = new R();
  		r.success = false;
  		r.code(ERROR);
  		r.message(ERROR_MESSAGE);
  		return r;
  	}
  
  	public R result(ResultCodeEnum codeEnum){
  		code(codeEnum.getCode());
  		message(codeEnum.getMessage());
  		return this;
  	}
  
  	public R message(String message){
  		this.message = message;
  		return this;
  	}
  
  	public R code(Integer code){
  		this.code = code;
  		return this;
  	}
  
  	public R data(String key, Object value){
  		this.data.put(key, value);
  		return this;
  	}
  
  	public R data(Map<String, Object> map) {
        if(this.data.isEmpty()){
            this.data = map;
        }else {
            this.data.putAll(map);
        }
        return this;
    }
  }
  ```
  
+ ControllerAspect

  ```ruby
  /** @description 输出被调用的controller和方法名字和参数到日志 **/
  @Slf4j
  @Aspect
  @Component
  public class ControllerAspect {
  
      @Pointcut("execution(* com.alex.*.controller.*.*(..))")
      public void controllerMethod(){ }
  
      @Before("controllerMethod()")
      public void before(JoinPoint joinPoint){
          String className = joinPoint.getTarget().getClass().getName();
          String methodName = joinPoint.getSignature().getName();
          Object[] args = joinPoint.getArgs();
          log.info("\n AspectJ --> className:" + className + " --> methodName: " +
                  methodName + " --> args: " + Arrays.toString(args));
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
  		Map<String, String> map = new HashMap<>(4);
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
  
+ 编码生成工具

  ```ruby
  /** @description 编码生成工具 */
  public abstract class CodePrefixUtils {
      /** 获取最新编码, 根据具体服务实现 @return 最新编码 */
      protected abstract String getLatestCode();
  
      /** 根据前缀生成编码 @param codePrefixEnum 编码前缀枚举 @return 编码 */
      public synchronized String getCode(CodePrefixEnum codePrefixEnum) {
          String codePrefix = codePrefixEnum.getPrefix();
          String code = getLatestCode();
          // 为空则是第一次添加，初始化，否则按最大值自增
          if(code == null){
              return codePrefix + CustomSerialGenerator.initCode();
          }
          String newCode = code.replace(codePrefix, "");
          long value = Long.parseLong(newCode);
          return codePrefix + ++value;
      }
  }
  ```

+ seccurity主配置

  ```ruby
  /** 配置设置 @param http http请求 @throws Exception 异常 */
  @Override
  protected void configure(HttpSecurity http) throws Exception {
      http.exceptionHandling()
      .authenticationEntryPoint(new UnauthorizedEntryPoint())
      .and().csrf().disable()
      .authorizeRequests()
      .anyRequest().authenticated()
      .and().logout().logoutUrl("/admin/acl/info/logout")
      .addLogoutHandler(new TokenLogoutHandler(tokenManager,redisTemplate)).and()
      .addFilter(new TokenLoginFilter(authenticationManager(), tokenManager, redisTemplate))
      .addFilter(new TokenAuthenticationFilter(authenticationManager(),
          tokenManager, redisTemplate)).httpBasic();
      /// 一个用户只能创建一个Session，后登录挤掉先登录用户
      http.sessionManagement()
      .maximumSessions(1)
      .expiredUrl("/admin/acl/login")
      .maxSessionsPreventsLogin(false)
      .expiredSessionStrategy(new CustomExpiredSessionStrategy());
  }
  ```

  


+ 网关
  ```ruby
  /** @description 全局过滤器 **/
  @Component
  @Slf4j
  public class AuthGlobalFilter implements GlobalFilter, Ordered {
      private final RedisTemplate<String, Object> redisTemplate;
      private final BlackList blackList;
      private final WhiteList whiteList;
  
      @Autowired
      public AuthGlobalFilter(RedisTemplate<String, Object> redisTemplate, BlackList blackList, WhiteList whiteList) {
          this.redisTemplate = redisTemplate;
          this.blackList = blackList;
          this.whiteList = whiteList;
      }
  
      @Override
      public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
          ServerHttpRequest request = exchange.getRequest();
          String path = request.getURI().getPath();
          log.info("=================" + path);
          // 白名单
          if(AccessFilterUtils.match(path, whiteList.getWhiteList())){
              return chain.filter(exchange);
          }
          // 禁止外部访问接口
          if(AccessFilterUtils.match(path, blackList.getBlackList())){
              return outResponse(exchange);
          }
          String token = getToken(request);
          if(StringUtils.isBlank(token)) {
              return outResponse(exchange);
          }
          if(!checkToken(token)){
              return outResponse(exchange);
          }
          return chain.filter(exchange);
      }
  
      @Override
      public int getOrder() {
          return 0;
      }
  
      private Mono<Void> out(ServerHttpResponse response) {
          R r = R.err().result(ResultCodeEnum.GATEWAY_AUTH_EXCEPTION);
          byte[] bits = r.toString().getBytes(StandardCharsets.UTF_8);
          DataBuffer buffer = response.bufferFactory().wrap(bits);
          response.getHeaders().add("Content-Type", "application/json;charset=UTF-8");
          return response.writeWith(Mono.just(buffer));
      }
  
      /** 获取请求token */
      private String getToken(ServerHttpRequest request) {
          String token = request.getHeaders().getFirst(TokenConstant.AUTHENTICATION);
          // 如果前端设置了令牌前缀，则裁剪掉前缀
          if (StringUtils.isNotEmpty(token) && token.startsWith(TokenConstant.PREFIX)) {
              token = token.replaceFirst(TokenConstant.PREFIX, StringUtils.EMPTY);
          }
          return token;
      }
  
      /** 验证token */
      private boolean checkToken(String token) {
          String s = Jwts.parser().setSigningKey(TokenConstant.SECRET).parseClaimsJws(token).getBody().getSubject();
          Boolean hasKey = redisTemplate.hasKey(s);
          return hasKey != null && hasKey;
      }
  
      private Mono<Void> outResponse(ServerWebExchange exchange){
          ServerHttpResponse response = exchange.getResponse();
          return out(response);
      }
  }
  ```
  
  ```ruby
  /** @description 请求限流 */
  @Configuration
  public class SentinelRuleConfig {
  
      /** 配置限流过滤器 */
      @Bean
      @Order(-1)
      public GlobalFilter sentinelGatewayFilter() {
          return new SentinelGatewayFilter();
      }
  
      @PostConstruct
      public void doInit() {
          initCustomizedApis();
          initGatewayRules();
      }
  
      /** 网关限流规则 */
      private void initGatewayRules() {
          Set<GatewayFlowRule> rules = new HashSet<>();
          rules.add(new GatewayFlowRule("hrms-system")
                  // 限流阈值
                  .setCount(6)
                  // 时间间隔，单位是秒，默认是 1 秒
                  .setIntervalSec(2)
          );
          rules.add(new GatewayFlowRule("hrms-company")
                  .setCount(4)
                  .setIntervalSec(2)
          );
          // 加载网关限流规则
          GatewayRuleManager.loadRules(rules);
      }
  
      /** 网关分组限流 */
      private void initCustomizedApis() {
          Set<ApiDefinition> definitions = new HashSet<>();
          ApiDefinition api1 = new ApiDefinition("hrms-system").setPredicateItems(
                  new HashSet<>() {{
                      add(new ApiPathPredicateItem().setPattern("/admin/acl/permission/listAll"));
                      add(new ApiPathPredicateItem().setPattern("/admin/acl/role/**"));
                  }});
  
          ApiDefinition api2 = new ApiDefinition("hrms-company").setPredicateItems(
                  new HashSet<>() {{
                      add(new ApiPathPredicateItem().setPattern("/company/company/listPage/**"));
                      add(new ApiPathPredicateItem().setPattern("/company/department/list/**"));
                      add(new ApiPathPredicateItem().setPattern("/company/position/listPage/**"));
                  }});
          definitions.add(api1);
          definitions.add(api2);
          GatewayApiDefinitionManager.loadApiDefinitions(definitions);
      }
  }
  ```
  
  

## 功能截图



