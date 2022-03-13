package com.alex.member.dto;

import com.alex.common.base.AbstractBaseQuery;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * @author _Alexzinv_
 * @date 2022/3/13
 * @description
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class UserTransferQuery extends AbstractBaseQuery implements Serializable {
    private static final long serialVersionUID = 1L;
}
