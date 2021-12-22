package com.alex.member.service.impl;

import com.alex.common.bean.member.UserCompanyDepartmentPositionTo;
import com.alex.common.consant.MemUserCompanyConstant;
import com.alex.common.consant.ResultCodeEnum;
import com.alex.common.exception.HRMSException;
import com.alex.common.util.CustomSerialGenerator;
import com.alex.common.util.DateUtils;
import com.alex.member.client.UserClient;
import com.alex.member.dto.UserCompanyQuery;
import com.alex.member.dto.UserCompanyRelationDO;
import com.alex.member.dto.struct.UserCompanyRelationStruct;
import com.alex.member.entity.UserCompany;
import com.alex.member.mapper.UserCompanyMapper;
import com.alex.member.service.UserCompanyService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.seata.spring.annotation.GlobalTransactional;
import org.springframework.beans.factory.annotation.Autowired;
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

    private UserClient userClient;

    @Autowired
    public void setUserClient(UserClient userClient) {
        this.userClient = userClient;
    }

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

        LambdaQueryWrapper<UserCompany> wrapper = Wrappers.lambdaQuery(UserCompany.class)
                .eq(UserCompany::getCompanyId, companyId)
                .eq(UserCompany::getJobStatus, jobStatus)
                .like(isNotBlank(nickname), UserCompany::getNickname, nickname)
                .eq(departmentId != null, UserCompany::getDepartmentId, departmentId)
                .like(isNotBlank(workingCity), UserCompany::getWorkingCity, workingCity)
                .eq(workNumber != null, UserCompany::getWorkNumber, workNumber)
                .like(isNotBlank(position), UserCompany::getPosition, position);

        return baseMapper.selectPage(pageEntity, wrapper);
    }

    @GlobalTransactional(rollbackFor = Exception.class)
    @Override
    public boolean update(UserCompany entity) {
        Long companyIdForUpdate = entity.getCompanyId();
        // 更新员工信息。需要更新的公司id为空
        if(companyIdForUpdate == null){
            return super.updateById(entity);
        }

        UserCompany userCompany = getById(entity.getId());
        Long companyId = userCompany.getCompanyId();

        // 保存关联到用户表
        UserCompanyRelationDO relationDO = UserCompanyRelationStruct.INSTANCE.toRelationDO(entity);
        userClient.updateById(relationDO);
        // 之前未关联公司，现在第一次关联，需要初始化数据
        if(companyId == null){
            // 生成工号和保存其他默认值
            init(companyIdForUpdate, entity);
            return super.updateById(entity);
        }

        // 已关联公司需要更换公司, 需初始化和清空值
        if(!companyId.equals(companyIdForUpdate)){
            // 清空无关值
            LambdaUpdateWrapper<UserCompany> wrapper = Wrappers.lambdaUpdate(UserCompany.class)
                    .eq(UserCompany::getId, userCompany.getCompanyId())
                    .set(UserCompany::getDepartmentId, null)
                    .set(UserCompany::getDepartmentName, null)
                    .set(UserCompany::getEmployForm, null)
                    .set(UserCompany::getJobStatus, null)
                    .set(UserCompany::getPosition, null)
                    .set(UserCompany::getPositionId, null)
                    .set(UserCompany::getWorkingCity, null);
            super.update(wrapper);
            // 生成工号和保存其他默认值
            init(companyIdForUpdate, entity);
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

    private void init(Long companyId, UserCompany entity){
        Long workerNumber = getCurrentWorkNumber(companyId);
        entity.setWorkNumber(workerNumber);
        entity.setJobStatus(MemUserCompanyConstant.JobStatus.IN_ACTIVE_SERVICE);
        entity.setJoinTime(Calendar.getInstance().getTime());
        entity.setCorrectionTime(DateUtils.addDateMonths(Calendar.getInstance().getTime(), 1));
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
