package com.zhunism.backendapp.controller;

import com.zhunism.backendapp.dto.request.AuthenticationRequestDTO;
import com.zhunism.backendapp.dto.AuthenticationResponseDTO;
import com.zhunism.backendapp.service.AuthService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/api")
public class AuthenticationController {

    @Autowired
    private AuthService authService;

    @PostMapping("/authentication")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequestDTO authenticationRequestDTO, HttpServletResponse response) throws IOException {
        AuthenticationResponseDTO token;

        try{
             token = authService.generatedTokenWithUser(authenticationRequestDTO);
        } catch (DisabledException e) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, e.getMessage());
            return null;
        }

        return new ResponseEntity<>(token,HttpStatus.OK);

    }

}
