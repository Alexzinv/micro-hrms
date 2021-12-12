package com.alex.system.dto.stuct;

import com.alex.system.dto.PermissionStatusVo;
import com.alex.system.entity.Permission;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @author _Alexzinv_
 * @date 2021/12/12
 * @description 权限实体VO类转换
 */
@Mapper
public interface PermissionStruct {
    PermissionStruct INSTANCE = Mappers.getMapper(PermissionStruct.class);

    /**
     * 权限vo转实体类
     * @param vo vo
     * @return 实体类
     */
    Permission toPermission(PermissionStatusVo vo);


}
