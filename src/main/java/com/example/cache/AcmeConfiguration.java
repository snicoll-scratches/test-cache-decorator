package com.example.cache;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AcmeConfiguration {

	@Bean
	public static AcmeCacheManagerPostProcessor acmeCacheManagerPostProcessor() {
		return new AcmeCacheManagerPostProcessor();
	}

}
