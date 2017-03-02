package com.one.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@SpringBootApplication
@ImportResource("classpath:spring-security.xml")
@ComponentScan(basePackages = { "com" })
@EnableAutoConfiguration
@PropertySource("classpath:message.properties")
public class App {
	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}

	// 跨域配置
	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurerAdapter() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				// 参数为url
				registry.addMapping("/**")
				// 允许的ip，参数字符串或者字符串数组 *=匹配所有  
				.allowedOrigins(new String[]{"http://localhost","http://localhost:9999"});
			}
		};
	}
}
