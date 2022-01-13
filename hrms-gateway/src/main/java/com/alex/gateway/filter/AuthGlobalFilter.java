package com.alex.gateway.filter;

import com.alex.common.consant.ResultCodeEnum;
import com.alex.common.consant.TokenConstant;
import com.alex.common.util.R;
import com.alex.gateway.util.AccessFilterUtils;
import io.jsonwebtoken.Jwts;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;

/**
 * @author _Alexzinv_
 * @date 2022/1/10
 * @description 网关全局过滤器 校验token
 */
@Component
@Slf4j
public class AuthGlobalFilter implements GlobalFilter, Ordered {

	private final RedisTemplate<String, Object> redisTemplate;
	private final BlackList blackList;
	private final WhiteList whiteList;

	@Autowired
	public AuthGlobalFilter(RedisTemplate<String, Object> redisTemplate, BlackList blackList, WhiteList whiteList) {
		this.redisTemplate = redisTemplate;
		this.blackList = blackList;
		this.whiteList = whiteList;
	}

	@Override
	public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
		ServerHttpRequest request = exchange.getRequest();
		String path = request.getURI().getPath();
		log.info("=================" + path);
		// 白名单
		if(AccessFilterUtils.match(path, whiteList.getWhiteList())){
			return chain.filter(exchange);
		}
		// 禁止外部访问接口
		if(AccessFilterUtils.match(path, blackList.getBlackList())){
			return outResponse(exchange);
		}
		String token = getToken(request);
		if(StringUtils.isBlank(token)) {
			return outResponse(exchange);
		}
		if(!checkToken(token)){
			return outResponse(exchange);
		}
		return chain.filter(exchange);
	}

	@Override
	public int getOrder() {
		return 0;
	}

	private Mono<Void> out(ServerHttpResponse response) {
		R r = R.err().result(ResultCodeEnum.GATEWAY_AUTH_EXCEPTION);
		byte[] bits = r.toString().getBytes(StandardCharsets.UTF_8);
		DataBuffer buffer = response.bufferFactory().wrap(bits);
		response.getHeaders().add("Content-Type", "application/json;charset=UTF-8");
		return response.writeWith(Mono.just(buffer));
	}

	/**
	 * 获取请求token
	 */
	private String getToken(ServerHttpRequest request) {
		String token = request.getHeaders().getFirst(TokenConstant.AUTHENTICATION);
		// 如果前端设置了令牌前缀，则裁剪掉前缀
		if (StringUtils.isNotEmpty(token) && token.startsWith(TokenConstant.PREFIX)) {
			token = token.replaceFirst(TokenConstant.PREFIX, StringUtils.EMPTY);
		}
		return token;
	}

	/**
	 * 验证token
	 */
	private boolean checkToken(String token) {
		String s = Jwts.parser().setSigningKey(TokenConstant.SECRET).parseClaimsJws(token).getBody().getSubject();
		Boolean hasKey = redisTemplate.hasKey(s);
		return hasKey != null && hasKey;
	}

	private Mono<Void> outResponse(ServerWebExchange exchange){
		ServerHttpResponse response = exchange.getResponse();
		return out(response);
	}

}
