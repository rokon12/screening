package com.bazlur.screening.config;

import com.bazlur.screening.security.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.encoding.MessageDigestPasswordEncoder;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import static org.apache.coyote.http11.Constants.a;

/**
 * @author Bazlur Rahman Rokon
 * @since 11/28/16.
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private SecurityAuthenticationProvider securityAuthenticationProvider;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
			.antMatchers("/css/**", "/js/**", "/img/**", "/plugins/**", "/bootstrap/**").permitAll()
			.antMatchers("/", "/home").permitAll()
			.antMatchers("/login").permitAll()
			.antMatchers("/signup").permitAll()
			.anyRequest().authenticated()
			.and()
			.formLogin()
			.loginPage("/login")
			.successHandler(new SecurityAuthenticationSuccessHandler())
			.failureHandler(new SecurityAuthenticationFailureHandler())
			.permitAll()
			.and()
			.logout()
			.logoutRequestMatcher(new AntPathRequestMatcher("/logout")).logoutSuccessUrl("/login?logout")
			.invalidateHttpSession(true).deleteCookies("JSESSIONID")
			.permitAll()
			.and()
			.authenticationProvider(securityAuthenticationProvider)
		;

	}

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth
			.inMemoryAuthentication()
			.withUser("admin").password("admin").roles("USER");
	}


	@Bean
	public MessageDigestPasswordEncoder messageDigestPasswordEncoder() {

		return new MessageDigestPasswordEncoder("sha-256");
	}

}
