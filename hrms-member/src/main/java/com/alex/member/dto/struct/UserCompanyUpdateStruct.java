package com.alex.member.dto.struct;

import com.alex.member.dto.UserCompanySaveUpdateTo;
import com.alex.member.entity.UserCompany;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @author _Alexzinv_
 * @date 2021/12/3
 * @description userCompany 实体DO互转
 */
@Mapper
public interface UserCompanyUpdateStruct {

    UserCompanyUpdateStruct INSTANCE = Mappers.getMapper(UserCompanyUpdateStruct.class);

    /**
     * to转entity
     * @param to 更新数据传输对象
     * @return entity
     */
    UserCompany toUserCompany(UserCompanySaveUpdateTo to);
}
