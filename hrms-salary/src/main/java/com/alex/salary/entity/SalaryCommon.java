package com.alex.salary.entity;

import java.math.BigDecimal;

import com.alex.common.valid.group.AddGroup;
import com.alex.common.valid.group.UpdateGroup;
import com.baomidou.mybatisplus.annotation.*;

import java.util.Date;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

/**
 * 员工薪酬公共表
 *
 * @author _Alexzinv_
 * @since 2022-01-01
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("sa_salary_common")
@ApiModel(value="SalaryCommon对象", description="员工薪酬公共表")
public class SalaryCommon implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "ID")
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    @ApiModelProperty(value = "企业ID")
    @NotNull(groups = {AddGroup.class})
    @Null(groups = {UpdateGroup.class})
    private Long companyId;

    @ApiModelProperty(value = "午餐补助")
    private BigDecimal lunchSalary;

    @ApiModelProperty(value = "交通补助")
    private BigDecimal trafficSalary;

    @ApiModelProperty(value = "养老金基数")
    private BigDecimal pensionBase;

    @ApiModelProperty(value = "养老金比率")
    private BigDecimal pensionRate;

    @ApiModelProperty(value = "医疗基数")
    private BigDecimal medicalBase;

    @ApiModelProperty(value = "医疗保险比率")
    private BigDecimal medicalRate;

    @ApiModelProperty(value = "公积金基数")
    private BigDecimal accumulationFundBase;

    @ApiModelProperty(value = "公积金比率")
    private BigDecimal accumulationFundRate;

    @ApiModelProperty(value = "启用时间")
    private Date startDate;

    @ApiModelProperty(value = "创建时间")
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    @ApiModelProperty(value = "更新时间")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;


}
