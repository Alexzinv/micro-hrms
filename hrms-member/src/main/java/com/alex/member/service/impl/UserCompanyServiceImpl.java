package com.alex.member.service.impl;

import com.alex.member.dto.UserCompanyQuery;
import com.alex.member.entity.UserCompany;
import com.alex.member.mapper.UserCompanyMapper;
import com.alex.member.service.UserCompanyService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Date;

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

        QueryWrapper<UserCompany> wrapper = new QueryWrapper<>();

        // String name = query.getName();
        // Integer state = query.getState();
        // Integer auditState = query.getAuditState();
        // Date begin = query.getExpirationDateBegin();
        // Date end = query.getExpirationDateEnd();
        //
        // if(StringUtils.hasText(name)){
        //     wrapper.and(item -> item.eq("name", name).or()
        //             .like("legal_representative", name)
        //     );
        // }
        //
        // wrapper.eq(state != null, "state", state)
        //         .eq(auditState != null, "audit_state", auditState)
        //         .gt(null != begin, "expiration_date", begin)
        //         .le(null != end, "expiration_date", end);

        return baseMapper.selectPage(pageEntity, wrapper);
    }
}
