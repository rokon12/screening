package com.bazlur.screening.domain;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
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
@NoArgsConstructor
public abstract class Question extends AuditableEntity<Long> {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(length = 10)
	@Enumerated(EnumType.STRING)
	private Difficulty difficulty;

	private Integer maxScore;

	@Column(length = 200)
	private String name;

	private Integer allocatedTime; // in seconds

	@Column(columnDefinition = "text")
	private String description;

	@Column(columnDefinition = "text")
	private String additionalNotes; //Optional

	@ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
	private Set<Tag> tags = new HashSet<>();

	@Override
	public Long getId() {
		return id;
	}
}
