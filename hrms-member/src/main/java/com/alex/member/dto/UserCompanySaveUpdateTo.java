package com.alex.member.dto;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

/**
 * @author _Alexzinv_
 * @date 2021/12/3
 * @description 更新数据
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="数据更新对象")
public class UserCompanySaveUpdateTo implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "ID")
    private Long id;

    @ApiModelProperty(value = "账号")
    private String username;

    @ApiModelProperty(value = "昵称")
    private String nickname;

    @ApiModelProperty(value = "企业ID")
    private Long companyId;

    @ApiModelProperty(value = "部门ID")
    private Long departmentId;

    @ApiModelProperty(value = "岗位名称")
    private String position;

    @ApiModelProperty(value = "离职时间")
    private Date resignTime;

    @ApiModelProperty(value = "聘用形式")
    private Integer employForm;

    @ApiModelProperty(value = "工作城市")
    private String workingCity;

    @ApiModelProperty(value = "转正时间")
    private Date correctionTime;

    @ApiModelProperty(value = "在职状态 1.在职  2.离职")
    private Integer jobStatus;

    @ApiModelProperty(value = "员工照片")
    private String staffPhoto;
}