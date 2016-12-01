package com.bazlur.screening.config;

import org.modelmapper.ModelMapper;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.ShallowEtagHeaderFilter;

import javax.servlet.Filter;

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

    @Bean
    public FilterRegistrationBean filterRegistration() {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        filterRegistrationBean.setFilter(shallowEtagHeaderFilter());
        filterRegistrationBean.addUrlPatterns("/api/*");

        return filterRegistrationBean;
    }

    @Bean
    public Filter shallowEtagHeaderFilter() {
        return new ShallowEtagHeaderFilter();
    }
}
