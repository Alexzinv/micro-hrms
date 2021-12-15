package com.alex.system.dto;

import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @author _Alexzinv_
 * @date 2021/12/12
 * @description 权限状态vo
 */
@Data
public class PermissionStatusVo implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * id
     */
    @NotNull
    private Long id;
    /**
     * 状态(0:禁止,1:正常)
     */
    @NotNull
    @Min(0)
    @Max(1)
    private Integer status;
}
