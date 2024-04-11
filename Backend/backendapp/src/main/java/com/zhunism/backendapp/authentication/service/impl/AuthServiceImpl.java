package com.zhunism.backendapp.authentication.service.impl;

import com.zhunism.backendapp.authentication.dto.response.AuthenticationResponseDTO;
import com.zhunism.backendapp.authentication.dto.request.AuthenticationRequestDTO;
import com.zhunism.backendapp.authentication.dto.request.SignupRequestDTO;
import com.zhunism.backendapp.authentication.dto.response.UserResponseDTO;
import com.zhunism.backendapp.authentication.entity.User;
import com.zhunism.backendapp.authentication.exception.DuplicatedUserException;
import com.zhunism.backendapp.authentication.repository.UserRepository;
import com.zhunism.backendapp.authentication.service.AuthService;
import com.zhunism.backendapp.authentication.util.JwtUtil;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;
    private final UserDetailsService userDetailsService;
    private final JwtUtil jwtUtil;
    private final AuthenticationManager authenticationManager;

    public AuthServiceImpl(UserRepository userRepository, UserDetailsService userDetailsService, JwtUtil jwtUtil, AuthenticationManager authenticationManager) {
        this.userRepository = userRepository;
        this.userDetailsService = userDetailsService;
        this.jwtUtil = jwtUtil;
        this.authenticationManager = authenticationManager;
    }

    @Override
    public UserResponseDTO createUser(SignupRequestDTO signupRequestDTO) {
        if(userRepository.findFirstByUserName(signupRequestDTO.getUserName()) != null) throw new DuplicatedUserException();

        User user = new User();
        user.setFirstName(signupRequestDTO.getFirstName());
        user.setLastName(signupRequestDTO.getLastName());
        user.setPhone(signupRequestDTO.getPhone());
        user.setUserName(signupRequestDTO.getUserName());
        user.setPassword(new BCryptPasswordEncoder().encode(signupRequestDTO.getPassword()));

        User createdUser = userRepository.save(user);

        UserResponseDTO userResponseDTO = new UserResponseDTO();
        userResponseDTO.setId(createdUser.getId());
        userResponseDTO.setFirstName(createdUser.getFirstName());
        userResponseDTO.setLastName(createdUser.getLastName());
        userResponseDTO.setPhone(createdUser.getPhone());
        userResponseDTO.setUserName(createdUser.getUserName());

        return userResponseDTO;
    }

    @Override
    public AuthenticationResponseDTO generatedTokenWithUser(AuthenticationRequestDTO authenticationRequestDTO)  {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequestDTO.getUserName(), authenticationRequestDTO.getPassword()));
        } catch (BadCredentialsException e) {
            throw new BadCredentialsException("Incorrect Username or password.");
        } catch (DisabledException disabledException) {
            throw new DisabledException("User is not created. Please Register first.");
        }

        final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequestDTO.getUserName());
        final String jwt = jwtUtil.generateToken(userDetails.getUsername());

        return new AuthenticationResponseDTO(jwt);
    }

}
