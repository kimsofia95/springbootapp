package app.controller;

import app.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Controller
public class UserController {
	@GetMapping(value = "login")
	public String loginPage() {
		return "login";
	}
	@GetMapping(value = "user")
	public String UserPageId(ModelMap modelMap, @AuthenticationPrincipal User user) {
		modelMap.addAttribute("user", user);
		return "userPage";
	}
}