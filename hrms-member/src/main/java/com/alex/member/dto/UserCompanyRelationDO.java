package com.alex.member.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @author _Alexzinv_
 * @date 2021/12/22
 * @description 用户关联公司
 */
@Data
public class UserCompanyRelationDO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;


    private Long companyId;
}
