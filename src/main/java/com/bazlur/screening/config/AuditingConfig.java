package com.bazlur.screening.config;

import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * @author Bazlur Rahman Rokon
 * @since 11/30/16.
 */
@EnableJpaAuditing
public class AuditingConfig {

	@Bean
	public AuditorAware<String> createAuditorProvider() {

		return new SecurityAuditor();
	}

	@Bean
	public AuditingEntityListener createAuditingListener() {

		return new AuditingEntityListener();
	}

	public static class SecurityAuditor implements AuditorAware<String> {
		@Override
		public String getCurrentAuditor() {
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();

			return auth.getName();
		}
	}
}
