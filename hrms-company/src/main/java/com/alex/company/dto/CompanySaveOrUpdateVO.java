package com.alex.company.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

/**
 * @Author _Alexzinv_
 * @Date 2021/11/23
 * @Description 保存或修改数据对象
 */
@Data
@ApiModel(value="Company修改对象")
public class CompanySaveOrUpdateVO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    @ApiModelProperty(value = "公司名称")
    @NotBlank
    private String name;

    @ApiModelProperty(value = "法人代表")
    private String legalRepresentative;

    @ApiModelProperty(value = "公司电话")
    @Pattern(regexp = "0?(13|14|15|18|17)[0-9]{9}")
    private String companyPhone;

    @ApiModelProperty(value = "邮箱")
    @Pattern(regexp = "\\w[-\\w.+]*@([A-Za-z0-9][-A-Za-z0-9]+\\.)+[A-Za-z]{2,14}")
    private String mailbox;

    @ApiModelProperty(value = "公司地址")
    private String companyAddress;

    @ApiModelProperty(value = "公司规模")
    private String companySize;

    @ApiModelProperty(value = "企业登录账号ID")
    private Long managerId;

    @ApiModelProperty(value = "营业执照-图片地址")
    private String businessLicenseUrl;

    @ApiModelProperty(value = "所属行业")
    private String industry;

    @ApiModelProperty(value = "备注")
    private String remarks;
}
