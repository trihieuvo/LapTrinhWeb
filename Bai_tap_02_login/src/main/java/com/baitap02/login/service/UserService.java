package com.baitap02.login.service;

import com.baitap02.login.model.User;

public interface UserService {
    User login(String username, String password);
    User get(String username);
}