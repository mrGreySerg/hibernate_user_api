package org.serg.spring.hibernate_user_api.integration;

import org.junit.jupiter.api.Test;
import org.serg.spring.hibernate_user_api.entity.User;
import org.serg.spring.hibernate_user_api.repository.UserRepository;
import org.serg.spring.hibernate_user_api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource("classpath:application-test.yaml")
public class UserIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserRepository userRepository;

    @Test
    public void returnSavedUser() throws Exception {

        User user = User.builder()
                .username("Serg")
                .age(30)
                .build();

        User savedUser = userRepository.save(user);

        mockMvc.perform(MockMvcRequestBuilders.get("/users"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].username").value(savedUser.getUsername()))
                .andExpect(jsonPath("$[0].age").value(savedUser.getAge()));

    }

}
