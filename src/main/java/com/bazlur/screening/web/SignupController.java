package com.bazlur.screening.web;

import com.bazlur.screening.domain.User;
import com.bazlur.screening.dto.SignupForm;
import com.bazlur.screening.service.SignupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.Set;

import static com.bazlur.screening.utils.StringUtils.isNotEmpty;
import static com.bazlur.screening.validation.ValidationUtils.addFieldError;

/**
 * @author Bazlur Rahman Rokon
 * @since 11/28/16.
 */
@Controller
public class SignupController {
	protected static final String MODEL_NAME_SIGNUP_DTO = "signupForm";
	protected static final String ERROR_CODE_EMAIL_EXIST = "Exist.signupForm.email";

	@Autowired
	private SignupService signupService;

	@GetMapping("signup")
	public String showSignUpForm(SignupForm signupForm) {

		return "signup";
	}

	@PostMapping("signup")
	public String completeSignUp(@Valid SignupForm signupForm, BindingResult result, RedirectAttributes redirectAttributes) {
		validate(signupForm, result);

		if (result.hasErrors()) {

			return "signup";
		}

		signupService.createNewUser(signupForm);
		redirectAttributes.addFlashAttribute("message", "Your sign up has been successful");

		return "redirect:/login";
	}

	private void validate(SignupForm signupForm, BindingResult result) {
		if (isNotEmpty(signupForm.getEmail())) {
			Set<User> users = signupService.findByEmail(signupForm.getEmail());

			if (!CollectionUtils.isEmpty(users)) {
				addFieldError(MODEL_NAME_SIGNUP_DTO, SignupForm.FIELD_NAME_EMAIL, signupForm.getEmail(),
					ERROR_CODE_EMAIL_EXIST, result);
			}
		}
	}
}
