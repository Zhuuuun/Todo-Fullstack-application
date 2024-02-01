package com.zhunism.backendapp.service.impl;

import com.zhunism.backendapp.entity.User;
import com.zhunism.backendapp.exception.ElementNotFoundException;
import com.zhunism.backendapp.repository.UserRepository;
import com.zhunism.backendapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;
    @Override
    public int findUserIdByUserName(String userName) {
        User user =  userRepository.findFirstByUserName(userName);
        if(user == null) throw new ElementNotFoundException("User not found with Username : " + userName);
        return user.getId();

    }

}
