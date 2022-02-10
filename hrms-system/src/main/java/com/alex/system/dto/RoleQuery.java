package com.alex.system.dto;

import com.alex.common.base.AbstractBaseQuery;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * @author _Alexzinv_
 * @date 2022/1/21
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class RoleQuery extends AbstractBaseQuery implements Serializable {

    private static final long serialVersionUID = 3L;

    /**
     * 角色名称或编码
     */
    private String roleNameOrCode;
}
