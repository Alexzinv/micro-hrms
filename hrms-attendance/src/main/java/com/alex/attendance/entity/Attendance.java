package com.alex.attendance.entity;

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
 * <p>
 * 出勤
 * </p>
 *
 * @author _Alexzinv_
 * @since 2022-03-13
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("atte_attendance")
@ApiModel(value="Attendance对象", description="出勤")
public class Attendance implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "ID")
    @TableId(value = "id", type = IdType.INPUT)
    private Long id;

    @ApiModelProperty(value = "用户id")
    private Long userId;

    @ApiModelProperty(value = "企业ID")
    private Long companyId;

    @ApiModelProperty(value = "部门ID")
    private Long departmentId;

    @ApiModelProperty(value = "考勤状态 1正常2旷工3迟到4早退5外出6年假7事假8病假9产假10调休11补签")
    private Integer atteStatus;

    @ApiModelProperty(value = "上班考勤时间")
    private Date workInTime;

    @ApiModelProperty(value = "考勤地点")
    private String workInPlace;

    @ApiModelProperty(value = "下班考勤时间")
    private Date workOutTime;

    @ApiModelProperty(value = "下班考勤地点")
    private String workOutPlace;

    @ApiModelProperty(value = "备注")
    private String notes;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @ApiModelProperty(value = "更新时间")
    private Date updateTime;


}
