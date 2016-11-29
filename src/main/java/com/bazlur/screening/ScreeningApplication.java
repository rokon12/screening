package com.bazlur.screening;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication //(exclude={org.springframework.boot.autoconfigure.thymeleaf.ThymeleafAutoConfiguration.class} )
public class ScreeningApplication {

	public static void main(String[] args) {
		SpringApplication.run(ScreeningApplication.class, args);
	}
}
