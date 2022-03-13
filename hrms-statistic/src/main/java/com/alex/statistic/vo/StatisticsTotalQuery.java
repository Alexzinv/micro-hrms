package com.alex.statistic.vo;

import com.alex.common.base.AbstractBaseQuery;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * @author _Alexzinv_
 * @date 2022/3/13
 * @description
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class StatisticsTotalQuery extends AbstractBaseQuery {

    private Date createTime;
}
