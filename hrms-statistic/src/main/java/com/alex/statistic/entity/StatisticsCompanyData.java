package com.alex.statistic.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 月公司人数变动统计
 * </p>
 *
 * @author _Alexzinv_
 * @since 2022-03-13
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="StatisticsCompanyData对象", description="月公司人数变动统计")
public class StatisticsCompanyData implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "ID")
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    @ApiModelProperty(value = "公司总人数")
    private Integer companyMemberNum;

    @ApiModelProperty(value = "当月新加入人数")
    private Integer monthRecruitNum;

    @ApiModelProperty(value = "当月离职人数")
    private Integer monthResigningNum;

    @ApiModelProperty(value = "统计年月")
    private Date calculateDate;

    @ApiModelProperty(value = "创建时间")
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    @ApiModelProperty(value = "更新时间")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;


}
