package org.serg.spring.hibernate_user_api.integration;

import org.junit.jupiter.api.Test;
import org.serg.spring.hibernate_user_api.dto.UserResponseDto;
import org.serg.spring.hibernate_user_api.entity.Post;
import org.serg.spring.hibernate_user_api.entity.User;
import org.serg.spring.hibernate_user_api.repository.PostRepository;
import org.serg.spring.hibernate_user_api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;

@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application-test.yaml")
public class PostIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserRepository userRepository;

    @Test
    public void returnsSavedPost() throws Exception {

        User newUser = User.builder()
                .username("Serg")
                .age(30)
                .build();
        User savedUser = userRepository.save(newUser);

        Post newPost = Post.builder()
                .title("Title post")
                .text("Text post")
                .date(LocalDate.parse("2020-11-11"))
                .user(newUser)
                .build();

        Post savedPost = postRepository.save(newPost);

        mockMvc.perform(get("/posts"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].title").value(newPost.getTitle()))
                .andExpect(jsonPath("$[0].text").value(newPost.getText()));

    }

}
