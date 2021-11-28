package com.alex.system.dto;


import lombok.Data;

/**
 * @Author _Alexzinv_
 * @Date 2021/9/23
 * @Description 查询条件对象
 */
@Data
public class UserQueryVO {

    /**
     * 账号
     */
    private String username;

    /**
     * 昵称
     */
    private String nickname;

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
}
