package com.zhunism.backendapp.authentication.service;

import com.zhunism.backendapp.authentication.dto.response.AuthenticationResponseDTO;
import com.zhunism.backendapp.authentication.dto.request.AuthenticationRequestDTO;
import com.zhunism.backendapp.authentication.dto.request.SignupRequestDTO;
import com.zhunism.backendapp.authentication.dto.response.UserResponseDTO;
import com.zhunism.backendapp.authentication.entity.User;
import com.zhunism.backendapp.authentication.exception.DuplicatedUserException;

import java.util.List;

public interface AuthService {
    UserResponseDTO createUser(SignupRequestDTO signupRequestDTO) throws DuplicatedUserException;

    AuthenticationResponseDTO generatedTokenWithUser(AuthenticationRequestDTO authenticationRequestDTO);


}
