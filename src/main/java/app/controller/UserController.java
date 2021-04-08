package app.controller;

import app.model.User;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class UserController {

	@GetMapping(value = "/user")
	public String UserPageId(Model model, @AuthenticationPrincipal User user) {
		model.addAttribute("user", user);
		return "userPage";
	}


	@GetMapping(value = "login")
	public String loginPage() {
		return "login";
	}
}