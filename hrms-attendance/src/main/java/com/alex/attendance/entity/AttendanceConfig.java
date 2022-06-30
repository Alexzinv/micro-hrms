package com.alex.attendance.entity;

import com.alex.common.base.BaseEntity;
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
 * 考勤配置表
 * </p>
 *
 * @author _Alexzinv_
 * @since 2022-03-13
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("atte_attendance_config")
@ApiModel(value="AttendanceConfig对象", description="考勤配置表")
public class AttendanceConfig extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "ID")
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    @ApiModelProperty(value = "企业ID")
    @NotNull(groups = {AddGroup.class})
    private Long companyId;

    @ApiModelProperty(value = "部门ID")
    private Long departmentId;

    @ApiModelProperty(value = "上午打卡时间")
    private Date morningStartTime;

    @ApiModelProperty(value = "上午打卡时间")
    private Date morningEndTime;

    @ApiModelProperty(value = "下午打卡时间")
    private Date afternoonStartTime;

    @ApiModelProperty(value = "下午打卡时间")
    private Date afternoonEndTime;

    @ApiModelProperty(value = "备注")
    private String notes;
}
