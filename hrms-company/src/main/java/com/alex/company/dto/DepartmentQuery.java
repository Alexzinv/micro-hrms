package com.alex.company.dto;

import com.alex.common.valid.group.QueryGroup;
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
    @NotNull(groups = {QueryGroup.class})
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
