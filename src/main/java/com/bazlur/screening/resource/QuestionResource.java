package com.bazlur.screening.resource;

import com.bazlur.screening.dto.QuestionDTO;
import org.springframework.hateoas.Resource;

/**
 * @author Bazlur Rahman Rokon
 * @since 12/1/16.
 */
public class QuestionResource extends Resource<QuestionDTO> {

    public QuestionResource(QuestionDTO content) {
        super(content);
    }
}
