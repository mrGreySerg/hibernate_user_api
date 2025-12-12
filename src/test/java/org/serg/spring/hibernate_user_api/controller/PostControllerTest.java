package org.serg.spring.hibernate_user_api.controller;

import org.junit.jupiter.api.Test;
import org.serg.spring.hibernate_user_api.dto.PostResponseDto;
import org.serg.spring.hibernate_user_api.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.server.ResponseStatusException;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.contains;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;


import java.time.LocalDate;
import java.util.EmptyStackException;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(PostController.class)
public class PostControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PostService postService;

    @Test
    public void whenExpectedListOfPosts() throws Exception {
        List<PostResponseDto> postResponseDtoList =
                List.of(
                        PostResponseDto.builder()
                                .title("title")
                                .date(LocalDate.parse("2020-11-11"))
                                .text("text")
                                .build(),
                        PostResponseDto.builder()
                                .title("title_2")
                                .date(LocalDate.parse("2010-10-10"))
                                .text("text")
                                .build()
                );
        when(postService.getAllPosts()).thenReturn(postResponseDtoList);
        mockMvc.perform(get("/posts"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].title").value("title"));
    }


    @Test
    public void whenReturnEmptyList() throws Exception {
        List<PostResponseDto> emptyList = List.of();

        when(postService.getAllPosts()).thenReturn(emptyList);

        mockMvc.perform(get("/posts"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(0));


    }

    @Test
    public void whenThrowExceptionWithStatusNotFound() throws Exception {

        when(postService.getPostById(anyLong()))
                .thenThrow(new ResponseStatusException(HttpStatus.NOT_FOUND, "Post not found"));

        mockMvc.perform(get("/posts/42"))
                .andExpect(status().isNotFound());


    }

}
