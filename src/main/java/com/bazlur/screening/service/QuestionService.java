package com.bazlur.screening.service;

import com.bazlur.screening.domain.Question;

import java.util.List;

/**
 * @author Bazlur Rahman Rokon
 * @since 12/1/16.
 */
public interface QuestionService {

	List<Question> findAllQuestions();
}
