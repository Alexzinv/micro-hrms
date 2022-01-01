package com.alex.salary.entity;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 奖惩
 *
 * @author _Alexzinv_
 * @since 2022-01-01
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("sa_rewards_punishment")
@ApiModel(value="RewardsPunishment对象", description="奖惩")
public class RewardsPunishment implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "id")
    @TableId(value = "id", type = IdType.INPUT)
    private Long id;

    @ApiModelProperty(value = "工号")
    private Long salaryPersonalId;

    @ApiModelProperty(value = "奖罚原因")
    private String rpReason;

    @ApiModelProperty(value = "奖罚分")
    private Integer rpPoint;

    @ApiModelProperty(value = "奖罚金额")
    private BigDecimal rpMoney;

    @ApiModelProperty(value = "奖罚类别，0：奖，1：罚")
    private Integer rpType;

    @ApiModelProperty(value = "备注")
    private String notes;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @ApiModelProperty(value = "更新时间")
    private Date updateTime;


}
