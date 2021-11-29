package com.alex.system.client;

import com.alex.common.consant.ResultCodeEnum;
import com.alex.common.util.R;
import org.springframework.stereotype.Component;

/**
 * @Author _Alexzinv_
 * @Date 2021/11/29
 * @Description Company RPC degrade service
 */
@Component
public class CompanyDegradeService implements CompanyClient {
    @Override
    public R get(Long id) {
        return R.err().result(ResultCodeEnum.SYSTEM_BUSY);
    }
}
