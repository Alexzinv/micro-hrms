package com.alex.member.dto;

import com.alex.common.base.AbstractBaseQuery;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * @author _Alexzinv_
 * @date 2022/3/3
 * @description 员工培训查询条件对象
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class UserTrainQuery extends AbstractBaseQuery implements Serializable {
    private static final long serialVersionUID = 1L;



}
