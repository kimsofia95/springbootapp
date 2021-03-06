package app.service;

import app.model.Role;
import app.model.User;
import app.repository.RoleRepository;
import app.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class InitServiceImpl implements InitService{
    
    @Override
    public User getDefaultUser() {
        Set<Role> roles = new HashSet<>();
        Role roleAdmin = new Role(1, "ROLE_ADMIN");
        Role roleUser = new Role(2, "ROLE_USER");
        roles.add(roleAdmin);
        roles.add(roleUser);
        return new User(1, "admin", "admin", roles);
    }
}
