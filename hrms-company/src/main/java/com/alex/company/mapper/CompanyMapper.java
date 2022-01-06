package com.alex.company.mapper;

import com.alex.company.entity.Company;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 *
 * @author _Alexzinv_
 * @since 2021-11-06
 */
public interface CompanyMapper extends BaseMapper<Company> {

    /**
     * 判断公司名称是否存在
     * @param name 公司名称
     * @return bool
     */
    boolean isExist(@Param("name") String name);
}
