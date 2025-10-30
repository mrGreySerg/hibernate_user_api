package org.serg.spring.hibernate_user_api.util;

import org.serg.spring.hibernate_user_api.dto.UserRequestDto;
import org.serg.spring.hibernate_user_api.dto.UserResponseDto;
import org.serg.spring.hibernate_user_api.entity.User;

public class UserMapper {

    public static UserRequestDto userToUserRequestDto(User user) {
        return UserRequestDto.builder()
                .username(user.getUsername())
                .age(user.getAge())
                .build();
    }

    public static UserResponseDto userToUserResponseDto(User user) {
        return UserResponseDto.builder()
                .id(user.getId())
                .username(user.getUsername())
                .age(user.getAge())
                .build();
    }

    public static User userRequestDtoToUser(UserRequestDto userRequestDto) {
        return User.builder()
                .username(userRequestDto.getUsername())
                .age(userRequestDto.getAge())
                .build();
    }

    public static User userResponseDtoToUser(UserResponseDto userResponseDto) {
        return User.builder()
                .id(userResponseDto.getId())
                .username(userResponseDto.getUsername())
                .age(userResponseDto.getAge())
                .build();
    }
}
