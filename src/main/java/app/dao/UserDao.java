package app.dao;

import app.model.User;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Map;

public interface UserDao {
    User getUserByName(String name);
}
