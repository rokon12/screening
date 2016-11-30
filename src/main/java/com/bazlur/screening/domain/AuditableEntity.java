package com.bazlur.screening.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;

/**
 * @author Bazlur Rahman Rokon
 * @since 11/28/16.
 */
@Data
@ToString
@EqualsAndHashCode(callSuper = true)
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class AuditableEntity<ID> extends BaseEntity<ID> {

	@CreatedBy
	@Basic(optional = false)
	@Column(nullable = false, length = 200)
	private String createdBy;

	@LastModifiedBy
	@Basic(optional = false)
	@Column(nullable = false, length = 200)
	private String lastUpdatedBy;
}
