package com.zhunism.backendapp.authentication.service.impl;

import com.zhunism.backendapp.authentication.entity.User;
import com.zhunism.backendapp.authentication.exception.ElementNotFoundException;
import com.zhunism.backendapp.authentication.repository.UserRepository;
import com.zhunism.backendapp.authentication.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public int findUserIdByUserName(String userName) {
        User user =  userRepository.findFirstByUserName(userName);
        if(user == null) throw new ElementNotFoundException("User not found with Username : " + userName);
        return user.getId();

    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

}
