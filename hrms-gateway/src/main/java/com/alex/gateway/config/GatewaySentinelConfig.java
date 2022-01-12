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

    // private final List<ViewResolver> viewResolvers;
    //
    // private final ServerCodecConfigurer serverCodecConfigurer;
    //
    // public GatewaySentinelConfig(ObjectProvider<List<ViewResolver>> viewResolversProvider,
    //                             ServerCodecConfigurer serverCodecConfigurer) {
    //     this.viewResolvers = viewResolversProvider.getIfAvailable(Collections::emptyList);
    //     this.serverCodecConfigurer = serverCodecConfigurer;
    // }
    //
    // /**
    //  * 限流异常处理器, 暂时使用自定义 SentinelFallbackHandler */
    // @Bean
    // @Order(Ordered.HIGHEST_PRECEDENCE)
    // public SentinelGatewayBlockExceptionHandler sentinelGatewayBlockExceptionHandler() {
    //     // Register the block exception handler for Spring Cloud Gateway.
    //     return new SentinelGatewayBlockExceptionHandler(viewResolvers, serverCodecConfigurer);
    // }

    @Bean
    @Order(Ordered.HIGHEST_PRECEDENCE)
    public SentinelFallbackHandler sentinelGatewayExceptionHandler() {
        return new SentinelFallbackHandler();
    }
}
