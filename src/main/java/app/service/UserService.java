package app.service;


import app.model.Role;
import app.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<User> getAllUsers();

    void save(User user);

    User show(int id);

    void update(User user, int[] role);

    Role showRole(int id);

    void delete(int id);

    User findUserByName(String name);

    void createDefaultRows();

    Optional<User> findById(int id);
}
