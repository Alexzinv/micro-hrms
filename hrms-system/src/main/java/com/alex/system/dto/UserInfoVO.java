package com.alex.system.dto;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.Date;

/**
 * @Author _Alexzinv_
 * @Date 2021/11/25
 * @Description
 */
@Data
public class UserInfoVO implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    @TableId
    private Long id;
    /**
     * 账号
     */
    @NotBlank
    private String username;
    /**
     * 密码
     */
    @NotEmpty
    @Length(min = 6)
    private String password;
    /**
     * 昵称
     */
    @NotBlank
    private String nickname;
    /**
     * 用户头像
     */
    private String avatar;
    /**
     * 用户签名
     */
    private String token;
    /**
     * 企业ID
     */
    private Long companyId;
    /**
     * 部门ID
     */
    private Long departmentId;
    /**
     * 入职时间
     */
    private Date joinTime;
    /**
     * 离职时间
     */
    private Date resignTime;
    /**
     * 聘用形式
     */
    private Integer employForm;
    /**
     * 工号
     */
    private String workNumber;
    /**
     * 管理形式
     */
    private String manageForm;
    /**
     * 工作城市
     */
    private String workingCity;
    /**
     * 转正时间
     */
    private Date correctionTime;
    /**
     * 在职状态 1.在职  2.离职
     */
    private Integer jobStatus;
    /**
     * 员工照片
     */
    private String staffPhoto;
    /**
     * 用户级别
     */
    private Integer level;
}