package com.alex.system.service.impl;

import com.alex.common.base.BaseQuery;
import com.alex.system.entity.Log;
import com.alex.system.mapper.LogMapper;
import com.alex.system.service.LogService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * @author _Alexzinv_
 * @date 2022/1/20
 */
@Service("logService")
public class LogServiceImpl extends ServiceImpl<LogMapper, Log> implements LogService {
    @Override
    public void buildCondition(LambdaQueryWrapper<Log> wrapper, BaseQuery query) {

    }
}
