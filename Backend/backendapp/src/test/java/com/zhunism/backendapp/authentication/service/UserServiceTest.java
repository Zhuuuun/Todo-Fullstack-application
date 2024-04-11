package com.zhunism.backendapp.authentication.service;

import com.zhunism.backendapp.authentication.entity.User;
import com.zhunism.backendapp.authentication.repository.UserRepository;
import com.zhunism.backendapp.authentication.service.impl.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
    public static final String FIRST_NAME = "Daily";
    public static final String LAST_NAME = "Ali";
    public static final String USER_NAME = "dailyali";
    public static final String PHONE_NUMBER = "0000000000";
    public static final String PASSWORD = "P@ssw0rd";
    public static final int USER_ID = 100;

    @Mock UserRepository userRepository;
    private UserService userService;

    @BeforeEach
    void setUp() {
        userService = new UserServiceImpl(userRepository);
    }

    @Test
    void testFindIdByUsernameMustReturnId() {
        User user = new User(
                USER_ID,
                FIRST_NAME,
                LAST_NAME,
                PHONE_NUMBER,
                USER_NAME,
                new BCryptPasswordEncoder().encode(PASSWORD)
        );
        when(userRepository.findFirstByUserName(USER_NAME)).thenReturn(user);

        userService.findUserIdByUserName(USER_NAME);

        assertThat(user.getId()).isEqualTo(USER_ID);
    }
}
