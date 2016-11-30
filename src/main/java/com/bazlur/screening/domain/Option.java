package com.bazlur.screening.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * @author Bazlur Rahman Rokon
 * @since 12/1/16.
 */
@Entity
@Getter
@Setter
@Builder
@Table(name = "QUESTION_OPTIONS")
public class Option extends BaseEntity<Long> {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(columnDefinition = "text")
	private String name;

	private Boolean correct;

	@Override
	public Long getId() {
		return id;
	}
}
