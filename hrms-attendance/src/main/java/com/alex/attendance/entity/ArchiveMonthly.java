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

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * <p>
 * 考勤归档
 * </p>
 *
 * @author _Alexzinv_
 * @since 2022-03-13
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("atte_archive_monthly")
@ApiModel(value="ArchiveMonthly对象", description="考勤归档")
public class ArchiveMonthly extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "ID")
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    @ApiModelProperty(value = "企业ID")
    @NotNull(groups = {AddGroup.class})
    private Long companyId;

    @ApiModelProperty(value = "部门ID")
    @NotNull(groups = {AddGroup.class})
    private Long departmentId;

    @ApiModelProperty(value = "归档年份")
    @NotBlank(groups = {AddGroup.class})
    private String archiveYear;

    @ApiModelProperty(value = "归档月份")
    @NotBlank(groups = {AddGroup.class})
    private String archiveMonth;

    @ApiModelProperty(value = "总人数")
    private Integer totalMemberNum;

    @ApiModelProperty(value = "全勤人数")
    private Integer fullAtteMemberNum;

    @ApiModelProperty(value = "是否归档(0已经归档1没有归档)")
    @ListValue(values = {0, 1}, groups = {AddGroup.class})
    private Integer isArchived;

    private String notes;

}
