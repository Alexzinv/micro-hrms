package com.alex.salary.dto;

import com.alex.common.base.AbstractBaseQuery;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * @author _Alexzinv_
 * @date 2022/1/3
 * @description 个人工资部分查询条件
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class SalaryPersonalQuery extends AbstractBaseQuery implements Serializable {

    private static final long serialVersionUID = 1L;
}
