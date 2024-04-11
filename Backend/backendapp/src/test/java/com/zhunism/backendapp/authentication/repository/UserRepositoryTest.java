package com.zhunism.backendapp.authentication.repository;

import com.zhunism.backendapp.authentication.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;


@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class UserRepositoryTest {

    public static final String USER_NAME = "johndoe123";
    public static final String FIRST_NAME = "john";
    public static final String LAST_NAME = "doe";
    @Autowired
    private UserRepository userRepository;

    @Test
    public void testSaveUserShouldValidInDatabase() {
        User expectedUser = createSingleUser(USER_NAME,FIRST_NAME,LAST_NAME);

        userRepository.save(expectedUser);
        User actualUser = userRepository.findById(expectedUser.getId()).get();

        assertUser(expectedUser,actualUser);
    }

    @Test
    public void testFindFirstByUserNameShouldReturn() {
        User expectedUser = createSingleUser(USER_NAME,FIRST_NAME,LAST_NAME);

        userRepository.save(expectedUser);
        User actualUser = userRepository.findFirstByUserName(USER_NAME);

        assertUser(expectedUser,actualUser);
    }

    @Test
    public void testFindAllShouldReturnAll() {
        List<User> expectedUsers = new ArrayList<>(Arrays.asList(
                createSingleUser("user1","Marry","Ginger"),
                createSingleUser("user2","Tom","Hank"),
                createSingleUser("user3","Oh","Nana")
        ));

        userRepository.saveAll(expectedUsers);
        List<User> actualUsers = userRepository.findAll();

        assertEquals(expectedUsers.size(),actualUsers.size());
    }

    @Test
    public void testDeleteUserById() {
        User user = createSingleUser(USER_NAME,FIRST_NAME,LAST_NAME);
        int id = userRepository.save(user).getId();

        userRepository.delete(user);
        Optional<User> actualUser = userRepository.findById(id);

        assertFalse(actualUser.isPresent());
    }

    private static void assertUser(User u1, User u2) {
        assertEquals(u1.getId(),u2.getId());
        assertEquals(u1.getUserName(),u2.getUserName());
        assertEquals(u1.getFirstName(),u2.getFirstName());
        assertEquals(u1.getLastName(),u2.getLastName());
    }

    private static User createSingleUser(String userName, String firstName, String lastName) {
        User user = new User();
        user.setUserName(userName);
        user.setFirstName(firstName);
        user.setLastName(lastName);

        return user;
    }
}
