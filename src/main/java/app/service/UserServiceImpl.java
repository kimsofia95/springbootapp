package app.service;


import app.model.Role;
import app.model.User;
import app.repository.RoleRepository;
import app.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@Component
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final String salt = BCrypt.gensalt(12);

    @Autowired
    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public void save(User user) {
        user.setPassword(BCrypt.hashpw(user.getPassword(), salt));
        userRepository.save(user);
    }

    @Override
    public User show(int id) {
        Optional<User> optionalUser = userRepository.findById(id);
        return optionalUser.get();
    }

    @Override
    public Role showRole(int id) {
        Optional<Role> optionalUser = roleRepository.findById(id);

        return optionalUser.get();
    }

    @Override
    public void update(User user, int[] roles) {
        Set<Role> rol = new HashSet<>();
        for (int role_id: roles) {
            rol.add(showRole(role_id));
        }
        user.setRoles(rol);
        userRepository.save(user);
    }

    @Override
    public void delete(int id) {
        userRepository.deleteById(id);
    }

    @Override
    public User findUserByName(String name) {
       ArrayList<User> list = (ArrayList<User>) userRepository.findAll();
       for (User user: list) {
           if (user.getUsername().equals(name)) {
               return user;
           }
       }
       return null;
    }

    @Override
    public void saveDefaultUser(User user) {
        for (Role role: user.getRoles()) {
            roleRepository.save(role);
        }
        userRepository.save(user);
    }
}
