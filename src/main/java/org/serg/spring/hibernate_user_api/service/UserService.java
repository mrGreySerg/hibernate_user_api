package org.serg.spring.hibernate_user_api.service;

import org.serg.spring.hibernate_user_api.dto.UserRequestDto;
import org.serg.spring.hibernate_user_api.dto.UserResponseDto;
import org.serg.spring.hibernate_user_api.entity.User;

public interface UserService {

    UserResponseDto saveUser(UserRequestDto userRequestDto);
    void deleteUserById(Long id);
    UserResponseDto getUserById(Long id);
    UserResponseDto updateUserById(Long id, UserRequestDto userRequestDto);

}
