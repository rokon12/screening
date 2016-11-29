package com.bazlur.screening.service;

import com.bazlur.screening.domain.User;
import com.bazlur.screening.dto.FacebookDTO;
import com.bazlur.screening.dto.SignupForm;

import java.util.Optional;

/**
 * @author Bazlur Rahman Rokon
 * @since 11/28/16.
 */
public interface SignupService {

	Optional<User> findByEmail(String email);

	User createNewUser(SignupForm signupForm);

	void loginOrCreateFacebookUser(FacebookDTO dto);
}
