package com.alex.member.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @author _Alexzinv_
 * @date 2021/12/2
 * @description 查询条件对象
 */
@Data
public class UserCompanyQuery implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "企业ID")
    @NotNull
    private Long companyId;

    @ApiModelProperty(value = "姓名")
    private String nickname;

    @ApiModelProperty(value = "工号")
    private Long workNumber;

    @ApiModelProperty(value = "部门ID")
    private Long departmentId;

    @ApiModelProperty(value = "岗位名称")
    private String position;

    @ApiModelProperty(value = "工作城市")
    private String workingCity;

    @ApiModelProperty(value = "在职状态 1.在职  2.离职")
    private Integer jobStatus;
}
