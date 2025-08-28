package com.baitap02.login.service;

import java.util.Date;

import com.baitap02.login.dao.UserDao;
import com.baitap02.login.dao.UserDaoImpl;
import com.baitap02.login.model.User;



public class UserServiceImpl implements UserService {
    UserDao userDao = new UserDaoImpl();
    
    @Override
    public User login(String username, String password) {
        User user = this.get(username);
        if (user != null && password.equals(user.getPassword())) {
            return user;
        }
        return null;
    }
    
    @Override
    public User get(String username) {
        return userDao.get(username);
    }
    
    @Override
    public boolean register(String email, String password, String username, String fullname) {
        if (userDao.checkExistUsername(username) || userDao.checkExistEmail(email)) {
            return false;
        }
        
        User user = new User();
        user.setEmail(email);
        user.setPassword(password);
        user.setUserName(username);
        user.setFullName(fullname);
        user.setRoleid(0); // Mặc định roleid = 0 cho người dùng mới
        user.setCreatedDate(new Date());
        
        userDao.insert(user);
        return true;
    }
    
    @Override
    public void insert(User user) {
        userDao.insert(user);
    }

    @Override
    public boolean checkExistEmail(String email) {
        return userDao.checkExistEmail(email);
    }

    @Override
    public boolean checkExistUsername(String username) {
        return userDao.checkExistUsername(username);
    }
}