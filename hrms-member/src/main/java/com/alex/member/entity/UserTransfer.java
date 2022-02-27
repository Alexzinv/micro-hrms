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
 * 员工调职表
 * </p>
 *
 * @author _Alexzinv_
 * @since 2022-02-27
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("mem_user_transfer")
@ApiModel(value="UserTransfer对象", description="员工调职表")
public class UserTransfer implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "ID")
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    @ApiModelProperty(value = "企业ID")
    private Long companyId;

    @ApiModelProperty(value = "调动后部门")
    private String afterDepartment;

    @ApiModelProperty(value = "调动后职位")
    private String afterPosition;

    @ApiModelProperty(value = "调动日期")
    private Date transferDate;

    @ApiModelProperty(value = "调动原因")
    private String reason;

    @ApiModelProperty(value = "备注")
    private String notes;

    @ApiModelProperty(value = "创建时间")
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;


}
