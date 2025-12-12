package org.serg.spring.hibernate_user_api.controller;

import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Test;
import org.serg.spring.hibernate_user_api.dto.UserResponseDto;
import org.serg.spring.hibernate_user_api.service.PostService;
import org.serg.spring.hibernate_user_api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserController.class)
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @MockBean
    private PostService postService;

    @Test
    public void whenGetAllUsers() throws Exception {

        List<UserResponseDto> users = List.of(
                new UserResponseDto("Serg", 20),
                new UserResponseDto("Tom", 22)
        );

        when(userService.getAllUsers()).thenReturn(users);

        mockMvc.perform(MockMvcRequestBuilders.get("/users"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].username").value(users.get(0).getUsername()))
                .andExpect(jsonPath("$[0].age").value(users.get(0).getAge()))
                .andExpect(jsonPath("$[1].username").value(users.get(1).getUsername()))
                .andExpect(jsonPath("$[1].age").value(users.get(1).getAge()));

    }

}
