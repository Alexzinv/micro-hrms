package com.alex.company.entity;

import com.alex.common.valid.ListValue;
import com.alex.common.valid.group.AddGroup;
import com.alex.common.valid.group.UpdateGroup;
import com.alex.common.valid.group.UpdateStatusGroup;
import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 *
 * @author _Alexzinv_
 * @since 2021-11-06
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("co_company")
@ApiModel(value="Company对象")
public class Company implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键ID")
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    @Null(groups = {AddGroup.class})
    @NotNull(groups = {UpdateGroup.class, UpdateStatusGroup.class})
    private Long id;

    @ApiModelProperty(value = "公司名称")
    @NotBlank(groups = {AddGroup.class})
    private String name;

    @ApiModelProperty(value = "法人代表")
    private String legalRepresentative;

    @ApiModelProperty(value = "公司电话")
    @Pattern(regexp = "0?(13|14|15|18|17)[0-9]{9}", groups = {AddGroup.class, UpdateGroup.class})
    private String companyPhone;

    @ApiModelProperty(value = "邮箱")
    @Pattern(regexp = "\\w[-\\w.+]*@([A-Za-z0-9][-A-Za-z0-9]+\\.)+[A-Za-z]{2,14}", groups = {AddGroup.class, UpdateGroup.class})
    private String mailbox;

    @ApiModelProperty(value = "公司地址")
    private String companyAddress;

    @ApiModelProperty(value = "公司规模")
    private String companySize;

    @ApiModelProperty(value = "企业登录账号ID")
    private Long managerId;

    @ApiModelProperty(value = "当前版本")
    @Version
    @Null(groups = {AddGroup.class})
    private String version;

    @ApiModelProperty(value = "续期时间")
    private Date renewalDate;

    @ApiModelProperty(value = "到期时间")
    private Date expirationDate;

    @ApiModelProperty(value = "营业执照-图片地址")
    private String businessLicenseUrl;

    @ApiModelProperty(value = "所属行业")
    private String industry;

    @ApiModelProperty(value = "备注")
    private String remarks;

    @ApiModelProperty(value = "审核状态 0:未审核 1:审核通过 2:审核未通过")
    @ListValue(values = {0, 1, 2}, message = "请按规范填写", groups = {UpdateStatusGroup.class})
    @Null(groups = {AddGroup.class})
    private Integer auditState;

    @ApiModelProperty(value = "状态 0:未激活 1:已激活")
    @Null(groups = {AddGroup.class})
    @ListValue(values = {0, 1}, message = "请按规范填写", groups = {UpdateStatusGroup.class})
    private Integer state;

    @ApiModelProperty(value = "当前余额")
    @Null(groups = {AddGroup.class})
    private BigDecimal balance;

    @ApiModelProperty(value = "创建时间")
    @TableField(fill = FieldFill.INSERT)
    @Null(groups = {AddGroup.class})
    private Date createTime;

    @ApiModelProperty(value = "更新时间")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    @Null(groups = {AddGroup.class})
    private Date updateTime;

}
