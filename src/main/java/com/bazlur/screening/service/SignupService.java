package com.bazlur.screening.service;

import com.bazlur.screening.domain.User;
import com.bazlur.screening.dto.SignupForm;

import java.util.Set;

/**
 * @author Bazlur Rahman Rokon
 * @since 11/28/16.
 */
public interface SignupService {

	Set<User> findByEmail(String email);

	User createNewUser(SignupForm signupForm);
}
