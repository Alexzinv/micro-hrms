package com.alex.system.client;

import com.alex.common.util.BaseClient;
import com.alex.common.util.R;
import org.springframework.stereotype.Component;

/**
 * @Author _Alexzinv_
 * @Date 2021/11/29
 * @Description 公司远程调用降级服务
 */
@Component
public class CompanyDegradeService extends BaseClient implements CompanyClient {
    @Override
    public R getCompany(Long id) {
        return RESULT;
    }

    @Override
    public R getDepartment(Long id) {
        return RESULT;
    }
}
