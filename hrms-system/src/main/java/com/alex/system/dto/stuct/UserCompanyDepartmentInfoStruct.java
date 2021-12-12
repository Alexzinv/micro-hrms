package com.alex.system.dto.stuct;

import com.alex.system.dto.UserCompanyDepartmentInfoVO;
import com.alex.system.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

/**
 * @Author _Alexzinv_
 * @Date 2021/11/29
 * @Description 用户相关信息转换类
 */
@Mapper
public interface UserCompanyDepartmentInfoStruct {

    UserCompanyDepartmentInfoStruct INSTANCE = Mappers.getMapper(UserCompanyDepartmentInfoStruct.class);

    /**
     * 封装用户信息，公司，部门到视图对象
     * @param user 用户
     * @param company 公司
     * @param department 部门
     * @return vo
     */
    // @Mappings({
    //         @Mapping(source = "company.name", target = "companyName"),
    //         @Mapping(source = "department.name", target = "departmentName")
    // })
    UserCompanyDepartmentInfoVO toVO(User user, Object company, Object department);
}
