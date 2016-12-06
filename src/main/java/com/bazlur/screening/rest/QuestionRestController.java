package com.bazlur.screening.rest;

import com.bazlur.screening.dto.QuestionDTO;
import com.bazlur.screening.resource.QuestionResource;
import com.bazlur.screening.resource.QuestionResourceAssembler;
import com.bazlur.screening.service.QuestionService;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.ExposesResourceFor;
import org.springframework.hateoas.MediaTypes;
import org.springframework.hateoas.PagedResources;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;

/**
 * @author Bazlur Rahman Rokon
 * @since 12/1/16.
 */
@RestController
@RequestMapping(value = "/api/v1/questions", produces = {MediaTypes.HAL_JSON_VALUE})
@ExposesResourceFor(QuestionDTO.class)
public class QuestionRestController {

    private final QuestionResourceAssembler questionResourceAssembler;
    private final QuestionService questionService;

    @Inject
    public QuestionRestController(QuestionResourceAssembler questionResourceAssembler, QuestionService questionService) {
        this.questionResourceAssembler = questionResourceAssembler;
        this.questionService = questionService;
    }

    @GetMapping
    public PagedResources<QuestionResource> findAll(Pageable pageable, PagedResourcesAssembler<QuestionDTO> pagedResourcesAssembler) {
        PageImpl<QuestionDTO> questionDTOs = questionService.findAll(pageable);

        return pagedResourcesAssembler.toResource(questionDTOs, questionResourceAssembler);
    }

    @GetMapping("/{id}")
    public QuestionResource findOne(@PathVariable("id") Long id) {

        return questionResourceAssembler.toResource(questionService.findOne(id));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteQuestion(@PathVariable Long id){
	    questionService.delete(id);
    }
}
