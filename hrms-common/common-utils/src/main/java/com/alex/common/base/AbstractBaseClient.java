package com.alex.common.base;

import com.alex.common.consant.ResultCodeEnum;
import com.alex.common.util.R;

/**
 * @author _Alexzinv_
 * @date 2021/12/22
 * @description 基础客户端服务
 */
public abstract class AbstractBaseClient {

    protected static final R RESULT = R.err().result(ResultCodeEnum.SYSTEM_BUSY);

    protected R result(ResultCodeEnum resultCodeEnum){
        return R.err().result(resultCodeEnum);
    }
}
