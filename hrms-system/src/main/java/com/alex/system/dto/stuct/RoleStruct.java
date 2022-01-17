package com.alex.system.dto.stuct;

import com.alex.system.dto.RoleVO;
import com.alex.system.entity.Role;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @author _Alexzinv_
 * @date 2022/1/17
 * @description
 */
@Mapper
public interface RoleStruct {

    RoleStruct INSTANCE = Mappers.getMapper(RoleStruct.class);

    /**
     * 角色实体类转vo
     * @param role 实体类
     * @return vo
     */
    RoleVO toRoleVO(Role role);

    /**
     * 角色实体类列表转vo列表
     * @param roles 实体类列表
     * @return vo列表
     */
    List<RoleVO> toRoleVOList(List<Role> roles);
}
