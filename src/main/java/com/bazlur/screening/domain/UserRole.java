package com.bazlur.screening.domain;

import lombok.*;

import javax.persistence.*;

/**
 * @author Bazlur Rahman Rokon
 * @since 11/28/16.
 */
@Entity
@Data
@Builder
@ToString
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class UserRole extends BaseEntity<Long>{

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "user_id", nullable = false)
	private User user;

	@Enumerated(EnumType.STRING)
	private Role role;

	@Override
	public Long getId() {
		return id;
	}
}
