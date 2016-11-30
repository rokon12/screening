package com.bazlur.screening.domain;

import lombok.*;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Bazlur Rahman Rokon
 * @since 12/1/16.
 */
@Data
@Entity
@ToString
@EqualsAndHashCode(callSuper = true)
@DiscriminatorValue("MCQ")
@NoArgsConstructor
public class McqQuestion extends Question {
	private boolean allowMultiChoice;

	@OneToMany
	private Set<Option> options = new HashSet<>();
}
