package app.controller;

import app.model.User;
import app.service.RoleService;
import app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api")
public class RestAdminController {
    private final UserService userService;
    private final RoleService roleService;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public RestAdminController(UserService userService, RoleService roleService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.roleService = roleService;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping(value = "/add")
    public void addUser(@RequestBody User user) {
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        userService.save(user);
    }

    @PostMapping("/delete")
    public void deleteUser(@RequestBody User user) {
        userService.delete(user.getId());
    }

    @PutMapping(value = "/edit")
    public void editUser(@RequestBody User user) {
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        userService.save(user);
    }

    @GetMapping("/users")
    public List<User> allUsers() {
        return userService.getAllUsers();
    }
    @GetMapping(value = "/user")
    public User UserPageId(@AuthenticationPrincipal User user) {
        return user;
    }

    @GetMapping(value = "/users/{userid}")
    public Optional<User> userGetById(@PathVariable int userid) {
        return userService.findById(userid);
    }
}
