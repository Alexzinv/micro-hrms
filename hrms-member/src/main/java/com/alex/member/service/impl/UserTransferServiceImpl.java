package com.alex.member.service.impl;

import com.alex.common.base.AbstractBaseQuery;
import com.alex.member.dto.UserTransferQuery;
import com.alex.member.entity.UserTransfer;
import com.alex.member.mapper.UserTransferMapper;
import com.alex.member.service.UserTransferService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 员工调职表 服务实现类
 * </p>
 *
 * @author _Alexzinv_
 * @since 2022-02-27
 */
@Service
public class UserTransferServiceImpl extends ServiceImpl<UserTransferMapper, UserTransfer> implements UserTransferService {

    @Override
    public void buildCondition(LambdaQueryWrapper<UserTransfer> wrapper, AbstractBaseQuery query) {
        if(query instanceof UserTransferQuery){
            UserTransferQuery condition = (UserTransferQuery) query;

        }
    }

    @Override
    public List<UserTransfer> listByUserCompanyId(Long userCompanyId) {
        return baseMapper.selectList(new LambdaQueryWrapper<UserTransfer>().eq(UserTransfer::getUserCompanyId, userCompanyId));
    }
}
