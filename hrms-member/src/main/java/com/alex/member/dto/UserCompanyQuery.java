package com.alex.member.dto;

import com.alex.common.base.AbstractBaseQuery;
import com.alex.common.valid.ListValue;
import com.alex.common.valid.group.QueryGroup;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @author _Alexzinv_
 * @date 2021/12/2
 * @description 查询条件对象
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class UserCompanyQuery extends AbstractBaseQuery implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "企业ID")
    @NotNull(groups = {QueryGroup.class})
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
    @ListValue(values = {1, 2}, groups = {QueryGroup.class})
    private Integer jobStatus;
}
