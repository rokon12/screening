package com.bazlur.screening.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * @author Bazlur Rahman Rokon
 * @since 11/30/16.
 */
@Entity
@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
public class Tag extends AuditableEntity<Long> {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(length = 100)
	private String name;

	public Tag() {
	}

	public Tag(String name) {
		this.name = name;
	}

	@Override
	public Long getId() {
		return id;
	}

	@Override
	public String toString() {

		return name;
	}
}
