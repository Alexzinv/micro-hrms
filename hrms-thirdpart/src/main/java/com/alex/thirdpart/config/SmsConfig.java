package com.alex.thirdpart.config;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * @Author _Alexzinv_
 * @Date 2021/9/16
 * @Description 短信服务API
 */
@Configuration
public class SmsConfig implements InitializingBean {

	@Value("${aliyun.sms.profile.keyid}")
	private String keyId;

	@Value("${aliyun.sms.profile.keysecret}")
	private String keySecret;

	public static String KEY_ID;
	public static String KEY_SECRET;

	@Override
	public void afterPropertiesSet() {
		KEY_ID = keyId;
		KEY_SECRET = keySecret;
	}
}
