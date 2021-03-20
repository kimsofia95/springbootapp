package app.web.controller;

import app.model.Role;
import app.model.User;
import app.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.transaction.Transactional;
import java.util.ArrayList;

@Controller
public class AdminController {
    @Autowired
    private UserDetailsService userService;
    @Autowired
    private AdminService adminService;

    @GetMapping("/admin")
    public String userList(Model model) {
        model.addAttribute("allUsers", adminService.allUsers());
        return "admin";
    }

    @RequestMapping("/admin/delete/{username}")
    public ModelAndView deleteUser(Model model, @PathVariable String username) {
        ModelAndView modelAndView = new ModelAndView();
        User user = (User) userService.loadUserByUsername(username);
        adminService.deleteUser(user);
        modelAndView.setViewName("redirect:/admin");
        return modelAndView;
    }

    @RequestMapping(value = "/admin/edit/{username}", method = RequestMethod.GET)
    public ModelAndView editPage(@PathVariable("username") String username) {
        User user = (User) userService.loadUserByUsername(username);
        ArrayList<Role> roles = (ArrayList<Role>) adminService.allRoles();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("editUser");
        modelAndView.addObject("user", user);
        modelAndView.addObject("allRoles", roles);
        return modelAndView;
    }

    @RequestMapping(value = "/admin/edit", method = RequestMethod.POST)
    public ModelAndView editUser(@ModelAttribute("user") User user) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/admin");
        adminService.ChangeUser(user);
        return modelAndView;
    }

    @RequestMapping(value = "admin/add", method = RequestMethod.POST)
    public ModelAndView addUser(@ModelAttribute("user") User user) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/admin");
        adminService.addUser(user);
        return modelAndView;
    }

    @RequestMapping(value = "admin/add", method = RequestMethod.GET)
    public ModelAndView addUser() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("editUser");
        return modelAndView;
    }
    @RequestMapping("/admin/{username}")
    public String getUser(@PathVariable("username") String username, Model model) {
        model.addAttribute("user", userService.loadUserByUsername(username));
        return "admin";
    }
}