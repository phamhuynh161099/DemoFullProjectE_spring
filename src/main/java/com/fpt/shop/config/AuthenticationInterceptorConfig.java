package com.fpt.shop.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.fpt.shop.interceptor.AdminAuthenticationInterceptor;

@Configuration
public class AuthenticationInterceptorConfig implements WebMvcConfigurer {

	/* dang ki cac interceptor vao dau vao */
	
	@Autowired
	private AdminAuthenticationInterceptor adminAuthenticationInterceptor;
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(adminAuthenticationInterceptor)
		.addPathPatterns("/admin/**");
	}
	
}
