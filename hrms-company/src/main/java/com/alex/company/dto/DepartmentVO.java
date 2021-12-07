package com.alex.company.dto;

import com.alex.company.entity.Department;
import lombok.Data;

import java.util.List;

/**
 * @Author _Alexzinv_
 * @Date 2021/11/8
 * @Description 视图对象
 */
@Data
public class DepartmentVO {

    private String companyName;

    private String companyManager;

    private List<Department> departmentList;

}
