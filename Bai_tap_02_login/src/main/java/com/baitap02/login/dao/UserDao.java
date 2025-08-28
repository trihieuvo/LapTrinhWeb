package com.baitap02.login.dao;

import com.baitap02.login.model.User;

public interface UserDao {
    User get(String username);
    void insert(User user);
    boolean checkExistEmail(String email);
    boolean checkExistUsername(String username);

}