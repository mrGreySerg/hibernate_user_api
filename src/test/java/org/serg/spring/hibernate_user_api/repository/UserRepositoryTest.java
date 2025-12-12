package org.serg.spring.hibernate_user_api.repository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.serg.spring.hibernate_user_api.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

@ActiveProfiles("test")
@DataJpaTest
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void saveAndFindUserTest() {

        User user = User.builder()
                .username("Tom")
                .age(40)
                .build();

        User savedUser = userRepository.save(user);
        Optional<User> resultUser = userRepository.findById(savedUser.getId());

        Assertions.assertTrue(resultUser.isPresent());
        Assertions.assertEquals(savedUser.getUsername(), resultUser.get().getUsername());
        Assertions.assertEquals(savedUser.getAge(), resultUser.get().getAge());

    }
}
