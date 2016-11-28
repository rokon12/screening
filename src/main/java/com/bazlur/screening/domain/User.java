package com.bazlur.screening.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.CollectionUtils;

import javax.persistence.*;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author Bazlur Rahman Rokon
 * @since 11/28/16.
 */
@Entity
@Data
@Builder
@ToString
@EqualsAndHashCode(callSuper = false, exclude = "userRoles")
@NoArgsConstructor
@AllArgsConstructor
public class User extends BaseEntity<Long> implements UserDetails, NonDeletable {
	public static final String PASSWORD = "password";
	public static final String USERNAME = "username";
	public static final String EMAIL = "email";
	public static final String FULL_NAME = "full_name";
	public static final String FACEBOOK_ID = "facebook_id";
	public static final String FACEBOOK_IMAGE = "facebook_image";

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = PASSWORD, length = 500)
	private String password;

	@Column(length = 16)
	private String salt;

	@Column(name = EMAIL, length = 80, unique = true)
	private String email;

	@Column(name = FULL_NAME, length = 200)
	private String fullName;

	@JsonIgnore
	@OneToMany(mappedBy = "user", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private Set<UserRole> userRoles = new HashSet<>();

	private Boolean enabled; //TODO by default it should be false, after email confirmation this should be enabled
	private Boolean credentialsNonExpired;
	private Boolean accountNonExpired;
	private Boolean accountNonLocked;
	private Boolean deleted;

	@Transient
	private List<SimpleGrantedAuthority> simpleGrantedAuthorityList;

	/**
	 * FACEBOOK ELEMENTS
	 **/
	@Column(name = FACEBOOK_ID, length = 500)
	private String facebookId;

	@Column(name = FACEBOOK_IMAGE, length = 500)
	private String facebookImage;

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		if (simpleGrantedAuthorityList == null && !CollectionUtils.isEmpty(getUserRoles())) {
			simpleGrantedAuthorityList = new ArrayList<>();

			simpleGrantedAuthorityList
				.addAll(getUserRoles()
					.stream()
					.map(userRole -> new SimpleGrantedAuthority(userRole.getRole().name()))
					.collect(Collectors.toList()));
		}

		return simpleGrantedAuthorityList;
	}

	@Override
	public String getUsername() {
		return email;
	}

	@Override
	public boolean isAccountNonExpired() {
		return accountNonExpired;
	}

	@Override
	public boolean isAccountNonLocked() {
		return accountNonLocked;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return credentialsNonExpired;
	}

	@Override
	public boolean isEnabled() {
		return enabled;
	}

	@Override
	public Boolean isDeleted() {

		return deleted;
	}
}
