package com.alex.member.service.impl;

import com.alex.common.bean.member.UserCompanyDepartmentPositionTo;
import com.alex.common.consant.MemUserCompanyConstant;
import com.alex.common.consant.ResultCodeEnum;
import com.alex.common.exception.HRMSException;
import com.alex.common.util.CustomSerialGenerator;
import com.alex.common.util.DateUtils;
import com.alex.member.dto.UserCompanyQuery;
import com.alex.member.entity.UserCompany;
import com.alex.member.mapper.UserCompanyMapper;
import com.alex.member.service.UserCompanyService;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Calendar;

/**
 * <p>
 * user公司扩展表 服务实现类
 * </p>
 *
 * @author _Alexzinv_
 * @since 2021-12-02
 */
@Service
public class UserCompanyServiceImpl extends ServiceImpl<UserCompanyMapper, UserCompany> implements UserCompanyService {

    @Override
    public Page<UserCompany> listPage(Integer page, Integer limit, UserCompanyQuery query) {
        Page<UserCompany> pageEntity = new Page<>(page, limit);

        Long companyId = query.getCompanyId();
        if(companyId == null){
            return new Page<>();
        }
        String nickname = query.getNickname();
        Long departmentId = query.getDepartmentId();
        String workingCity = query.getWorkingCity();
        Long workNumber = query.getWorkNumber();
        String position = query.getPosition();
        Integer jobStatus = query.getJobStatus();
        // 已离职的默认不查询
        if(jobStatus == null){
            jobStatus = 0;
        }

        LambdaQueryChainWrapper<UserCompany> wrapper = lambdaQuery()
                .eq(UserCompany::getCompanyId, companyId)
                .eq(UserCompany::getJobStatus, jobStatus)
                .like(isNotBlank(nickname), UserCompany::getNickname, nickname)
                .eq(departmentId != null, UserCompany::getDepartmentId, departmentId)
                .like(isNotBlank(workingCity), UserCompany::getWorkingCity, workingCity)
                .eq(workNumber != null, UserCompany::getWorkNumber, workNumber)
                .like(isNotBlank(position), UserCompany::getPosition, position);

        return baseMapper.selectPage(pageEntity, wrapper);
    }

    @Override
    public boolean update(UserCompany entity) {
        Long companyIdForUpdate = entity.getCompanyId();
        if(companyIdForUpdate == null){
            return super.updateById(entity);
        }
        UserCompany company = getById(entity.getId());
        Long companyId = company.getCompanyId();
        // 之前未关联公司 || 已关联公司需要更换公司
        if(companyId == null || !companyId.equals(companyIdForUpdate)){
            return this.save(entity);
        }
        return super.updateById(entity);
    }

    @Override
    public void updateUserCompanyDepartmentPosition(UserCompanyDepartmentPositionTo to) {
        Long departmentId = to.getDepartmentId();
        boolean b;
        if(departmentId != null){
            String departmentName = to.getDepartmentName();
            b = baseMapper.updateDepartmentName(departmentId, departmentName);
            if(!b){
                throw new HRMSException(ResultCodeEnum.GLOBAL_TRANSACTIONAL_EXCEPTION);
            }
        }
        Long positionId = to.getPositionId();
        if(positionId != null){
            String position = to.getPosition();
            b = baseMapper.updatePositionName(positionId, position);
            if(!b){
                throw new HRMSException(ResultCodeEnum.GLOBAL_TRANSACTIONAL_EXCEPTION);
            }
        }
    }

    @Override
    public boolean save(UserCompany entity) {
        Long companyId = entity.getCompanyId();
        if(companyId != null){
            // 生成工号和保存其他默认值
            Long workerNumber = getCurrentWorkNumber(companyId);
            entity.setWorkNumber(workerNumber);
            entity.setJobStatus(MemUserCompanyConstant.JobStatus.IN_ACTIVE_SERVICE);
            entity.setJoinTime(Calendar.getInstance().getTime());
            entity.setCorrectionTime(DateUtils.addDateMonths(Calendar.getInstance().getTime(), 1));
        }
        return super.save(entity);
    }

    private Long getCurrentWorkNumber(Long companyId) {
        // 查询出当前公司工号列最大值
        Long maxWorkNumber = baseMapper.getMaxWorkNumber(companyId);
        // 为空则是第一次添加，初始化为10000，否则按最大值自增
        return maxWorkNumber == null
                ? CustomSerialGenerator.initSerial()
                : ++maxWorkNumber;
    }

    private boolean isNotBlank(String s){
        return StringUtils.hasText(s);
    }
}
