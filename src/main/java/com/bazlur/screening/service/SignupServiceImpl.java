package com.bazlur.screening.service;

import com.bazlur.screening.domain.Role;
import com.bazlur.screening.domain.User;
import com.bazlur.screening.domain.UserRole;
import com.bazlur.screening.dto.SignupForm;
import com.bazlur.screening.repository.UserRepository;
import com.bazlur.screening.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.MessageDigestPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author Bazlur Rahman Rokon
 * @since 11/28/16.
 */
@Service
@Transactional
public class SignupServiceImpl implements SignupService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private MessageDigestPasswordEncoder messageDigestPasswordEncoder;

	@Override
	public Set<User> findByEmail(String email) {

		return userRepository.findByEmail(email);
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

		UserRole userRole = UserRole.builder().role(Role.ROLE_USER)
			.user(user)
			.build();

		Set<UserRole> userRoles = Collections.singletonList(userRole).stream().collect(Collectors.toSet());
		user.setUserRoles(userRoles);

		return userRepository.save(user);
	}
}
