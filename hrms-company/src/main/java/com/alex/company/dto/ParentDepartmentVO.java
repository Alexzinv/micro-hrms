package com.alex.company.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @author _Alexzinv_
 * @date 2021/12/28
 * @description 返回父级部门数据给选择器
 */
@Data
public class ParentDepartmentVO implements Serializable{

    private static final long serialVersionUID = 1L;

    private Long id;

    private String name;
}
