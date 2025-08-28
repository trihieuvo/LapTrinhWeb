package com.baitap02.login.service;

import com.baitap02.login.model.User;

public interface UserService {
    User login(String username, String password);
    User get(String username);
    
    void insert(User user);
    boolean register(String email, String password, String username, String fullname);
    boolean checkExistEmail(String email);
    boolean checkExistUsername(String username);

}