package com.alex.member.entity;

import com.alex.common.util.RegExpUtils;
import com.alex.common.valid.ListValue;
import com.alex.common.valid.group.AddGroup;
import com.alex.common.valid.group.UpdateGroup;
import com.baomidou.mybatisplus.annotation.*;

import java.util.Date;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.Pattern;

/**
 * <p>
 * 用户个人信息
 * </p>
 *
 * @author _Alexzinv_
 * @since 2021-12-02
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("mem_user_personal_info")
@ApiModel(value="UserPersonalInfo对象", description="用户个人信息")
public class UserPersonalInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "ID")
    @TableId(value = "id", type = IdType.INPUT)
    private Long id;

    @ApiModelProperty(value = "姓名")
    private String name;

    @ApiModelProperty(value = "性别  0：女，1：男")
    @ListValue(values = {0, 1}, groups = {AddGroup.class, UpdateGroup.class})
    private Integer gender;

    @ApiModelProperty(value = "出生日期")
    private Date birthday;

    @ApiModelProperty(value = "身份证号")
    @Pattern(regexp = RegExpUtils.ID_CARD_REGEXP)
    private String idCard;

    @ApiModelProperty(value = "婚姻状况 0：未婚 1：已婚，2：离异")
    @ListValue(values = {0, 1, 2}, groups = {AddGroup.class, UpdateGroup.class})
    private Integer wedlock;

    @ApiModelProperty(value = "民族")
    private String nation;

    @ApiModelProperty(value = "籍贯")
    private String nativePlace;

    @ApiModelProperty(value = "政治面貌 1: 少先队员 2：共青团员 3：党员 4:其他")
    @ListValue(values = {1, 2, 3, 4}, groups = {AddGroup.class, UpdateGroup.class})
    private Integer politicsStatus;

    @ApiModelProperty(value = "邮箱")
    @Pattern(regexp = RegExpUtils.EMAIL_REGEXP)
    private String email;

    @ApiModelProperty(value = "电话")
    @Pattern(regexp = RegExpUtils.MOBILE_PHONE_REGEXP)
    private String phone;

    @ApiModelProperty(value = "联系地址")
    private String address;

    @ApiModelProperty(value = "学历 1：初中及以下 2：高中 3：大专 4：本科 5：硕士 6：博士")
    @ListValue(values = {1, 2, 3, 4, 5, 6, 7}, groups = {AddGroup.class, UpdateGroup.class})
    private Integer education;

    @ApiModelProperty(value = "毕业院校")
    private String school;

    @ApiModelProperty(value = "创建时间")
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    @ApiModelProperty(value = "更新时间")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;


}
