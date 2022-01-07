package com.alex.company.service.impl;

import com.alex.common.base.BaseQuery;
import com.alex.common.bean.member.UserCompanyDepartmentPositionTo;
import com.alex.common.bean.member.UserCompanyTo;
import com.alex.common.consant.CompanyConstant;
import com.alex.common.consant.PositionConstant;
import com.alex.common.consant.ResultCodeEnum;
import com.alex.common.exception.HRMSException;
import com.alex.company.client.MemberUserCompanyClient;
import com.alex.company.dto.PositionQuery;
import com.alex.company.entity.Position;
import com.alex.company.mapper.PositionMapper;
import com.alex.company.service.PositionService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.seata.spring.annotation.GlobalTransactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * <p>
 * 岗位 服务实现类
 * </p>
 *
 * @author _Alexzinv_
 * @since 2021-12-15
 */
@Service
public class PositionServiceImpl extends ServiceImpl<PositionMapper, Position> implements PositionService {

    private MemberUserCompanyClient memberUserCompanyClient;

    @Autowired
    public void setMemberUserCompanyClient(MemberUserCompanyClient memberUserCompanyClient) {
        this.memberUserCompanyClient = memberUserCompanyClient;
    }

    @Override
    public boolean save(Position entity) {
        Long companyId = entity.getCompanyId();
        Integer status = entity.getStatus();
        if(companyId == null){
            throw new HRMSException(ResultCodeEnum.NO_COMPANY_ID);
        }
        if(status == null){
            entity.setStatus(CompanyConstant.State.ACTIVATED);
        }
        return super.save(entity);
    }

    /**
     * 更新本地岗位名字需要同步更新关联表冗余字段
     * @param entity position
     * @return save-status */
    @GlobalTransactional(rollbackFor = Exception.class)
    @Override
    public boolean updateById(Position entity) {
        String name = entity.getName();
        if(StringUtils.hasText(name)){
            UserCompanyDepartmentPositionTo to = new UserCompanyDepartmentPositionTo();
            to.setPositionId(entity.getId());
            to.setPosition(entity.getName());
            memberUserCompanyClient.updateUserCompanyDepartmentPosition(to);
        }
        return super.updateById(entity);
    }

    @Override
    public void buildCondition(LambdaQueryWrapper<Position> wrapper, BaseQuery query) {
        if(query instanceof PositionQuery){
            PositionQuery positionQuery = (PositionQuery) query;
            Long companyId = positionQuery.getCompanyId();
            String name = positionQuery.getName();
            Integer status = positionQuery.getStatus();
            Integer sort = positionQuery.getSort();
            wrapper.eq(Position::getCompanyId, companyId)
                    .like(StringUtils.hasText(name), Position::getName, name)
                    .eq(status != null, Position::getStatus, status);

            if(sort == null){
                return;
            }

            if(PositionConstant.IsSort.ASC.equals(sort)){
                wrapper.orderByAsc(Position::getSort);
            }else {
                wrapper.orderByDesc(Position::getSort);
            }
        }
    }
}
