package app.web.controller;

import app.model.Role;
import app.model.User;
import app.service.RoleService;
import app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
public class RestAdminController {
    private final UserService userService;
    private final RoleService roleService;

    @Autowired
    public RestAdminController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @PostMapping(value = "/admin")
    public void addUser(@RequestBody User user, @RequestParam(value = "roles", required = false) int[] roles) {
        Set<Role> rol = new HashSet<>();
        for (int role_id: roles) {
            rol.add(userService.showRole(role_id));
        }
        user.setRoles(rol);
        userService.save(user);
    }

    @PostMapping("/admin/delete/{userid}")
    public void deleteUser(Model model, @PathVariable int userid) {
        userService.delete(userid);
    }

    @PostMapping(value = "/admin/edit/{userid}")
    public void editUser(@RequestBody User user, @PathVariable int userid) {
        userService.save(user);
    }

    @GetMapping("/api/users")
    public List<User> allUsers() {
        return userService.getAllUsers();
    }
    @GetMapping(value = "/api/user")
    public User UserPageId(@AuthenticationPrincipal User user) {
        return user;
    }
}
