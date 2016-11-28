package com.bazlur.screening.security;

import com.bazlur.screening.domain.User;
import com.bazlur.screening.repository.UserRepository;
import com.bazlur.screening.utils.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.authentication.encoding.MessageDigestPasswordEncoder;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @author Bazlur Rahman Rokon
 * @since 11/28/16.
 */
@Service("securityAuthenticationProvider")
public class SecurityAuthenticationProvider extends AbstractUserDetailsAuthenticationProvider {
	private static final Logger LOGGER = LoggerFactory.getLogger(AuthenticationProvider.class);

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private MessageDigestPasswordEncoder messageDigestPasswordEncoder;

	@Override
	protected void additionalAuthenticationChecks(UserDetails userDetails, UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {
		LOGGER.info("additionalAuthenticationChecks, userDetails={}", userDetails == null ? "null" : userDetails.toString());
	}

	@Override
	protected UserDetails retrieveUser(String username, UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {
		LOGGER.info("retrieveUser() username:{}", username);

		if (StringUtils.isEmpty(username)) {
			setHideUserNotFoundExceptions(false);
			throw new UsernameNotFoundException("Enter your email address.");
		}

		Optional<User> userOptional = userRepository.findOneByEmail(username);
		String givenPassword = (String) authentication.getCredentials();

		if (!userOptional.isPresent()) {
			throw new BadCredentialsException("Incorrect username or password.");
		}

		User targetUser = userOptional.get();

		if (!messageDigestPasswordEncoder.encodePassword(givenPassword, targetUser.getSalt()).equals(targetUser.getPassword())) {

			throw new BadCredentialsException("Incorrect username or password.");
		}

		return targetUser;
	}
}
