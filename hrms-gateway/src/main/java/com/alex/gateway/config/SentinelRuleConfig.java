package com.alex.gateway.config;

import com.alibaba.csp.sentinel.adapter.gateway.common.api.ApiDefinition;
import com.alibaba.csp.sentinel.adapter.gateway.common.api.ApiPathPredicateItem;
import com.alibaba.csp.sentinel.adapter.gateway.common.api.GatewayApiDefinitionManager;
import com.alibaba.csp.sentinel.adapter.gateway.common.rule.GatewayFlowRule;
import com.alibaba.csp.sentinel.adapter.gateway.common.rule.GatewayRuleManager;
import com.alibaba.csp.sentinel.adapter.gateway.sc.SentinelGatewayFilter;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

import javax.annotation.PostConstruct;
import java.util.HashSet;
import java.util.Set;

/**
 * @author _Alexzinv_
 * @date 2022/1/12
 * @description
 */
@Configuration
public class SentinelRuleConfig {

    /** 配置限流过滤器 */
    @Bean
    @Order(-1)
    public GlobalFilter sentinelGatewayFilter() {
        return new SentinelGatewayFilter();
    }

    @PostConstruct
    public void doInit() {
        initCustomizedApis();
        initGatewayRules();
    }

    /** 网关限流规则 */
    private void initGatewayRules() {
        Set<GatewayFlowRule> rules = new HashSet<>();
        rules.add(new GatewayFlowRule("hrms-system")
                // 限流阈值
                .setCount(6)
                // 时间间隔，单位是秒，默认是 1 秒
                .setIntervalSec(2)
        );
        rules.add(new GatewayFlowRule("hrms-company")
                .setCount(4)
                .setIntervalSec(2)
        );
        // 加载网关限流规则
        GatewayRuleManager.loadRules(rules);
    }

    /** 网关分组限流 */
    private void initCustomizedApis() {
        Set<ApiDefinition> definitions = new HashSet<>();
        ApiDefinition api1 = new ApiDefinition("hrms-system").setPredicateItems(
                new HashSet<>() {{
                    add(new ApiPathPredicateItem().setPattern("/admin/acl/permission/listAll"));
                    add(new ApiPathPredicateItem().setPattern("/admin/acl/role/**"));
                }});

        ApiDefinition api2 = new ApiDefinition("hrms-company").setPredicateItems(
                new HashSet<>() {{
                    add(new ApiPathPredicateItem().setPattern("/company/company/listPage/**"));
                    add(new ApiPathPredicateItem().setPattern("/company/department/list/**"));
                    add(new ApiPathPredicateItem().setPattern("/company/position/listPage/**"));
                }});
        definitions.add(api1);
        definitions.add(api2);
        GatewayApiDefinitionManager.loadApiDefinitions(definitions);
    }
}
