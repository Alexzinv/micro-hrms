package com.alex.company.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @Author _Alexzinv_
 * @Date 2021/11/6
 * @Description
 */
@Data
public class CompanyQuery {

    /**
     * 公司名或法人名字
     */
    private String name;

    /**
     * 激活状态
     */
    private Integer state;

    /**
     * 审核状态 0:未审核 1:审核通过 2:审核未通过
     */
    private Integer auditState;

    /**
     * 到期时间
     */
    private Date expirationDateBegin;

    /**
     * 到期时间
     */
    private Date expirationDateEnd;
}
