package com.zhunism.backendapp.authentication.service;

import com.zhunism.backendapp.authentication.entity.User;

import java.util.List;

public interface UserService {
    int findUserIdByUserName(String userName);
    List<User> findAll();
}
