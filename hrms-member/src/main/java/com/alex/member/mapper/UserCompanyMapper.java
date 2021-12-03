package com.alex.member.mapper;

import com.alex.member.entity.UserCompany;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 * user公司扩展表 Mapper 接口
 * </p>
 *
 * @author _Alexzinv_
 * @since 2021-12-02
 */
public interface UserCompanyMapper extends BaseMapper<UserCompany> {

    /**
     *
     * 获取最大工号
     * @return 工号
     */
    Long getMaxWorkNumber();
}
