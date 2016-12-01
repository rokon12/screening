package com.bazlur.screening.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Bazlur Rahman Rokon
 * @since 12/1/16.
 */
@Configuration
public class ApplicationConfig {
    @Bean
    public ModelMapper modelMapper() {

        return new ModelMapper();
    }
}
