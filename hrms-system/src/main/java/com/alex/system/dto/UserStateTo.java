package com.alex.system.dto;

import com.alex.common.valid.ListValue;
import com.alex.common.valid.group.UpdateStatusGroup;
import lombok.Data;

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
    @NotNull(groups = {UpdateStatusGroup.class})
    private Long id;

    /**
     * 启用状态 0是禁用，1是启用
     */
    @ListValue(values = {0, 1}, groups = {UpdateStatusGroup.class})
    @NotNull(groups = {UpdateStatusGroup.class})
    private Integer enableState;
}
