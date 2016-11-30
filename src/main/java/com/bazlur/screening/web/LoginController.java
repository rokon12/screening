package com.bazlur.screening.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.security.Principal;

/**
 * @author Bazlur Rahman Rokon
 * @since 11/28/16.
 */
@Controller
public class LoginController {
	private static final Logger LOGGER = LoggerFactory.getLogger(LoginController.class);

	@GetMapping("login")
	public String login() {
		LOGGER.info("Returning login page");

		return "login";
	}

	@GetMapping(value = { "/logout" })
	public String logout(HttpServletRequest request, Model model, Principal principal) throws ServletException {
		if (null != principal) {
			SecurityContextHolder.clearContext();

			HttpSession session = request.getSession(false);
			if (session != null) {
				session.invalidate();
			}

			request.logout();
		}

		return "redirect:/";
	}
}
