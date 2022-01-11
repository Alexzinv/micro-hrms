package com.alex.gateway.config;

import com.alex.gateway.handle.SentinelFallbackHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;

/**
 * @author _Alexzinv_
 * @date 2022/1/11
 * @description 网关限流配置
 */
@Configuration
public class GatewaySentinelConfig {
    @Bean
    @Order(Ordered.HIGHEST_PRECEDENCE)
    public SentinelFallbackHandler sentinelGatewayExceptionHandler() {
        return new SentinelFallbackHandler();
    }
}
