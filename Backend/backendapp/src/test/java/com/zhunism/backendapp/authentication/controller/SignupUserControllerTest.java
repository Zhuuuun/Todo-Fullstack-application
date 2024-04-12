package com.zhunism.backendapp.authentication.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zhunism.backendapp.authentication.dto.request.SignupRequestDTO;
import com.zhunism.backendapp.authentication.dto.response.UserResponseDTO;
import com.zhunism.backendapp.authentication.service.AuthService;
import com.zhunism.backendapp.authentication.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class SignupUserControllerTest {
    public static final String FIRST_NAME = "Gojo";
    public static final String LAST_NAME = "Satoru";
    public static final String USER_NAME = "gojosatoru";
    public static final String PHONE_NUMBER = "0000000000";
    public static final String PASSWORD = "P@ssw0rd";
    public static final int ID = 1;
    @Autowired
    private MockMvc mockMvc;
    @Mock private AuthService authService;
    @Mock private UserService userService;
    private final ObjectMapper objectMapper = new ObjectMapper();
    private static SignupRequestDTO signupRequestDTO;
    private static UserResponseDTO userResponseDTO;

    @BeforeEach
    void setUp() {
        SignupUserController signupUserController = new SignupUserController(authService,userService);
        mockMvc = MockMvcBuilders.standaloneSetup(signupUserController).alwaysDo(print()).build();

        signupRequestDTO = new SignupRequestDTO(
                FIRST_NAME,
                LAST_NAME,
                USER_NAME,
                PHONE_NUMBER,
                PASSWORD
        );

        userResponseDTO = new UserResponseDTO(
                ID,
                FIRST_NAME,
                LAST_NAME,
                USER_NAME,
                PHONE_NUMBER
        );
    }

    @Test
    void testCreateUserWithValidInputShouldSuccess() throws Exception {
        when(authService.createUser(signupRequestDTO)).thenReturn(userResponseDTO);

        mockMvc.perform(
                post("/api/register")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(signupRequestDTO))
                )
                .andExpect(status().isCreated());
    }
}
