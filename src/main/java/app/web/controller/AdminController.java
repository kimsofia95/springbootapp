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
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

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
    public String deleteUser(Model model, @PathVariable int userid) {
        userService.delete(userid);
        return "redirect:/admin";
    }

    @GetMapping(value = "/admin/edit/{userid}")
    public String editPage(ModelMap model, @PathVariable("userid") int userid) {
        User user = userService.show(userid);
        ArrayList<Role> roles = (ArrayList<Role>) roleService.getAllRoles();
        model.addAttribute("user", user);
        model.addAttribute("allRoles", roles);
        return "editUser";
    }

    @PostMapping(value = "/admin/edit")
    public String editUser(@ModelAttribute("user") User user, @RequestParam(value = "roles", required = false) String[] roles) {
        userService.update(user, roles);
        return "redirect:/admin";
    }

    @PostMapping(value = "/admin/add")
    public String addUser(@ModelAttribute("user") User user,@RequestParam(value = "roles", required = false) int[] roles) {
        Set<Role> rol = new HashSet<>();
        for (int role_id: roles) {
            rol.add(userService.showRole(role_id));
        }
        user.setRoles(rol);
        userService.save(user);
        return "redirect:/admin";
    }

    @GetMapping(value = "admin/add")
    public String addUser(ModelMap model) {
        model.addAttribute("allRoles", roleService.getAllRoles());
        model.addAttribute("addUser", new User());
        return "editUser";
    }
    @GetMapping("/admin/{userid}")
    public String getUser(@PathVariable("userid") int userid, Model model) {
        model.addAttribute("user", userService.show(userid));
        return "admin";
    }
}