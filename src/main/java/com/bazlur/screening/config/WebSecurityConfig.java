package com.bazlur.screening.config;

import com.bazlur.screening.interceptor.FacebookConnectInterceptor;
import com.bazlur.screening.security.SecurityAuthenticationFailureHandler;
import com.bazlur.screening.security.SecurityAuthenticationProvider;
import com.bazlur.screening.security.SecurityAuthenticationSuccessHandler;
import com.bazlur.screening.service.SignupService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.encoding.MessageDigestPasswordEncoder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.security.crypto.encrypt.TextEncryptor;
import org.springframework.security.web.authentication.RememberMeServices;
import org.springframework.security.web.authentication.rememberme.TokenBasedRememberMeServices;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.social.connect.jdbc.JdbcUsersConnectionRepository;
import org.springframework.social.connect.support.ConnectionFactoryRegistry;
import org.springframework.social.connect.web.ConnectController;
import org.springframework.social.facebook.connect.FacebookConnectionFactory;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

/**
 * @author Bazlur Rahman Rokon
 * @since 11/28/16.
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	private static final Logger log = LoggerFactory.getLogger(WebSecurityConfig.class);

	@Autowired
	private SecurityAuthenticationProvider securityAuthenticationProvider;

	@Autowired
	private Environment environment;

	@Autowired
	private SignupService signupService;

	@Autowired
	private DataSource dataSource;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
			.antMatchers("/css/**", "/js/**", "/img/**", "/plugins/**", "/bootstrap/**").permitAll()
			.antMatchers("/", "/home").authenticated()
			.antMatchers("/login").permitAll()
			.antMatchers("/signup").permitAll()
			.antMatchers("/connect/**").permitAll()
			.antMatchers("/logout/**").authenticated()
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
			.invalidateHttpSession(true)
			.deleteCookies("JSESSIONID", "online_screening_test")
			.permitAll()
			.and()
			.authenticationProvider(securityAuthenticationProvider)
			.exceptionHandling().and()
			.rememberMe()
			.rememberMeServices(rememberMeServices());
	}

	@Bean
	public MessageDigestPasswordEncoder messageDigestPasswordEncoder() {

		return new MessageDigestPasswordEncoder("sha-256");
	}

	@Bean
	public RememberMeServices rememberMeServices() {
		TokenBasedRememberMeServices rememberMeServices = new TokenBasedRememberMeServices("password", userDetailsService());
		rememberMeServices.setCookieName(environment.getProperty("local.cookieName"));
		rememberMeServices.setParameter("rememberMe");
		return rememberMeServices;
	}

	@Bean
	public ConnectController connectController() {
		ConnectController controller = new ConnectController(connectionFactoryLocator(), connectionRepository());
		controller.addInterceptor(new FacebookConnectInterceptor(signupService));

		return controller;
	}

	@Bean
	public ConnectionFactoryLocator connectionFactoryLocator() {
		ConnectionFactoryRegistry registry = new ConnectionFactoryRegistry();
		String clinetId = environment.getProperty("facebook.clientId");
		String appSecret = environment.getProperty("facebook.clientSecret");

		registry.addConnectionFactory(new FacebookConnectionFactory(clinetId, appSecret));

		return registry;
	}

	@Bean
	@Scope(value = "request", proxyMode = ScopedProxyMode.INTERFACES)
	public ConnectionRepository connectionRepository() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication == null) {
			throw new IllegalStateException("Unable to get a ConnectionRepository: no user signed in");
		}

		return usersConnectionRepository().createConnectionRepository(authentication.getName());
	}

	@Bean
	public UsersConnectionRepository usersConnectionRepository() {

		return new JdbcUsersConnectionRepository(dataSource, connectionFactoryLocator(), textEncryptor());
	}

	@Bean
	public TextEncryptor textEncryptor() {
		String localEncryptionPassword = environment.getProperty("local.encryption.password");
		String localEncryptionSalt = environment.getProperty("local.encryption.hexSalt");

		return Encryptors.delux(localEncryptionPassword, localEncryptionSalt);
	}

	//work around deu to facebook api Changes
	//http://stackoverflow.com/a/40312144/893197
	@PostConstruct
	private void init() {
		try {
			String[] fieldsToMap = {"id", "about", "age_range", "birthday",
				"context", "cover", "currency", "devices", "education",
				"email", "favorite_athletes", "favorite_teams",
				"first_name", "gender", "hometown", "inspirational_people",
				"installed", "install_type", "is_verified", "languages",
				"last_name", "link", "locale", "location", "meeting_for",
				"middle_name", "name", "name_format", "political",
				"quotes", "payment_pricepoints", "relationship_status",
				"religion", "security_settings", "significant_other",
				"sports", "test_group", "timezone", "third_party_id",
				"updated_time", "verified", "viewer_can_send_gift",
				"website", "work"};

			Field field = Class.forName(
				"org.springframework.social.facebook.api.UserOperations")
				.getDeclaredField("PROFILE_FIELDS");
			field.setAccessible(true);

			Field modifiers = field.getClass().getDeclaredField("modifiers");
			modifiers.setAccessible(true);
			modifiers.setInt(field, field.getModifiers() & ~Modifier.FINAL);
			field.set(null, fieldsToMap);

		} catch (Exception ex) {
			log.error("failed in post construct", ex);
		}
	}

	@Configuration
	@Order(Ordered.HIGHEST_PRECEDENCE)
	static class  BasicSecurityConfig extends WebSecurityConfigurerAdapter{
        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http.csrf().disable();
            http.antMatcher("/api/**") //
                    .authorizeRequests().anyRequest().permitAll() //TODO for now
                    .and() //
                    .httpBasic();
        }
    }
}
