package com.alex.common.bean.member;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @author _Alexzinv_
 * @date 2021/12/21
 * @description 企业下的部门岗位更新数据传输对象
 */
@Data
public class UserCompanyDepartmentPositionTo implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "部门ID")
    private Long departmentId;

    @ApiModelProperty(value = "部门名称")
    private String departmentName;

    @ApiModelProperty(value = "岗位ID")
    private Long positionId;

    @ApiModelProperty(value = "岗位名称")
    private String position;
}
