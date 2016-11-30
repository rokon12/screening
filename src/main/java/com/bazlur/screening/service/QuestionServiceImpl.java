package com.bazlur.screening.service;

import com.bazlur.screening.domain.Question;
import com.bazlur.screening.repository.QuestionRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.util.List;

/**
 * @author Bazlur Rahman Rokon
 * @since 12/1/16.
 */
@Service
@Transactional
public class QuestionServiceImpl implements QuestionService {

	@Inject
	private QuestionRepository questionRepository;

	@Override
	public List<Question> findAllQuestions() {

		return questionRepository.findAll();
	}
}
