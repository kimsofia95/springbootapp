package app.service;

import app.dao.AdminDao;
import app.dao.UserDao;
import app.model.Role;
import app.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;

@Component
@Service
public class AdminServiceImpl implements AdminService{
    @Autowired
    private AdminDao adminDao;

    @Transactional
    @Override
    public ArrayList<Role> allRoles() {
        return adminDao.allRoles();
    }

    @Transactional
    @Override
    public ArrayList<User> allUsers() {
       return adminDao.allUsers();
    }

    @Transactional
    @Override
    public void deleteUser(User user) {
        adminDao.deleteUser(user);
    }

    @Transactional
    @Override
    public void addUser(User user) {
        adminDao.addUser(user);
    }

    @Transactional
    @Override
    public void ChangeUser(User user) {
        adminDao.ChangeUser(user);
    }
}
