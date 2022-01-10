package com.alex.common.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import static com.google.common.base.Predicates.not;

/**
 * @Author _Alexzinv_
 * @Date 2021/9/26
 * @Description Swagger配置类
 */

@EnableSwagger2
@Configuration
public class SwaggerConfig implements WebMvcConfigurer {

	/**
	 * swagger插件
	 * paths: 包含路径不进行显示
	 */
	@Bean
	@SuppressWarnings(value = "all")
	public Docket webApiConfig(){
		return new Docket(DocumentationType.SWAGGER_2)
				.groupName("webApi")
				.apiInfo(webApiInfo())
				.select()
				.paths(not(PathSelectors.regex("/error.*")))
				.build();
	}

	private ApiInfo webApiInfo(){
		return new ApiInfoBuilder()
				.title("网站-人力资源管理Api")
				.description("本文档描述了人力资源管理微服务接口定义")
				.version("1.0")
				.contact(new Contact("Alex", "http://alex.com", "228722771@qq.com"))
				.build();
	}

}
