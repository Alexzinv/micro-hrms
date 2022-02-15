package com.alex.salary.entity;

import java.math.BigDecimal;

import com.alex.common.valid.group.AddGroup;
import com.baomidou.mybatisplus.annotation.*;

import java.util.Date;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;

/**
 * 员工薪酬个人表
 *
 * @author _Alexzinv_
 * @since 2022-01-01
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("sa_salary_personal")
@ApiModel(value="SalaryPersonal对象", description="员工薪酬个人表")
public class SalaryPersonal implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "id")
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    @ApiModelProperty(value = "工号")
    private Long salaryPersonalId;

    @ApiModelProperty(value = "工资公共部分id")
    @NotNull(groups = AddGroup.class)
    private Long salaryCommonId;

    @ApiModelProperty(value = "基本工资")
    private BigDecimal basicSalary;

    @ApiModelProperty(value = "奖金")
    private BigDecimal bonus;

    @ApiModelProperty(value = "应发工资")
    private BigDecimal totalSalary;

    @ApiModelProperty(value = "创建时间")
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    @ApiModelProperty(value = "更新时间")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;


}
