package com.bazlur.screening.service;

import com.bazlur.screening.domain.Question;
import com.bazlur.screening.dto.QuestionDTO;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @author Bazlur Rahman Rokon
 * @since 12/1/16.
 */
public interface QuestionService {

	List<Question> findAllQuestions();

	PageImpl<QuestionDTO> findAll(Pageable pageable);

	QuestionDTO findOne(Long id);
}
