package com.zhunism.backendapp.authentication.service;

import com.zhunism.backendapp.authentication.dto.request.AuthenticationRequestDTO;
import com.zhunism.backendapp.authentication.dto.request.SignupRequestDTO;
import com.zhunism.backendapp.authentication.entity.User;
import com.zhunism.backendapp.authentication.repository.UserRepository;
import com.zhunism.backendapp.authentication.service.impl.AuthServiceImpl;
import com.zhunism.backendapp.authentication.util.JwtUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AuthServiceTest {
    public static final String FIRST_NAME = "Daily";
    public static final String LAST_NAME = "Ali";
    public static final String USER_NAME = "dailyali";
    public static final String PHONE_NUMBER = "0000000000";
    public static final String PASSWORD = "P@ssw0rd";
    public static final String DUPLICATED_USER_EXCEPTION_MESSAGE = "Username already exists. Please use another Username.";
    @Mock private UserRepository userRepository;
    @Mock private UserDetailsService userDetailsService;
    @Mock private JwtUtil jwtUtil;
    @Mock private AuthenticationManager authenticationManager;
    private AuthService authService;

    @BeforeEach
    void setUp() {
        authService = new AuthServiceImpl(
                userRepository,
                userDetailsService,
                jwtUtil,
                authenticationManager
        );
    }

    @Test
    void testCreateUserWithValidRequestShouldSuccess() {
        SignupRequestDTO signupRequestDTO = new SignupRequestDTO(
                FIRST_NAME,
                LAST_NAME,
                USER_NAME,
                PHONE_NUMBER,
                PASSWORD
        );
        User expected = convertSignupRequestDTOToUser(signupRequestDTO);
        when(userRepository.save(Mockito.any(User.class))).thenReturn(expected);

        authService.createUser(signupRequestDTO);

        ArgumentCaptor<User> userArgumentCaptor = ArgumentCaptor.forClass(User.class);
        verify(userRepository)
                .save(userArgumentCaptor.capture());
        User actual = userArgumentCaptor.getValue();

        assertUser(expected,actual);

    }

    @Test
    void testValidUserAuthMustGenerateToken() {
        AuthenticationRequestDTO authenticationRequestDTO = new AuthenticationRequestDTO(
                USER_NAME,
                PASSWORD
        );
        UserDetails userDetails = Mockito.mock(UserDetails.class);
        when(userDetailsService.loadUserByUsername(Mockito.contains(USER_NAME))).thenReturn(userDetails);
        when(userDetails.getUsername()).thenReturn(USER_NAME);

        authService.generatedTokenWithUser(authenticationRequestDTO);

        verify(authenticationManager).authenticate(new UsernamePasswordAuthenticationToken(USER_NAME,PASSWORD));
        verify(jwtUtil).generateToken(USER_NAME);
    }

    private static User convertSignupRequestDTOToUser(SignupRequestDTO signupRequestDTO) {
        User user = new User();
        user.setFirstName(signupRequestDTO.getFirstName());
        user.setLastName(signupRequestDTO.getLastName());
        user.setPhone(signupRequestDTO.getPhone());
        user.setUserName(signupRequestDTO.getUserName());

        return user;
    }

    private static void assertUser(User u1, User u2) {
        assertThat(u1.getFirstName()).isEqualTo(u2.getFirstName());
        assertThat(u1.getLastName()).isEqualTo(u2.getLastName());
        assertThat(u1.getUserName()).isEqualTo(u2.getUserName());
        assertThat(u1.getPhone()).isEqualTo(u2.getPhone());
        assertThat(new BCryptPasswordEncoder().matches(PASSWORD,u2.getPassword())).isTrue();
    }

}
