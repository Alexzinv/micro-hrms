package com.alex.system.service.impl;

import com.alex.common.base.AbstractBaseQuery;
import com.alex.system.dto.LogQuery;
import com.alex.system.entity.Log;
import com.alex.system.mapper.LogMapper;
import com.alex.system.service.LogService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @author _Alexzinv_
 * @date 2022/1/20
 */
@Service("logService")
public class LogServiceImpl extends ServiceImpl<LogMapper, Log> implements LogService {
    @Override
    public void buildCondition(LambdaQueryWrapper<Log> wrapper, AbstractBaseQuery query) {
        if(query instanceof LogQuery){
            LogQuery condition = (LogQuery) query;
            String username = condition.getUsername();
            Date start = condition.getStart();
            Date end = condition.getEnd();

            wrapper.like(StringUtils.isNotBlank(username), Log::getUsername, username)
                    .ge(start != null, Log::getCreateTime, start)
                    .le(end != null, Log::getCreateTime, end)
                    .orderByDesc(Log::getId);
        }
    }
}
