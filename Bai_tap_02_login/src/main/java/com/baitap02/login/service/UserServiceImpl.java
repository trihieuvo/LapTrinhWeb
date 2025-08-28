package com.baitap02.login.service;

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
}