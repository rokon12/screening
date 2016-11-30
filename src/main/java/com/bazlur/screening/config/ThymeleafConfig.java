package com.bazlur.screening.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.thymeleaf.extras.java8time.dialect.Java8TimeDialect;

/**
 * @author Bazlur Rahman Rokon
 * @since 11/30/16.
 */
@Configuration
public class ThymeleafConfig {

	@Bean
	public Java8TimeDialect java8TimeDialect() {

		return new Java8TimeDialect();
	}
}
