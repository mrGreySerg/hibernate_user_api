package org.serg.spring.hibernate_user_api.service;

import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.serg.spring.hibernate_user_api.dto.UserResponseDto;
import org.serg.spring.hibernate_user_api.entity.User;
import org.serg.spring.hibernate_user_api.repository.UserRepository;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl userService;

    @Test
    public void whenGetUserById() {

        User newUser = User.builder()
                .username("Serg")
                .age(20)
                .build();

        UserResponseDto expectedUserDto = UserResponseDto.builder()
                .username("Serg")
                .age(20)
                .build();

        when(userRepository.findById(anyLong())).thenReturn(Optional.of(newUser));

        UserResponseDto actualUser = userService.getUserById(12L);

        verify(userRepository, times(1)).findById(anyLong());

        Assertions.assertEquals(expectedUserDto.getUsername(), actualUser.getUsername());

    }

    @Test
    public void whenThrowExceptionInMethodGetById() {

        when(userRepository.findById(anyLong())).thenThrow(EntityNotFoundException.class);

        Assertions.assertThrows(EntityNotFoundException.class,() -> userService.getUserById(20L));

        verify(userRepository, times(1)).findById(anyLong());

    }

}
