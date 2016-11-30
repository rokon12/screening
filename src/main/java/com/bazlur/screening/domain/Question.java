package com.bazlur.screening.domain;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.util.Set;

import static javax.persistence.InheritanceType.JOINED;

/**
 * @author Bazlur Rahman Rokon
 * @since 11/30/16.
 */
@Entity
@Data
@ToString
@EqualsAndHashCode(callSuper = true)
@Inheritance(strategy = JOINED)
@DiscriminatorColumn(name = "QUESTION_TYPE")
public abstract class Question extends AuditableEntity<Long> {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(length = 10)
	@Enumerated(EnumType.STRING)
	private Difficulty difficulty;

	private Long maxScore;

	@Column(length = 200)
	private String name;

	private Integer allocatedTime; // in seconds

	@Column(columnDefinition = "text")
	private String description;

	@Column(columnDefinition = "text")
	private String additionalNotes; //Optional

	@ManyToMany
	private Set<Tag> tags;

	@Override
	public Long getId() {
		return id;
	}
}
