package com.alex.member.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @author _Alexzinv_
 * @date 2021/12/28
 * @description
 */
@Data
public class UserCompanyVO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private String nickname;
}
