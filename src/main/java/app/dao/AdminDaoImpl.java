package app.dao;

import app.model.User;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;

@Component
@Repository
public class AdminDaoImpl implements AdminDao{

    @PersistenceContext(unitName = "entityManagerFactory")
    private EntityManager entityManager;
    @Override
    public ArrayList<User> allUsers() {
        return null;
    }

    @Override
    public void deleteUser(Long userId) {

    }
}
