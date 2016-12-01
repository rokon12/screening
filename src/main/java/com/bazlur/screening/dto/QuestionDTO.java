package com.bazlur.screening.dto;

import com.bazlur.screening.domain.Difficulty;
import com.bazlur.screening.domain.QuestionType;
import lombok.*;
import org.springframework.hateoas.core.Relation;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Bazlur Rahman Rokon
 * @since 12/1/16.
 */
@Data
@Builder
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@EqualsAndHashCode(of = {"name", "id"})
@Relation(value = "question", collectionRelation = "questions")
public class QuestionDTO {
    private Long id;
    private Difficulty difficulty;
    private QuestionType questionType;
    private Integer maxScore;
    private String name;
    private Integer allocatedTime; // in seconds
    private String description;
    private String additionalNotes; //Optional
    private boolean allowMultiChoice;
    @Singular
    private Set<String> tags = new HashSet<>();
    @Singular
    private Set<OptionDTO> options = new HashSet<>();
}
