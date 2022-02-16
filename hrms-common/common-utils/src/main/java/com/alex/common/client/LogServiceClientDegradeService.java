package com.alex.common.client;

import com.alex.common.base.AbstractBaseClient;
import com.alex.common.bean.system.Log;
import com.alex.common.util.R;

/**
 * @author _Alexzinv_
 * @date 2022/2/16
 * @description
 */
public class LogServiceClientDegradeService extends AbstractBaseClient implements LogServiceClient {
    @Override
    public R saveLog(Log log) {
        return RESULT;
    }
}
