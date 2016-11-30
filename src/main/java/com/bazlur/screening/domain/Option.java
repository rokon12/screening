package com.bazlur.screening.domain;

import lombok.Builder;
import lombok.Data;

import javax.persistence.*;

/**
 * @author Bazlur Rahman Rokon
 * @since 12/1/16.
 */
@Entity
@Data
@Builder
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
