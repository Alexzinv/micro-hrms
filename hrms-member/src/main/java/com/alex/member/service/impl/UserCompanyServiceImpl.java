package com.alex.member.service.impl;

import com.alex.member.dto.UserCompanyQuery;
import com.alex.member.entity.UserCompany;
import com.alex.member.mapper.UserCompanyMapper;
import com.alex.member.service.UserCompanyService;
import com.alex.member.util.SerialGenerator;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

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

        String nickname = query.getNickname();
        Long departmentId = query.getDepartmentId();
        String workingCity = query.getWorkingCity();
        Long workNumber = query.getWorkNumber();
        String position = query.getPosition();
        Integer form = query.getEmployForm();
        Integer jobStatus = query.getJobStatus();

        if(jobStatus == null){
            jobStatus = 0;
        }

        LambdaQueryChainWrapper<UserCompany> wrapper = lambdaQuery()
                .like(isNotBlank(nickname), UserCompany::getNickname, nickname)
                .eq(departmentId != null, UserCompany::getDepartmentId, departmentId)
                .like(isNotBlank(workingCity), UserCompany::getWorkingCity, workingCity)
                .eq(workNumber != null, UserCompany::getWorkNumber, workNumber)
                .like(isNotBlank(position), UserCompany::getPosition, position)
                .eq(form != null, UserCompany::getEmployForm, form)
                .eq(UserCompany::getJobStatus, jobStatus);

        return baseMapper.selectPage(pageEntity, wrapper);
    }



    @Override
    public boolean save(UserCompany entity) {
        // 生成工号
        Long workerNumber = getCurrentWorkNumber();
        entity.setWorkNumber(workerNumber);
        return super.save(entity);
    }

    private Long getCurrentWorkNumber() {
        // 查询出工号最大值
        Long maxWorkNumber = baseMapper.getMaxWorkNumber();
        // 为空则是第一次添加，初始化，否则按最大值自增
        return maxWorkNumber == null
                ? SerialGenerator.initSerial()
                : ++maxWorkNumber;
    }

    private boolean isNotBlank(String s){
        return StringUtils.hasText(s);
    }
}
