package com.alex.system.dto;

import com.alex.common.base.AbstractBaseQuery;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

/**
 * @author _Alexzinv_
 * @date 2022/1/20
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class LogQuery extends AbstractBaseQuery implements Serializable {
    private static final long serialVersionUID = 2L;

    /** 用户名 */
    private String username;

    /** 开始时间 */
    private Date start;

    /** 结束时间 */
    private Date end;
}
