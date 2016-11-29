package com.bazlur.screening.service;

import com.bazlur.screening.domain.Role;
import com.bazlur.screening.domain.User;
import com.bazlur.screening.domain.UserRole;
import com.bazlur.screening.dto.FacebookDTO;
import com.bazlur.screening.dto.SignupForm;
import com.bazlur.screening.repository.UserRepository;
import com.bazlur.screening.utils.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.encoding.MessageDigestPasswordEncoder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * @author Bazlur Rahman Rokon
 * @since 11/28/16.
 */
@Service
@Transactional
public class SignupServiceImpl implements SignupService {
	private static final Logger log = LoggerFactory.getLogger(SignupService.class);

	@Autowired
	private ConnectionRepository connectionRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private MessageDigestPasswordEncoder messageDigestPasswordEncoder;

	@Override
	public Optional<User> findByEmail(String email) {

		return userRepository.findOneByEmail(email);
	}

	@Override
	public User createNewUser(SignupForm signupForm) {
		String salt = StringUtils.generateRandomString(16);

		User user = User.builder()
			.email(signupForm.getEmail())
			.fullName(signupForm.getFullName())
			.salt(salt)
			.password(messageDigestPasswordEncoder.encodePassword(signupForm.getPassword(), salt))
			.deleted(false)
			.accountNonExpired(true)
			.accountNonLocked(true)
			.enabled(true)
			.credentialsNonExpired(true)
			.build();

		Set<UserRole> userRoles = createUserRoles(user);
		user.setUserRoles(userRoles);

		return userRepository.save(user);
	}

	@Override
	public void loginOrCreateFacebookUser(FacebookDTO dto) {
		Optional<User> userOptional = userRepository.findByFacebookId(dto.getId());

		User user = userOptional
			.map(u -> u)
			.orElseGet(() -> {
				User build = User.builder()
					.fullName(dto.getDisplayName())
					.email(dto.getEmail())
					.password(UUID.randomUUID().toString())
					.salt(StringUtils.generateRandomString(6))
					.facebookId(dto.getId())
					.facebookImage(dto.getImageURL())
					.deleted(false)
					.accountNonExpired(true)
					.accountNonLocked(true)
					.enabled(true)
					.credentialsNonExpired(true)
					.build();

				Set<UserRole> userRoles = createUserRoles(build);
				build.setUserRoles(userRoles);

				return userRepository.save(build);
			});

		Authentication authentication = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());

		//not neccesary any more
		Connection<Facebook> connection = connectionRepository.findPrimaryConnection(Facebook.class);
		connectionRepository.removeConnection(connection.getKey());

		SecurityContextHolder.clearContext();
		SecurityContextHolder.getContext().setAuthentication(authentication);
	}

	private Set<UserRole> createUserRoles(User user) {
		UserRole userRole = UserRole.builder().role(Role.ROLE_USER)
			.user(user)
			.build();

		return Collections.singletonList(userRole)
			.stream()
			.collect(Collectors.toSet());
	}
}
