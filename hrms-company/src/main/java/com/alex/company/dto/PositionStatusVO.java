package com.alex.company.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * @author _Alexzinv_
 * @date 2021/12/15
 * @description 岗位状态vo
 */
@Data
public class PositionStatusVO {

    @NotNull
    private Long id;

    @ApiModelProperty(value = "启用状态 0:禁用 1:启用")
    @Min(0)
    @Max(1)
    @NotNull
    private Integer status;
}
