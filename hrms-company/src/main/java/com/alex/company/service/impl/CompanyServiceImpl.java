package com.alex.company.service.impl;

import com.alex.common.consant.CompanyConstant;
import com.alex.company.dto.CompanyQuery;
import com.alex.company.entity.Company;
import com.alex.company.mapper.CompanyMapper;
import com.alex.company.service.CompanyService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 *
 * @author _Alexzinv_
 * @since 2021-11-06
 */
@Service
public class CompanyServiceImpl extends ServiceImpl<CompanyMapper, Company> implements CompanyService {

    @Override
    public boolean save(Company company) {
        String name = company.getName();
        if(isExist(name)){
            return false;
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
        if(isExist(name)){
            return false;
        }
        return super.updateById(company);
    }

    @Override
    public Page<Company> listPage(Integer page, Integer limit, CompanyQuery companyQuery) {
        Page<Company> pageEntity = new Page<>(page, limit);

        QueryWrapper<Company> wrapper = new QueryWrapper<>();

        // TODO
        // String name = enterpriseQueryDO.getName();
        // String legalPerson = enterpriseQueryDO.getLegalPerson();
        // Date renewalDateBegin = enterpriseQueryDO.getRenewalDateBegin();
        // Date renewalDateEnd = enterpriseQueryDO.getRenewalDateEnd();

        // wrapper.like(org.springframework.util.StringUtils.hasText(name), "name", name).or()
        //         .like(org.springframework.util.StringUtils.hasText(legalPerson), "legal_person", legalPerson)
        //         .gt(null != renewalDateBegin, "renewal_date", renewalDateBegin)
        //         .le(null != renewalDateEnd, "renewal_date", renewalDateEnd);

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
