package com.bazlur.screening.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Bazlur Rahman Rokon
 * @since 11/28/16.
 */
public class SecurityAuthenticationFailureHandler implements org.springframework.security.web.authentication.AuthenticationFailureHandler {
	private static final Logger LOGGER = LoggerFactory.getLogger(SecurityAuthenticationFailureHandler.class);

	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
		String userName = request.getParameter("username");
		LOGGER.info("onAuthenticationFailure- username={}, exceptionClass={}", userName, exception.getClass().getName());

		String parameter = "";

		if (exception instanceof UsernameNotFoundException) {
			parameter = "usernameEmpty";
		} else if (exception instanceof BadCredentialsException) {
			parameter = "badCredential";
		} else if (exception instanceof LockedException) {
			parameter = "userLocked";
		} else if (exception instanceof DisabledException) {
			parameter = "userNotActivated" + "&userEmail=" + userName;
		} else if (exception instanceof AccountExpiredException) {
			parameter = "accountExpired";
		}

		response.sendRedirect("/login?errorType=" + parameter);
	}
}
