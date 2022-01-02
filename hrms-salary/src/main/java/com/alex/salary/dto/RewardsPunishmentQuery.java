package com.alex.salary.dto;

import lombok.Data;

/**
 * @author _Alexzinv_
 * @date 2022/1/2
 * @description 奖惩查询数据对象
 */
@Data
public class RewardsPunishmentQuery {

    private Integer rpType;

    private Data startTime;

    private Data endTime;
}
