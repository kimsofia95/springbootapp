package app.dao;

import app.model.User;

import java.util.Map;

public interface UserDao {
    User getUserByName(String name);
}
