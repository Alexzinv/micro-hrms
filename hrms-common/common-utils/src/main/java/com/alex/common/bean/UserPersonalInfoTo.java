package com.alex.common.bean;

import io.swagger.annotations.ApiModelProperty;
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

    @ApiModelProperty(value = "ID")
    private Long id;
}
