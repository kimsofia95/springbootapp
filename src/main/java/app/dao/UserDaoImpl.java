package app.dao;

import app.model.Role;
import app.model.User;
import org.springframework.context.annotation.EnableLoadTimeWeaving;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;

@Component
@Repository
public class UserDaoImpl implements UserDao {

    @PersistenceContext(unitName = "entityManagerFactory")
    private EntityManager entityManager;

    @Override
    public User getUserByName(String name) {
        User user = (User) entityManager.createQuery("select n from User n where n.name = '" + name +"'").getSingleResult();
        if (user == null) {
            return null;
        }

        return user;
    }
}

