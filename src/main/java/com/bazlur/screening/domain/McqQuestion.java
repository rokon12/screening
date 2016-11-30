package com.bazlur.screening.domain;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.Set;

/**
 * @author Bazlur Rahman Rokon
 * @since 12/1/16.
 */
@Data
@Entity
@Builder
@ToString
@EqualsAndHashCode(callSuper = true)
@DiscriminatorValue("MCQ")
public class McqQuestion extends Question {
	private boolean allowMultiChoice;

	@OneToMany
	private Set<Option> options;
}
