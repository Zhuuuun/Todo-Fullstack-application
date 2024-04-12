package com.zhunism.backendapp.authentication.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zhunism.backendapp.authentication.dto.request.AuthenticationRequestDTO;
import com.zhunism.backendapp.authentication.dto.response.AuthenticationResponseDTO;
import com.zhunism.backendapp.authentication.service.AuthService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ExtendWith(MockitoExtension.class)
public class AuthenticationControllerTest {
    public static final String INVALID_USER_NAME = "tojisan";
    @Autowired
    private MockMvc mockMvc;
    @Mock
    private AuthService authService;
    private final ObjectMapper objectMapper = new ObjectMapper();
    public static final String VALID_USER_NAME = "gojosatoru";
    public static final String PASSWORD = "P@ssw0rd";
    public static final String JWT_TOKEN = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VybmFtZSI6Imdvam9zYXRvcnUiLCJwYXNzd29yZCI6IlBAc3N3MHJkIn0.tc2bfRjz6BW72DlYkDv1UIG01g5joFuoG8Ooks8MTqs";
    private AuthenticationRequestDTO validAuthenticationRequestDTO;
    private AuthenticationRequestDTO invalidAuthenticationRequestDTO;
    private AuthenticationResponseDTO authenticationResponseDTO;

    @BeforeEach
    void setUp() {
        AuthenticationController authenticationController = new AuthenticationController(authService);
        mockMvc = MockMvcBuilders.standaloneSetup(authenticationController).alwaysDo(print()).build();

        validAuthenticationRequestDTO = new AuthenticationRequestDTO(
                VALID_USER_NAME,
                PASSWORD
        );

        invalidAuthenticationRequestDTO = new AuthenticationRequestDTO(
                INVALID_USER_NAME,
                PASSWORD
        );

        authenticationResponseDTO = new AuthenticationResponseDTO(
                JWT_TOKEN
        );
    }

    @Test
    void testAuthenticateWithValidCredentialShouldSuccess() throws Exception {
        when(authService.generatedTokenWithUser(validAuthenticationRequestDTO))
                .thenReturn(authenticationResponseDTO);

        mockMvc.perform(
                    post("/api/authentication")
                            .contentType(MediaType.APPLICATION_JSON_VALUE)
                            .accept(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(validAuthenticationRequestDTO))
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.jwt").value(authenticationResponseDTO.getJwt()));
    }

    @Test
    @Disabled
    void testAuthenticateWithInvalidCredentialsShouldReturnErrorMessage() throws Exception {
        when(authService.generatedTokenWithUser(invalidAuthenticationRequestDTO)).thenThrow(BadCredentialsException.class);

        mockMvc.perform(
                        post("/api/authentication")
                                .contentType(MediaType.APPLICATION_JSON_VALUE)
                                .accept(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(invalidAuthenticationRequestDTO))
                )
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.errorMessage").value("Incorrect Username or password."));
    }

}
