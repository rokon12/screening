package com.bazlur.screening.domain;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author Bazlur Rahman Rokon
 * @since 11/28/16.
 */
@Data
@MappedSuperclass
public abstract class BaseEntity<ID> implements Serializable {

	@Version
	private Long version;

	@Column(name = "created_date", nullable = false)
	private LocalDateTime createdDate;

	@Column(name = "last_modified_date", nullable = false)
	private LocalDateTime lastModifiedDate;

	public abstract ID getId();

	@PrePersist
	public void prePersist() {
		this.createdDate = LocalDateTime.now();
		this.lastModifiedDate = LocalDateTime.now();
	}

	@PreUpdate
	public void preUpdate() {
		this.lastModifiedDate = LocalDateTime.now();
	}

}
