package com.alex.salary.dto;

import com.alex.common.base.AbstractBaseQuery;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * @author _Alexzinv_
 * @date 2022/1/2
 * @description 奖惩查询数据对象
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class RewardsPunishmentQuery extends AbstractBaseQuery implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer rpType;

    private Data startTime;

    private Data endTime;
}
