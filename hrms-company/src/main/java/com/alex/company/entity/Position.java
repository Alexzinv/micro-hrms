package com.alex.company.entity;

import com.alex.common.valid.ListValue;
import com.alex.common.valid.group.AddGroup;
import com.alex.common.valid.group.UpdateGroup;
import com.alex.common.valid.group.UpdateStatusGroup;
import com.baomidou.mybatisplus.annotation.*;

import java.util.Date;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.*;

/**
 *
 * @author _Alexzinv_
 * @date 2021-12-15
 * @description 岗位
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("co_position")
@ApiModel(value="Position对象", description="岗位")
public class Position implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.ASSIGN_ID)
    @Null(groups = {AddGroup.class})
    @NotNull(groups = {UpdateGroup.class, UpdateStatusGroup.class})
    private Long id;

    @ApiModelProperty(value = "企业ID")
    @NotNull(groups = {AddGroup.class})
    @Null(groups = {UpdateGroup.class})
    private Long companyId;

    @ApiModelProperty(value = "岗位名称")
    @NotBlank(groups = {AddGroup.class})
    private String name;

    @ApiModelProperty(value = "启用状态 0:禁用 1:启用")
    @ListValue(values = {0, 1}, message = "请按规范填写", groups = {AddGroup.class,
            UpdateGroup.class, UpdateStatusGroup.class})
    private Integer status;

    @ApiModelProperty(value = "显示顺序")
    private Integer sort;

    @ApiModelProperty(value = "创建时间")
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    @ApiModelProperty(value = "更新时间")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

}
