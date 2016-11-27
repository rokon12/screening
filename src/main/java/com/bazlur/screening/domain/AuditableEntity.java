package com.bazlur.screening.domain;

import lombok.*;

import javax.persistence.MappedSuperclass;

/**
 * @author Bazlur Rahman Rokon
 * @since 11/28/16.
 */
@Data
@ToString
@EqualsAndHashCode(callSuper = true)
@MappedSuperclass
public abstract class AuditableEntity<ID> extends BaseEntity<ID> {

	private User lastCreated;
	private User lastUpdated;
}
