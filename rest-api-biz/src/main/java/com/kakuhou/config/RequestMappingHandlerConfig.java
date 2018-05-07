package com.kakuhou.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

/**
 * 接口mapping配置
 * 
 * @author: guopeng
 * @date: 2018年5月7日
 * @title 接口mapping配置
 */
@Configuration
public class RequestMappingHandlerConfig {
	@Bean
	public RequestMappingHandlerMapping requestMappingHandlerMapping() {
		RequestMappingHandlerMapping mapping = new RequestMappingHandlerMapping();
		return mapping;
	}
}
