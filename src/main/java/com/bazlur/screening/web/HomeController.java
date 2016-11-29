package com.bazlur.screening.web;

import com.bazlur.screening.domain.User;
import com.bazlur.screening.repository.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.RememberMeServices;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.Principal;
import java.util.Optional;

/**
 * @author Bazlur Rahman Rokon
 * @since 11/28/16.
 */
@Controller
public class HomeController {
	private UserRepository userRepository;
	private RememberMeServices rememberMeServices;

	@Inject
	public HomeController(UserRepository userRepository, RememberMeServices rememberMeServices) {
		this.userRepository = userRepository;
		this.rememberMeServices = rememberMeServices;
	}

	@RequestMapping(value = {"", "/", "/home"}, method = RequestMethod.GET)
	public String index(Model model, Principal principal, HttpServletRequest request, HttpServletResponse response) {

		if (null != principal) {
			Optional<User> user = this.userRepository.findOneByEmail(principal.getName());
			if (user.isPresent()) {
				model.addAttribute("user", user.get());
			}

			//You don't need to call this on every page, just the one you get redirected to should suffice
			//Mostly moved here because of the interceptor layout of a ConnectInterceptor, which does not support HttpServletResponses
			//Move where-ever you want this! (though check for a principal)
			rememberMeServices.loginSuccess(request, response, SecurityContextHolder.getContext().getAuthentication());
		}

		return "home/index";
	}
}
