package com.alex.common.bean.member;

import lombok.Data;

import java.io.Serializable;

/**
 * @author _Alexzinv_
 * @date 2021/12/2
 * @description 用户个人信息数据对象
 */
@Data
public class UserPersonalInfoTo implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private String name;
}
