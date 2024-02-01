package com.zhunism.backendapp.service.impl;

import com.zhunism.backendapp.dto.AuthenticationResponseDTO;
import com.zhunism.backendapp.dto.request.AuthenticationRequestDTO;
import com.zhunism.backendapp.dto.request.SignupRequestDTO;
import com.zhunism.backendapp.dto.UserResponseDTO;
import com.zhunism.backendapp.entity.User;
import com.zhunism.backendapp.exception.DuplicatedUserException;
import com.zhunism.backendapp.repository.UserRepository;
import com.zhunism.backendapp.service.AuthService;
import com.zhunism.backendapp.service.jwt.UserDetailsServiceImpl;
import com.zhunism.backendapp.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.List;


@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private AuthenticationManager authenticationManager;

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
    public List<User> findAll() {
        return userRepository.findAll();
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
