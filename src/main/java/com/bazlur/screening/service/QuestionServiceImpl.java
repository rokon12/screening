package com.bazlur.screening.service;

import com.bazlur.screening.domain.McqQuestion;
import com.bazlur.screening.domain.Question;
import com.bazlur.screening.domain.QuestionType;
import com.bazlur.screening.domain.SubjectiveQuestion;
import com.bazlur.screening.dto.QuestionDTO;
import com.bazlur.screening.exception.ResourceDoesNotExistException;
import com.bazlur.screening.repository.QuestionRepository;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
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
    private final static Logger log = LoggerFactory.getLogger(QuestionServiceImpl.class);

    @Inject
    private QuestionRepository questionRepository;

    @Inject
    private ModelMapper modelMapper;

    @Override
    public List<Question> findAllQuestions() {

        return questionRepository.findAll();
    }

    @Override
    public PageImpl<QuestionDTO> findAll(Pageable pageable) {
        long total = questionRepository.count();
        List<QuestionDTO> questionDTOList = questionRepository.findAll(pageable).map(this::convertToDto).getContent();

        return new PageImpl<>(questionDTOList, pageable, total);
    }

    @Override
    public QuestionDTO findOne(Long id) {

        return questionRepository.findOneById(id)
                .map(this::convertToDto)
                .orElseThrow(ResourceDoesNotExistException::new);
    }


    private QuestionDTO convertToDto(Question question) {

        QuestionDTO questionDTO = modelMapper.map(question, QuestionDTO.class);
        if (question instanceof McqQuestion) {
            log.info("question is instance of McqQuestion");
        } else if (question instanceof SubjectiveQuestion) {
            questionDTO.setQuestionType(QuestionType.SUBJECTIVE);
            log.info("question is instance of Subjective");
        }

        return questionDTO;
    }
}
