package com.zhunism.backendapp.service;

import com.zhunism.backendapp.dto.AuthenticationResponseDTO;
import com.zhunism.backendapp.dto.request.AuthenticationRequestDTO;
import com.zhunism.backendapp.dto.request.SignupRequestDTO;
import com.zhunism.backendapp.dto.UserResponseDTO;
import com.zhunism.backendapp.entity.User;
import com.zhunism.backendapp.exception.DuplicatedUserException;

import java.util.List;

public interface AuthService {
    UserResponseDTO createUser(SignupRequestDTO signupRequestDTO) throws DuplicatedUserException;

    List<User> findAll();

    AuthenticationResponseDTO generatedTokenWithUser(AuthenticationRequestDTO authenticationRequestDTO);


}
