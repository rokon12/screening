package com.bazlur.screening.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author Bazlur Rahman Rokon
 * @since 11/28/16.
 */
@Controller
public class LoginController {

	@GetMapping("login")
	public String login() {

		return "login";
	}
}
