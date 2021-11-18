package com.alex.thirdpart.config;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * @Author _Alexzinv_
 * @Date 2021/9/16
 * @Description mail配置
 */
@Configuration
public class MailConfig implements InitializingBean {

	@Value("${spring.mail.host}")
	private String host;

	@Value("${spring.mail.username}")
	private String username;

	@Value("${spring.mail.password}")
	private String password;

	public static String HOST;
	public static String USERNAME;
	public static String PASSWORD;

	@Override
	public void afterPropertiesSet() {
		HOST = host;
		USERNAME = username;
		PASSWORD = password;
	}
}
