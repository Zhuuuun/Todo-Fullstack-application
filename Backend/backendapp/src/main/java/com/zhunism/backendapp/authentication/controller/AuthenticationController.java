package com.zhunism.backendapp.authentication.controller;

import com.zhunism.backendapp.authentication.dto.request.AuthenticationRequestDTO;
import com.zhunism.backendapp.authentication.dto.response.AuthenticationResponseDTO;
import com.zhunism.backendapp.authentication.service.AuthService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.DisabledException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/api")
public class AuthenticationController {
    private final AuthService authService;

    public AuthenticationController(AuthService authService) {
        this.authService = authService;
    }

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
