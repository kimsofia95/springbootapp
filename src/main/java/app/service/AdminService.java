package app.service;

import app.model.User;

import java.util.ArrayList;

public interface AdminService {
    ArrayList<User> allUsers();
    void deleteUser(Long userId);
}
