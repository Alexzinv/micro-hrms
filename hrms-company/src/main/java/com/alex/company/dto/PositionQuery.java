package com.alex.company.dto;

import com.alex.common.base.AbstractBaseQuery;
import com.alex.common.valid.ListValue;
import com.alex.common.valid.group.QueryGroup;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @author _Alexzinv_
 * @date 2021/12/15
 * @description position查询条件
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class PositionQuery extends AbstractBaseQuery implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "企业ID")
    @NotNull(groups = {QueryGroup.class})
    private Long companyId;

    @ApiModelProperty(value = "岗位名称")
    private String name;

    @ApiModelProperty(value = "启用状态 0:禁用 1:启用")
    @ListValue(values = {0, 1}, groups = {QueryGroup.class})
    private Integer status;

    @ApiModelProperty(value = "排序方向")
    private Integer sort;
}
