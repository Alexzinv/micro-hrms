package com.alex.company.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * @author _Alexzinv_
 * @date 2021/12/15
 * @description position查询条件
 */
@Data
public class PositionQuery {

    @ApiModelProperty(value = "企业ID")
    @NotNull
    private Long companyId;

    @ApiModelProperty(value = "岗位名称")
    private String name;

    @ApiModelProperty(value = "启用状态 0:禁用 1:启用")
    private Integer status;

    @ApiModelProperty(value = "排序方向")
    @Min(0)
    @Max(1)
    @NotNull
    private Integer sort;
}
