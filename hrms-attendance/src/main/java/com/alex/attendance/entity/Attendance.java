package com.alex.attendance.entity;

import com.alex.common.base.BaseEntity;
import com.alex.common.valid.ListValue;
import com.alex.common.valid.group.AddGroup;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;
import java.util.Date;

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
public class Attendance extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "ID")
    @TableId(value = "id", type = IdType.INPUT)
    private Long id;

    @ApiModelProperty(value = "用户id")
    @NotNull(groups = {AddGroup.class})
    private Long userId;

    @ApiModelProperty(value = "企业ID")
    @NotNull(groups = {AddGroup.class})
    private Long companyId;

    @ApiModelProperty(value = "部门ID")
    private Long departmentId;

    @ApiModelProperty(value = "考勤状态 1正常2旷工3迟到4早退5外出6年假7事假8病假9产假10调休11补签")
    @ListValue(values = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11})
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
}
