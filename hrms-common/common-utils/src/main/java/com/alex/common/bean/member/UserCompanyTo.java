package com.alex.common.bean.member;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 * user公司扩展表TO
 * </p>
 *
 * @author _Alexzinv_
 * @since 2021-12-02
 */
@Data
public class UserCompanyTo implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "ID")
    private Long id;

    @ApiModelProperty(value = "账号")
    private String username;

    @ApiModelProperty(value = "昵称")
    private String nickname;

    @ApiModelProperty(value = "企业ID")
    private Long companyId;
}
