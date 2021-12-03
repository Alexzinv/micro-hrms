package com.alex.system.dto;

import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * @author _Alexzinv_
 * @date 2021/12/3
 * @description 修改账号状态
 */
@Data
public class UserStateTo {

    /**
     * id
     */
    @NotNull
    private Long id;

    /**
     * 启用状态 0是禁用，1是启用
     */
    @Min(0)
    @Max(1)
    @NotNull
    private Integer enableState;
}
