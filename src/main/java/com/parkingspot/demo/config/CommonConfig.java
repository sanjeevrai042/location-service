package com.parkingspot.demo.config;

import java.util.concurrent.Executor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.client.RestTemplate;

@Configuration
@EnableAsync
public class CommonConfig {

	@Bean
    public Executor asyncExecutor() {
		 return new ThreadPoolTaskExecutor();
	 }	
	
	
}
