package com.alex.company.dto;

import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.io.Serializable;

/**
 * @Author _Alexzinv_
 * @Date 2021/11/23
 * @Description 审核状态改变数据对象
 */
@Data
public class CompanyStateVO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    @Min(0)
    @Max(1)
    private Integer state;
}
