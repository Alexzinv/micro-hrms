package com.alex.thirdpart.config;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * @Author _Alexzinv_
 * @Date 2021/9/16
 * @Description oss配置
 */
@Configuration
public class OssConfig implements InitializingBean {

	@Value("${spring.cloud.alicloud.oss.endpoint}")
	private String endpoint;

	@Value("${spring.cloud.alicloud.oss.bucket}")
	private String bucket;

	@Value("${spring.cloud.alicloud.access-key}")
	private String accessId;

	public static String ENDPOINT;
	public static String BUCKET;
	public static String ACCESS_ID;

	@Override
	public void afterPropertiesSet() {
		ENDPOINT = endpoint;
		BUCKET = bucket;
		ACCESS_ID = accessId;
	}
}
