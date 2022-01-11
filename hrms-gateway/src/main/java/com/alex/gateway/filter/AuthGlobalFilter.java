package com.alex.gateway.filter;

import com.alex.common.consant.TokenConstant;
import com.google.gson.JsonObject;
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
import org.springframework.util.AntPathMatcher;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;

/**
 * @Author _
 * @Description
 */
@Component
@Slf4j
@SuppressWarnings({"rawtypes", "unchecked"})
public class AuthGlobalFilter implements GlobalFilter, Ordered {

	private final AntPathMatcher antPathMatcher = new AntPathMatcher();
	private static final String AUTH = "/**";
	private static final String INNER = "/**/inner/**";
	private RedisTemplate<String, Object> redisTemplate;

	@Autowired
	public void setRedisTemplate(RedisTemplate redisTemplate) {
		this.redisTemplate = redisTemplate;
	}

	@Override
	public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
		ServerHttpRequest request = exchange.getRequest();
		String path = request.getURI().getPath();
		log.info("=================" + path);
		//校验用户必须登录
		if(antPathMatcher.match(AUTH, path)) {
			if(WhiteList.WHITELIST.contains(path)){
				return chain.filter(exchange);
			}
			String token = getToken(request);
			if(StringUtils.isBlank(token)) {
				return outResponse(exchange);
			}
			if(!checkToken(token)){
				return outResponse(exchange);
			}
		}
		//内部服务接口，不允许外部访问
		if(antPathMatcher.match(INNER, path)) {
			return outResponse(exchange);
		}
		return chain.filter(exchange);
	}

	@Override
	public int getOrder() {
		return 0;
	}

	private Mono<Void> out(ServerHttpResponse response) {
		JsonObject message = new JsonObject();
		message.addProperty("success", false);
		message.addProperty("code", 28004);
		message.addProperty("data", "鉴权失败");
		byte[] bits = message.toString().getBytes(StandardCharsets.UTF_8);
		DataBuffer buffer = response.bufferFactory().wrap(bits);
		/// response.setStatusCode(HttpStatus.UNAUTHORIZED);
		/// 指定编码，否则在浏览器中会中文乱码
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
		log.info("---------"+s);
		// Boolean hasKey = redisTemplate.hasKey(s);
		// log.info("++++++key"+ hasKey);
		// return hasKey != null && hasKey;
		return StringUtils.isNotBlank(s);
	}

	private Mono<Void> outResponse(ServerWebExchange exchange){
		ServerHttpResponse response = exchange.getResponse();
		return out(response);
	}

}
