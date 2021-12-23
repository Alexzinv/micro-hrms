package com.alex.member.entity;

import com.alex.common.valid.ListValue;
import com.alex.common.valid.group.AddGroup;
import com.alex.common.valid.group.UpdateGroup;
import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.io.Serializable;
import java.util.Date;

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
    @NotNull(groups = {UpdateGroup.class})
    private Long id;

    @ApiModelProperty(value = "账号")
    @NotNull(groups = {AddGroup.class})
    private String username;

    @ApiModelProperty(value = "工号")
    @Null(groups = {AddGroup.class, UpdateGroup.class})
    private Long workNumber;

    @ApiModelProperty(value = "昵称")
    private String nickname;

    @ApiModelProperty(value = "企业ID")
    private Long companyId;

    @ApiModelProperty(value = "部门ID")
    private Long departmentId;

    @ApiModelProperty(value = "部门名称")
    private String departmentName;

    @ApiModelProperty(value = "岗位ID")
    private Long positionId;

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
    @ListValue(values = {1, 2}, groups = {UpdateGroup.class})
    @Null(groups = {AddGroup.class})
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
