package com.alex.company.dto.struct;

import com.alex.company.dto.DepartmentVO;
import com.alex.company.dto.ParentDepartmentVO;
import com.alex.company.entity.Company;
import com.alex.company.entity.Department;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @Author _Alexzinv_
 * @Date 2021/11/8
 * @Description
 */
@Mapper
public interface DepartmentStruct {

    DepartmentStruct INSTANCE = Mappers.getMapper(DepartmentStruct.class);

    /**
     * 封装公司信息和全部部门数据
     * @param company 公司信息
     * @param departmentList 部门信息列表
     * @return vo
     */
    @Mappings({
            @Mapping(source = "company.name", target = "companyName"),
            @Mapping(source = "company.managerId", target = "companyManager"),
    })
    DepartmentVO entity2VO(Company company, List<Department> departmentList);

    /**
     * 实体类转vo
     * @param department 实体类
     * @return vo
     */
    ParentDepartmentVO toParentDepartmentVO(Department department);

    /**
     * 实体列表转vo列表
     * @param departmentList 实体类列表
     * @return vo列表
     */
    List<ParentDepartmentVO> toParentDepartmentVOList(List<Department> departmentList);
}
