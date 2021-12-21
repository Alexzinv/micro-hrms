package com.alex.member.mapper;

import com.alex.member.entity.UserCompany;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

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
     * 获取当前公司最大工号
     * @param CompanyId 公司id
     * @return 工号 */
    Long getMaxWorkNumber(@Param("companyId") Long CompanyId);

    /**
     * 更新部门名字
     * @param departmentId 部门id
     * @param departmentName 部门名字
     * @return 操作结果 */
    boolean updateDepartmentName(@Param("departmentId") Long departmentId,
                                 @Param("departmentName") String departmentName);

    /**
     * 更新岗位名字
     * @param positionId 岗位id
     * @param position 岗位名字
     * @return 操作结果 */
    boolean updatePositionName(@Param("positionId") Long positionId,
                               @Param("position") String position);
}
