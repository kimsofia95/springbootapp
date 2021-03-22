package app.web.controller;

import app.model.User;
import app.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/")
public class UserController {
	private final UserDetailsService userDetailsService;
	private final AdminService adminService;

	@Autowired
	public UserController(UserDetailsService userDetailsService, AdminService adminService) {
		this.userDetailsService = userDetailsService;
		this.adminService = adminService;
	}

	@Transactional
	@GetMapping(value = "hello")
	public String printWelcome(ModelMap model) {
		List<String> messages = new ArrayList<>();
		messages.add("Hello!");
		messages.add("I'm Spring MVC-SECURITY application");
		messages.add("5.2.0 version by sep'19 ");
		model.addAttribute("messages", messages);
		return "hello";
	}

	@Transactional
    @GetMapping(value = "login")
    public ModelAndView loginPage(ModelMap modelMap) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("login");
        return modelAndView;
    }

	@Transactional
	@GetMapping(value = "user")
	public ModelAndView UserPageId(ModelMap modelMap, @AuthenticationPrincipal User user) {
		modelMap.addAttribute("user", user);
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("user");
		modelAndView.addObject(user);
		return modelAndView;
	}

}