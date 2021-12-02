package com.alex.member.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.util.Date;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * user公司扩展表
 * </p>
 *
 * @author _Alexzinv_
 * @since 2021-12-02
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("mem_user_company")
@ApiModel(value="UserCompany对象", description="user公司扩展表")
public class UserCompany implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "ID")
    @TableId(value = "id", type = IdType.INPUT)
    private Long id;

    @ApiModelProperty(value = "账号")
    private String username;

    @ApiModelProperty(value = "工号")
    private String workNumber;

    @ApiModelProperty(value = "昵称")
    private String nickname;

    @ApiModelProperty(value = "启用状态 0是禁用，1是启用")
    private Integer enableState;

    @ApiModelProperty(value = "企业ID")
    private Long companyId;

    @ApiModelProperty(value = "部门ID")
    private Long departmentId;

    @ApiModelProperty(value = "岗位名称")
    private String position;

    @ApiModelProperty(value = "入职时间")
    private Date joinTime;

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

    @ApiModelProperty(value = "创建时间")
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    @ApiModelProperty(value = "更新时间")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;


}
