package com.bazlur.screening.repository;

import com.bazlur.screening.domain.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;


import java.util.Optional;
import java.util.UUID;

/**
 * @author Bazlur Rahman Rokon
 * @since 11/29/16.
 */
@RunWith(SpringRunner.class)
@DataJpaTest
public class UserRepositoryTest {
	private static final String NAME = "Bazlur Rahman Rokon";
	private static final String EMAIL = "admin@bazlur.com";

	@Autowired
	private TestEntityManager entityManager;

	@Autowired
	private UserRepository userRepository;

	@Test
	public void findOneByEmail_userExistWithEmail_userReturned() throws Exception {
		entityManager.persist(createNewUser());
		Optional<User> userOptional = userRepository.findOneByEmail(EMAIL);

		Assert.assertTrue(userOptional.isPresent());
		Assert.assertEquals(userOptional.get().getEmail(), EMAIL);
	}

	@Test
	public void findOneByEmail_userNotExist_emptyUserReturned() {
		Optional<User> userOptional = userRepository.findOneByEmail("dummy@bazlur.com");

		Assert.assertFalse(userOptional.isPresent());
	}

	@Test
	public void findByFacebookId_userExistWithFacebookId_userReturned() throws Exception {
		User newUser = createNewUser();
		String facebookId = UUID.randomUUID().toString();
		newUser.setFacebookId(facebookId);
		entityManager.persist(newUser);

		Optional<User> userOptional = userRepository.findByFacebookId(facebookId);

		Assert.assertTrue(userOptional.isPresent());
		Assert.assertEquals(userOptional.get().getFacebookId(), facebookId);
	}

	private User createNewUser() {

		return User.builder()
			.fullName(NAME)
			.email(EMAIL)
			.build();
	}
}