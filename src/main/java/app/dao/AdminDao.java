package app.dao;

import app.model.User;

import java.util.ArrayList;

public interface AdminDao {
    ArrayList<User> allUsers();
    void deleteUser(Long userId);
}
