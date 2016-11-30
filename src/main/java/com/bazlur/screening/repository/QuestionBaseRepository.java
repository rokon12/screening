package com.bazlur.screening.repository;

import com.bazlur.screening.domain.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

/**
 * @author Bazlur Rahman Rokon
 * @since 12/1/16.
 */
@NoRepositoryBean
public interface QuestionBaseRepository<T extends Question> extends JpaRepository<T, Long> {

}
