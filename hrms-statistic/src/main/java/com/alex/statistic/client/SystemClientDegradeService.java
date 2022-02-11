package com.alex.statistic.client;

import com.alex.common.base.AbstractBaseClient;
import com.alex.common.util.R;
import org.springframework.stereotype.Component;

/**
 * @author _Alexzinv_
 * @date 2022/2/10
 * @description
 */
@Component
public class SystemClientDegradeService extends AbstractBaseClient implements SystemClient {

    @Override
    public R registerCount(String date) {
        return RESULT;
    }
}
