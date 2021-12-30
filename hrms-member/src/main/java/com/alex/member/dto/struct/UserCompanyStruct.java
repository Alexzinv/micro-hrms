package com.alex.member.dto.struct;

import com.alex.member.dto.UserCompanyVO;
import com.alex.member.entity.UserCompany;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @author _Alexzinv_
 * @date 2021/12/28
 * @description
 */
@Mapper
public interface UserCompanyStruct {

    UserCompanyStruct INSTANCE = Mappers.getMapper(UserCompanyStruct.class);

    /**
     * 实体类转VO
     * @param userCompany 实体类
     * @return vo
     */
    UserCompanyVO toVO(UserCompany userCompany);

    /**
     * 实体类列表转vo列表
     * @param userCompanies 实体类列表
     * @return vo列表
     */
    List<UserCompanyVO> toVOList(List<UserCompany> userCompanies);
}
