package com.alex.company.entity;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;
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
    private Long id;

    @ApiModelProperty(value = "公司名称")
    @NotBlank
    private String name;

    @ApiModelProperty(value = "企业登录账号ID")
    private Long managerId;

    @ApiModelProperty(value = "当前版本")
    @Version
    private String version;

    @ApiModelProperty(value = "续期时间")
    private Date renewalDate;

    @ApiModelProperty(value = "到期时间")
    private Date expirationDate;

    @ApiModelProperty(value = "公司地区")
    private String companyArea;

    @ApiModelProperty(value = "公司地址")
    private String companyAddress;

    @ApiModelProperty(value = "营业执照-图片ID")
    private String businessLicenseId;

    @ApiModelProperty(value = "法人代表")
    private String legalRepresentative;

    @ApiModelProperty(value = "公司电话")
    @Pattern(regexp = "0?(13|14|15|18|17)[0-9]{9}")
    private String companyPhone;

    @ApiModelProperty(value = "邮箱")
    @Pattern(regexp = "\\w[-\\w.+]*@([A-Za-z0-9][-A-Za-z0-9]+\\.)+[A-Za-z]{2,14}")
    private String mailbox;

    @ApiModelProperty(value = "公司规模")
    private String companySize;

    @ApiModelProperty(value = "所属行业")
    private String industry;

    @ApiModelProperty(value = "备注")
    private String remarks;

    @ApiModelProperty(value = "审核状态 0:未审核 1:审核通过 2:审核未通过")
    private Integer auditState;

    @ApiModelProperty(value = "状态 0:未激活 1:已激活")
    private Integer state;

    @ApiModelProperty(value = "当前余额")
    private BigDecimal balance;

    @ApiModelProperty(value = "创建时间")
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    @ApiModelProperty(value = "更新时间")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;


}
