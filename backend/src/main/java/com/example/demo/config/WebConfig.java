package com.example.demo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@SuppressWarnings("deprecation")
@Configuration
public class WebConfig extends WebMvcConfigurerAdapter {
	
	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**")
				.allowedMethods("GET", "POST", "PUT", "DELETE", "PATCH", "HEAD")
				.allowedOrigins("*")
				.allowCredentials(true)
				.allowedHeaders("Content-Type", "Authorization")
				.exposedHeaders("Content-Type", "Authorization")
				.maxAge(3000);
	}
}