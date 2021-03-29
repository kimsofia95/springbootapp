package app.web.controller;

import app.model.Role;
import app.model.User;
import app.service.RoleService;
import app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.NoSuchElementException;
import java.util.Set;

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
    public String deleteUser(Model model, @PathVariable int userid) {
        userService.delete(userid);
        return "redirect:/admin";
    }

    @PostMapping(value = "/admin/edit/{userid}")
    public  ResponseEntity<?> editUser(@PathVariable("userid") int userid, @RequestBody User user) {
        try {
            User existUser = userService.show(userid);
            userService.save(user);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
