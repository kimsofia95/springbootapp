package app.controller;

import app.model.Role;
import app.model.User;
import app.service.RoleService;
import app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@Controller
@RequestMapping("/admin")
public class AdminController {
    private final UserService userService;
    private final RoleService roleService;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public AdminController(UserService userService, RoleService roleService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.roleService = roleService;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping
    public String adminPage(ModelMap model, @AuthenticationPrincipal User user) {
        ArrayList<Role> roles = (ArrayList<Role>) roleService.getAllRoles();
        model.addAttribute("allUsers", userService.getAllUsers());
        model.addAttribute("user", user);
        model.addAttribute("addUser", new User());
        model.addAttribute("allRoles", roles);
        return "admin";
    }

    @PutMapping(value = "edit")
    public String editUser(@ModelAttribute("user") User user) {
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        userService.save(user);
        return "redirect:/admin";
    }

    @PostMapping
    public String addUser(@ModelAttribute("user") User user) {
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        userService.save(user);
        return "redirect:/admin";
    }

    @PostMapping("delete/{userid}")
    public String deleteUser(@PathVariable int userid) {
        userService.delete(userid);
        return "redirect:/admin";
    }

    @PostMapping(value = "edit/{userid}")
    public String editUser(@PathVariable("userid") int userid, @ModelAttribute(value = "submit") User user) {
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        userService.save(user);
        return "redirect:/admin";
    }

    @GetMapping("/admin/{userid}")
    public String getUser(@PathVariable("userid") int userid, Model model) {
        model.addAttribute("user", userService.show(userid));
        return "admin";
    }
}