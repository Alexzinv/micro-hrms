package com.alex.company.entity;

import com.alex.common.valid.group.AddGroup;
import com.alex.common.valid.group.UpdateGroup;
import com.alex.common.valid.group.UpdateStatusGroup;
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
 *
 * @author _Alexzinv_
 * @since 2021-11-08
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("co_department")
@ApiModel(value="Department对象", description="部门")
public class Department implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "ID")
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    @Null(groups = {AddGroup.class})
    @NotNull(groups = {UpdateGroup.class, UpdateStatusGroup.class})
    private Long id;

    @ApiModelProperty(value = "企业ID")
    @NotNull(groups = {AddGroup.class})
    private Long companyId;

    @ApiModelProperty(value = "父级部门ID")
    private Long pid;

    @ApiModelProperty(value = "部门名称")
    @NotNull(groups = {AddGroup.class})
    private String name;

    @ApiModelProperty(value = "部门编码")
    @Null(groups = {AddGroup.class, UpdateGroup.class})
    private String code;

    @ApiModelProperty(value = "部门负责人")
    private String manager;

    @ApiModelProperty(value = "介绍")
    private String introduce;

    @ApiModelProperty(value = "负责人ID")
    private Long managerId;

    @ApiModelProperty(value = "创建时间")
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    @ApiModelProperty(value = "更新时间")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;


}
