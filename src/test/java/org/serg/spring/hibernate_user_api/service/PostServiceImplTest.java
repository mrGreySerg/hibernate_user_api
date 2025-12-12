package org.serg.spring.hibernate_user_api.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.serg.spring.hibernate_user_api.dto.PostResponseDto;
import org.serg.spring.hibernate_user_api.entity.Post;
import org.serg.spring.hibernate_user_api.repository.PostRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PostServiceImplTest {

    @Mock
    private PostRepository postRepository;

    @InjectMocks
    private PostServiceImpl postServiceImpl;

    @Test
    public void whenReturnPostById() {

        PostResponseDto expectedPostResponseDto =
                PostResponseDto.builder()
                        .title("Title")
                        .date(LocalDate.parse("2020-11-11"))
                        .text("text")
                        .build();

        Post post = Post.builder()
                .title("Title")
                .date(LocalDate.parse("2020-11-11"))
                .text("text")
                .build();


        when(postRepository.findById(anyLong()))
                .thenReturn(Optional.of(post));

        PostResponseDto actualPostResponseDto = postServiceImpl.getPostById(1L);

        Assertions.assertEquals(expectedPostResponseDto, actualPostResponseDto);

        verify(postRepository, times(1)).findById(anyLong());

    }

    @Test
    public void whenThrowExceptionInMethodGetPostById() {

        when(postRepository.findById(anyLong()))
                .thenReturn(Optional.empty());


        Assertions.assertThrows(ResponseStatusException.class,
                () -> postServiceImpl.getPostById(10L));

        verify(postRepository, times(1)).findById(anyLong());

    }

}
