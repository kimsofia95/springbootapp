package app.dao;

import app.model.Role;
import app.model.User;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

@Component
@Repository
public class AdminDaoImpl implements AdminDao{

    @PersistenceContext(unitName = "entityManagerFactory")
    private EntityManager entityManager;
    @Override
    public ArrayList<User> allUsers() {
        return (ArrayList<User>) entityManager.createQuery("select n from User n").getResultList();
    }

    @Override
    public ArrayList<Role> allRoles() {
        return (ArrayList<Role>) entityManager.createQuery("select n from Role n").getResultList();
    }

    @Override
    public void deleteUser(User user) {
        entityManager.remove(entityManager.contains(user) ? user : entityManager.merge(user));
    }

    @Override
    public void addUser(User user, String[] roles) {
        Set<Role> rolesSet = new HashSet<>();
        for(String role: roles) {
            Role getRole = (Role) entityManager.createQuery("select n from Role n where n.role='" + role + "'").getSingleResult();
            rolesSet.add(getRole);
        }
        user.setRolesSecond(rolesSet);
        entityManager.persist(user);
    }

    @Override
    public void ChangeUser(User user, String[] roles) {
        Set<Role> rolesSet = new HashSet<>();
        for(String role: roles) {
            Role getRole = (Role) entityManager.createQuery("select n from Role n where n.role='" + role + "'").getSingleResult();
            rolesSet.add(getRole);
        }
        user.setRolesSecond(rolesSet);
        entityManager.merge(user);
    }
}
