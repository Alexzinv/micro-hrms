package com.alex.company.dto.struct;

import com.alex.company.dto.PositionStatusVO;
import com.alex.company.entity.Position;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @author _Alexzinv_
 * @date 2021/12/15
 * @description position转换类
 */
@Mapper
public interface PositionStruct {

    PositionStruct INSTANCE = Mappers.getMapper(PositionStruct.class);

    /**
     * 状态vo转实体
     * @param statusVO vo
     * @return 岗位实体
     */
    Position statusVoToEntity(PositionStatusVO statusVO);
}
