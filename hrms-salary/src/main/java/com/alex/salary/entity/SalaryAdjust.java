package com.alex.salary.entity;

import com.alex.common.valid.group.AddGroup;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 薪酬调整
 *
 * @author _Alexzinv_
 * @since 2022-01-01
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("sa_salary_adjust")
@ApiModel(value="SalaryAdjust对象", description="薪酬调整")
public class SalaryAdjust implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "id")
    @TableId(value = "id", type = IdType.INPUT)
    private Long id;

    @ApiModelProperty(value = "工号")
    @NotNull(groups = AddGroup.class)
    private Long salaryPersonalId;

    @ApiModelProperty(value = "调前薪资")
    private BigDecimal beforeSalary;

    @ApiModelProperty(value = "调后薪资")
    private BigDecimal afterSalary;

    @ApiModelProperty(value = "调薪原因")
    private String reason;

    @ApiModelProperty(value = "备注")
    private String notes;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @ApiModelProperty(value = "更新时间")
    private Date updateTime;


}
