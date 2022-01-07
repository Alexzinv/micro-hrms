package com.alex.member.service;

import com.alex.common.base.BaseService;
import com.alex.common.bean.member.UserCompanyDepartmentPositionTo;
import com.alex.member.dto.UserCompanyQuery;
import com.alex.member.dto.UserCompanyVO;
import com.alex.member.entity.UserCompany;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * user公司扩展表 服务类
 * </p>
 *
 * @author _Alexzinv_
 * @since 2021-12-02
 */
public interface UserCompanyService extends BaseService<UserCompany> {

    /**
     * 更新公司员工信息
     * @param userCompany 公司员工
     * @return 操作结果
     */
    boolean update(UserCompany userCompany);

    /**
     * 更新部门岗位信息
     * @param to 数据对象
     */
    void updateUserCompanyDepartmentPosition(UserCompanyDepartmentPositionTo to);

    /**
     * 列出公司人员
     * @param companyId 公司id
     * @return 人员列表
     */
    List<UserCompanyVO> listMembers(Long companyId);
}
