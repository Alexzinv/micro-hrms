package com.alex.member.entity;

import com.alex.common.valid.group.AddGroup;
import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 员工培训表
 * </p>
 *
 * @author _Alexzinv_
 * @since 2022-02-27
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("mem_user_train")
@ApiModel(value="UserTrain对象", description="员工培训表")
public class UserTrain implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "ID")
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    @ApiModelProperty(value = "企业ID")
    @NotNull(groups = {AddGroup.class})
    private Long companyId;

    @ApiModelProperty(value = "培训日期")
    @NotNull(groups = {AddGroup.class})
    private Date trainDate;

    @ApiModelProperty(value = "培训内容")
    @NotBlank(groups = {AddGroup.class})
    private String trainContent;

    @ApiModelProperty(value = "备注")
    private String notes;

    @ApiModelProperty(value = "创建时间")
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;


}
