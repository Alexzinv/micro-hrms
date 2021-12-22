package com.alex.company.service.impl;

import com.alex.common.consant.CompanyConstant;
import com.alex.common.consant.ResultCodeEnum;
import com.alex.common.exception.HRMSException;
import com.alex.company.dto.CompanyQuery;
import com.alex.company.entity.Company;
import com.alex.company.mapper.CompanyMapper;
import com.alex.company.service.CompanyService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Date;

/**
 *
 * @author _Alexzinv_
 * @since 2021-11-06
 */
@CacheConfig(cacheNames = "co-company")
@Service
public class CompanyServiceImpl extends ServiceImpl<CompanyMapper, Company> implements CompanyService {

    @Override
    public boolean save(Company company) {
        String name = company.getName();
        if(isExist(name)){
            throw new HRMSException(ResultCodeEnum.EXISTS_EXCEPTION);
        }
        company.setAuditState(CompanyConstant.AuditState.UNREVIEWED);
        company.setState(CompanyConstant.State.NOT_ACTIVATED);
        super.save(company);
        return true;
    }

    @Override
    public boolean updateById(Company company) {
        String name = company.getName();
        if(!StringUtils.hasText(name)){
            return super.updateById(company);
        }
        Company rawCompany = getById(company.getId());
        String companyName = rawCompany.getName();
        if(name.equals(companyName)){
            return super.updateById(company);
        }
        if(isExist(name)){
            throw new HRMSException(ResultCodeEnum.EXISTS_EXCEPTION);
        }
        return super.updateById(company);
    }

    @Cacheable
    @Override
    public Page<Company> listPage(Integer page, Integer limit, CompanyQuery companyQuery) {
        Page<Company> pageEntity = new Page<>(page, limit);

        String name = companyQuery.getName();
        Integer state = companyQuery.getState();
        Integer auditState = companyQuery.getAuditState();
        Date begin = companyQuery.getExpirationDateBegin();
        Date end = companyQuery.getExpirationDateEnd();
        LambdaQueryWrapper<Company> wrapper = Wrappers.lambdaQuery(Company.class);

        if(StringUtils.hasText(name)){
            wrapper.and(item -> item.eq(Company::getName, name).or()
                    .like(Company::getLegalRepresentative, name)
            );
        }

        wrapper.eq(state != null, Company::getState, state)
                .eq(auditState != null, Company::getAuditState, auditState)
                .gt(null != begin, Company::getExpirationDate, begin)
                .le(null != end, Company::getExpirationDate, end);

        return baseMapper.selectPage(pageEntity, wrapper);
    }

    /**
     * 公司是否已经存在
     * @param name 公司名
     * @return 是否存在
     */
    private boolean isExist(String name){
        int count = super.count(new QueryWrapper<Company>().eq("name", name));
        return count > 0;
    }
}
