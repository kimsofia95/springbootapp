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
import java.awt.*;
import java.util.ArrayList;
import java.util.Set;

@Controller
public class AdminController {
    private final UserDetailsService userService;
    private final AdminService adminService;

    @Autowired
    public AdminController(UserDetailsService userService, AdminService adminService) {
        this.userService = userService;
        this.adminService = adminService;
    }

    @GetMapping("/admin")
    public String userList(Model model) {
        model.addAttribute("allUsers", adminService.allUsers());
        return "admin";
    }

    @GetMapping("/admin/delete/{username}")
    public ModelAndView deleteUser(Model model, @PathVariable String username) {
        ModelAndView modelAndView = new ModelAndView();
        User user = (User) userService.loadUserByUsername(username);
        adminService.deleteUser(user);
        modelAndView.setViewName("redirect:/admin");
        return modelAndView;
    }

    @GetMapping(value = "/admin/edit/{username}")
    public ModelAndView editPage(@PathVariable("username") String username) {
        User user = (User) userService.loadUserByUsername(username);
        ArrayList<Role> roles = (ArrayList<Role>) adminService.allRoles();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("editUser");
        modelAndView.addObject("user", user);
        modelAndView.addObject("allRoles", roles);
        return modelAndView;
    }

    @PostMapping(value = "/admin/edit")
    public ModelAndView editUser(@ModelAttribute("user") User user, @RequestParam(value = "roles", required = false) String[] roles) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/admin");
        adminService.ChangeUser(user, roles);
        return modelAndView;
    }

    @PostMapping(value = "admin/add")
    public ModelAndView addUser(@ModelAttribute("user") User user,@RequestParam(value = "roles", required = false) String[] roles) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/admin");
        adminService.addUser(user, roles);
        return modelAndView;
    }

    @GetMapping(value = "admin/add")
    public ModelAndView addUser() {
        ModelAndView modelAndView = new ModelAndView();
        ArrayList<Role> roles = (ArrayList<Role>) adminService.allRoles();
        modelAndView.setViewName("editUser");
        modelAndView.addObject("allRoles", roles);
        return modelAndView;
    }
    @GetMapping("/admin/{username}")
    public String getUser(@PathVariable("username") String username, Model model) {
        model.addAttribute("user", userService.loadUserByUsername(username));
        return "admin";
    }
}