package com.alex.company.dto;

import com.alex.common.base.AbstractBaseQuery;
import com.alex.common.valid.ListValue;
import com.alex.common.valid.group.QueryGroup;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author _Alexzinv_
 * @Date 2021/11/6
 * @Description
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class CompanyQuery extends AbstractBaseQuery implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 公司名或法人名字
     */
    private String name;

    /**
     * 激活状态
     */
    @ListValue(values = {0, 1}, groups = {QueryGroup.class})
    private Integer state;

    /**
     * 审核状态 0:未审核 1:审核通过 2:审核未通过
     */
    @ListValue(values = {0, 1, 2}, groups = {QueryGroup.class})
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
