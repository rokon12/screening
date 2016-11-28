package com.bazlur.screening.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.web.context.request.RequestContextHolder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Set;

/**
 * @author Bazlur Rahman Rokon
 * @since 11/28/16.
 */
public class SecurityAuthenticationSuccessHandler implements org.springframework.security.web.authentication.AuthenticationSuccessHandler {
	private static final Logger LOGGER = LoggerFactory.getLogger(SecurityAuthenticationSuccessHandler.class);

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
		LOGGER.trace("onAuthenticationSuccess() login successful with login={}, sid={}", authentication.getName(), RequestContextHolder.currentRequestAttributes().getSessionId());

		//TODO depending on role, redirect to the user page
		Set<String> roles = AuthorityUtils.authorityListToSet(authentication.getAuthorities());
		LOGGER.info("roles: {} ", roles);

		response.sendRedirect("/home");
	}
}
