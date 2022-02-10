package com.alex.system.dto;


import com.alex.common.base.AbstractBaseQuery;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @Author _Alexzinv_
 * @Date 2021/9/23
 * @Description 查询条件对象
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class UserQueryVO extends AbstractBaseQuery {

    /**
     * 账号或昵称
     */
    private String name;

    /**
     * 启用状态 0是禁用，1是启用
     */
    private Integer enableState;

    /**
     * 企业ID
     */
    private Long companyId;

    /**
     * 账号级别
     */
    private Integer level;
}
