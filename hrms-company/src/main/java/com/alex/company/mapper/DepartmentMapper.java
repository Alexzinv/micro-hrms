package com.alex.company.mapper;

import com.alex.company.entity.Department;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 *
 * @author _Alexzinv_
 * @since 2021-11-08
 */
public interface DepartmentMapper extends BaseMapper<Department> {

    /**
     * 根据公司id获取日期最新的部门编码
     * @param companyId 公司id
     * @return 编码
     */
    String getLatestCode(@Param("companyId") Long companyId);
}
