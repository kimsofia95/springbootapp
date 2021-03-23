package app.web.controller;

import app.model.Role;
import app.model.User;
import app.service.RoleService;
import app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;

@Controller
public class AdminController {
    private final UserService userService;
    private final RoleService roleService;


    @Autowired
    public AdminController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping("/admin")
    public String userList(Model model) {
        model.addAttribute("allUsers", userService.getAllUsers());
        return "admin";
    }

    @GetMapping("/admin/delete/{userid}")
    public ModelAndView deleteUser(Model model, @PathVariable int userid) {
        ModelAndView modelAndView = new ModelAndView();
        userService.delete(userid);
        modelAndView.setViewName("redirect:/admin");
        return modelAndView;
    }

    @GetMapping(value = "/admin/edit/{userid}")
    public ModelAndView editPage(@PathVariable("userid") int userid) {
        User user = userService.show(userid);
        ArrayList<Role> roles = (ArrayList<Role>) roleService.getAllRoles();
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
        userService.update(user, roles);
        return modelAndView;
    }

    @PostMapping(value = "admin/add")
    public ModelAndView addUser(@ModelAttribute("user") User user,@RequestParam(value = "roles", required = false) String[] roles) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/admin");
        userService.save(user);
        return modelAndView;
    }

    @GetMapping(value = "admin/add")
    public ModelAndView addUser() {
        ModelAndView modelAndView = new ModelAndView();
        ArrayList<Role> roles = (ArrayList<Role>) roleService.getAllRoles();
        modelAndView.setViewName("editUser");
        modelAndView.addObject("allRoles", roles);
        return modelAndView;
    }
    @GetMapping("/admin/{userid}")
    public String getUser(@PathVariable("userid") int userid, Model model) {
        model.addAttribute("user", userService.show(userid));
        return "admin";
    }
}