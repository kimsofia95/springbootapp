package app.web.controller;

import app.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

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

    @PostMapping("/admin")
    public String  deleteUser(@RequestParam(required = true, defaultValue = "" ) Long userId,
                              @RequestParam(required = true, defaultValue = "" ) String action,
                              Model model) {
        if (action.equals("delete")){
            adminService.deleteUser(userId);
        }
        return "admin";
    }

    @GetMapping("/admin/gt/{username}")
    public String getUser(@PathVariable("username") String username, Model model) {
        model.addAttribute("allUsers", userService.loadUserByUsername(username));
        return "admin";
    }
}