package com.alex.member.service.impl;

import com.alex.common.base.AbstractBaseQuery;
import com.alex.member.dto.UserTrainQuery;
import com.alex.member.entity.UserTrain;
import com.alex.member.mapper.UserTrainMapper;
import com.alex.member.service.UserTrainService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 员工培训表 服务实现类
 * </p>
 *
 * @author _Alexzinv_
 * @since 2022-02-27
 */
@Service
public class UserTrainServiceImpl extends ServiceImpl<UserTrainMapper, UserTrain> implements UserTrainService {

    @Override
    public void buildCondition(LambdaQueryWrapper<UserTrain> wrapper, AbstractBaseQuery query) {
        if(query instanceof UserTrainQuery){
            UserTrainQuery condition = (UserTrainQuery) query;

        }
    }
}
