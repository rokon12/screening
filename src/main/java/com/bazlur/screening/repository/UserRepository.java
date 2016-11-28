package com.bazlur.screening.repository;

import com.bazlur.screening.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

/**
 * @author Bazlur Rahman Rokon
 * @since 11/28/16.
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	Set<User> findByEmail(String email);

	Optional<User> findOneByEmail(String email);

	Optional<User> findByFacebookId(String id);
}
