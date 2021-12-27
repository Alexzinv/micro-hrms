package com.alex.common.util;

import com.alex.common.consant.ResultCodeEnum;

/**
 * @author _Alexzinv_
 * @date 2021/12/22
 * @description 基础客户端服务
 */
public abstract class BaseClient {

    protected static final R RESULT = R.err().result(ResultCodeEnum.SYSTEM_BUSY);

    protected R result(ResultCodeEnum resultCodeEnum){
        return R.err().result(resultCodeEnum);
    }
}