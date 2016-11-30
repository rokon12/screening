package com.bazlur.screening.domain;

import lombok.Data;

import javax.persistence.*;

/**
 * @author Bazlur Rahman Rokon
 * @since 11/30/16.
 */
@Entity
@Data
public class Tag extends AuditableEntity<Long> {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(length = 100)
	private String name;

	@Override
	public Long getId() {
		return id;
	}
}
