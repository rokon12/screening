package com.bazlur.screening.dto;

import com.bazlur.screening.validation.PasswordNotEqual;
import lombok.*;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.Size;

/**
 * @author Bazlur Rahman Rokon
 * @since 11/28/16.
 */
@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@PasswordNotEqual(passwordFieldName = "password", passwordConfirmedFieldName = "passwordConfirmed")
public class SignupForm {
	public static final String FIELD_NAME_EMAIL = "email";

	private Long id;

	@NotEmpty
	@Size(min = 4, max = 120)
	private String fullName;

	@NotEmpty
	@Email
	private String email;

	@NotEmpty
	@Size(min = 6, max = 32)
	private String password;

	@NotEmpty
	@Size(min = 6, max = 32)
	private String passwordConfirmed;

	@AssertTrue(message = "You must agree to the terms")
	private boolean agreed;
}
