package com.alex.company.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @Author _Alexzinv_
 * @Date 2021/11/8
 * @Description
 */
@Data
public class DepartmentQuery {

    /**
     * 公司id
     */
    @NotNull
    private Long companyId;
    /**
     * 查询关键字[name, code]
     */
    private String key;

    /**
     * 部门负责人
     */
    private String manager;
}
