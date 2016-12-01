package com.bazlur.screening.repository;

import com.bazlur.screening.domain.Question;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author Bazlur Rahman Rokon
 * @since 12/1/16.
 */
@Repository
public interface QuestionRepository extends QuestionBaseRepository<Question> {
}
