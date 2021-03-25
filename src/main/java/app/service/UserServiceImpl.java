package app.service;


import app.model.Role;
import app.model.User;
import app.repository.RoleRepository;
import app.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@Component
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

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
    public void createDefaultRows() {
        Set<Role> rol = new HashSet<>();
        Role roleAdmin = new Role(1, "ROLE_ADMIN");
        Role roleUser = new Role(2, "ROLE_USER");
        rol.add(roleAdmin);
        rol.add(roleUser);
        User user = new User(1, "sofia", "kim", 25, "admin", "admin", rol);
        roleRepository.save(roleAdmin);
        roleRepository.save(roleUser);
        userRepository.save(user);
    }
}
