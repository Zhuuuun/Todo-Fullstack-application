package com.zhunism.backendapp.authentication.controller;

import com.zhunism.backendapp.authentication.dto.request.SignupRequestDTO;
import com.zhunism.backendapp.authentication.dto.response.UserResponseDTO;
import com.zhunism.backendapp.authentication.entity.User;
import com.zhunism.backendapp.authentication.service.AuthService;
import com.zhunism.backendapp.authentication.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class SignupUserController {

    private final AuthService authService;
    private final UserService userService;

    public SignupUserController(AuthService authService, UserService userService) {
        this.authService = authService;
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> createUser(@RequestBody @Valid SignupRequestDTO signupRequestDTO)  {
        UserResponseDTO createdUser = authService.createUser(signupRequestDTO);

        if(createdUser == null) return new ResponseEntity<>("User isn't created, Try again later.", HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(createdUser,HttpStatus.CREATED);
    }

    @GetMapping("/users")
    public List<User> findAll() {
        return userService.findAll();
    }
}
