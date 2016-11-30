package com.bazlur.screening.domain;

import lombok.*;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * @author Bazlur Rahman Rokon
 * @since 12/1/16.
 */
@Data
@Entity
@ToString
@EqualsAndHashCode(callSuper = true)
@DiscriminatorValue("SUBJECTIVE")
@NoArgsConstructor
public class SubjectiveQuestion extends Question {
}
