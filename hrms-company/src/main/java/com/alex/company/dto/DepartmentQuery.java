package com.alex.company.dto;

import com.alex.common.base.BaseQuery;
import com.alex.common.valid.group.QueryGroup;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @Author _Alexzinv_
 * @Date 2021/11/8
 * @Description
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class DepartmentQuery extends BaseQuery implements Serializable {

    private static final long serialVersionUID = 1L;

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
