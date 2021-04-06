package app.controller;

import app.model.Role;
import app.model.User;
import app.service.RoleService;
import app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

@Controller
@RequestMapping("/admin")
public class AdminController {
    private final UserService userService;
    private final RoleService roleService;

    @Autowired
    public AdminController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping
    public String userList(Model model) {
        model.addAttribute("allUsers", userService.getAllUsers());
        return "admin";
    }

    @GetMapping(value = "delete/{userid}")
    public String deleteUser(Model model, @PathVariable int userid) {
        userService.delete(userid);
        return "redirect:/admin";
    }

    @GetMapping(value = "edit/{userid}")
    public String editPage(ModelMap model, @PathVariable("userid") int userid) {
        User user = userService.show(userid);
        ArrayList<Role> roles = (ArrayList<Role>) roleService.getAllRoles();
        model.addAttribute("user", user);
        model.addAttribute("allRoles", roles);
        return "editUser";
    }

    @PutMapping(value = "edit")
    public String editUser(@ModelAttribute("user") User user) {
        userService.save(user);
        return "redirect:/admin";
    }

    @PostMapping(value = "add")
    public String addUser(@ModelAttribute("user") User user) {
        userService.save(user);
        return "redirect:/admin";
    }

    @GetMapping(value = "add")
    public String addUser(ModelMap model) {
        model.addAttribute("allRoles", roleService.getAllRoles());
        model.addAttribute("addUser", new User());
        return "editUser";
    }
}