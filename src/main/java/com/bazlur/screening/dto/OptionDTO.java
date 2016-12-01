package com.bazlur.screening.dto;

import lombok.*;

/**
 * @author Bazlur Rahman Rokon
 * @since 12/1/16.
 */
@Data
@Builder
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@EqualsAndHashCode(of = {"name", "correct"})
public class OptionDTO {
    private Long id;
    private String name;
    private Boolean correct;
}
