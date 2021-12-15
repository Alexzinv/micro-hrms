package com.alex.company.dto;

import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @Author _Alexzinv_
 * @Date 2021/11/23
 * @Description 审核状态改变数据对象
 */
@Data
public class CompanyCheckVO implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotNull
    private Long id;

    @Min(0)
    @Max(2)
    @NotNull
    private Integer auditState;
}
