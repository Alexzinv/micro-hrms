package com.alex.member.dto.struct;

import com.alex.member.dto.UserCompanyRelationDO;
import com.alex.member.entity.UserCompany;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @author _Alexzinv_
 * @date 2021/12/22
 * @description 实体关联类互转
 */
@Mapper
public interface UserCompanyRelationStruct {

    UserCompanyRelationStruct INSTANCE = Mappers.getMapper(UserCompanyRelationStruct.class);

    /**
     * 公司用户实体类转关联类
     * @param userCompany 实体类
     * @return 关联数据对象
     */
    UserCompanyRelationDO toRelationDO(UserCompany userCompany);
}
