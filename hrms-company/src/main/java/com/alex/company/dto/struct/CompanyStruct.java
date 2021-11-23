package com.alex.company.dto.struct;

import com.alex.company.dto.CompanyCheckVO;
import com.alex.company.dto.CompanySaveOrUpdateVO;
import com.alex.company.dto.CompanyStateVO;
import com.alex.company.entity.Company;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @Author _Alexzinv_
 * @Date 2021/11/23
 * @Description
 */
@Mapper
public interface CompanyStruct {

    CompanyStruct INSTANCE = Mappers.getMapper(CompanyStruct.class);

    /**
     * vo转实体类,新增修改
     * @param vo 数据对象
     * @return 实体类
     */
    Company saveOrUpdateVoToEntity(CompanySaveOrUpdateVO vo);

    /**
     * vo转实体类,核查状态
     * @param vo 数据对象
     * @return 实体类
     */
    Company checkVoToEntity(CompanyCheckVO vo);

    /**
     * vo转实体类,核查状态
     * @param vo 数据对象
     * @return 实体类
     */
    Company stateVoToEntity(CompanyStateVO vo);
}
