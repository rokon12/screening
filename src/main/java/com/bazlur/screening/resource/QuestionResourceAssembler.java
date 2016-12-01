package com.bazlur.screening.resource;

import com.bazlur.screening.dto.QuestionDTO;
import com.bazlur.screening.rest.QuestionRestController;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import org.springframework.stereotype.Component;

/**
 * @author Bazlur Rahman Rokon
 * @since 12/1/16.
 */
@Component
public class QuestionResourceAssembler extends ResourceAssemblerSupport<QuestionDTO, QuestionResource> {

    public QuestionResourceAssembler() {
        super(QuestionRestController.class, QuestionResource.class);
    }

    @Override
    public QuestionResource toResource(QuestionDTO questionDTO) {
        return createResourceWithId(questionDTO.getId(), questionDTO);
    }

    @Override
    protected QuestionResource instantiateResource(QuestionDTO entity) {
        return new QuestionResource(entity);
    }
}
