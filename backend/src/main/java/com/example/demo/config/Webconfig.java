package com.example.demo.config;

import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

public class Webconfig extends WebMvcConfigurerAdapter {
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