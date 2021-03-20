package app.service;

import app.dao.AdminDao;
import app.dao.UserDao;
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
    public ArrayList<User> allUsers() {
       return adminDao.allUsers();
    }

    @Transactional
    @Override
    public void deleteUser(Long userId) {
        adminDao.deleteUser(userId);
    }
}
