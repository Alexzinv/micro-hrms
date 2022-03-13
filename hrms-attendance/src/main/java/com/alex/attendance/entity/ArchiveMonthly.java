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
public class ArchiveMonthly implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "ID")
    @TableId(value = "id", type = IdType.INPUT)
    private Long id;

    @ApiModelProperty(value = "企业ID")
    private Long companyId;

    @ApiModelProperty(value = "部门ID")
    private Long departmentId;

    @ApiModelProperty(value = "归档年份")
    private String archiveYear;

    @ApiModelProperty(value = "归档月份")
    private String archiveMonth;

    @ApiModelProperty(value = "总人数")
    private Integer totalMemberNum;

    @ApiModelProperty(value = "全勤人数")
    private Integer fullAtteMemberNum;

    @ApiModelProperty(value = "是否归档(0已经归档1没有归档)")
    private Integer isArchived;

    private String notes;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;


}
