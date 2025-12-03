package org.serg.spring.hibernate_user_api.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.serg.spring.hibernate_user_api.dto.UserRequestDto;
import org.serg.spring.hibernate_user_api.dto.UserResponseDto;
import org.serg.spring.hibernate_user_api.entity.User;
import org.serg.spring.hibernate_user_api.repository.UserRepository;
import org.serg.spring.hibernate_user_api.util.UserMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public UserResponseDto saveUser(UserRequestDto userRequestDto) {

        User user = UserMapper.userRequestDtoToUser(userRequestDto);
        User saved = userRepository.save(user);
        UserResponseDto userResponseDto = UserMapper.userToUserResponseDto(saved);

        return userResponseDto;
    }

    @Override
    public void deleteUserById(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public UserResponseDto getUserById(Long id) {
        Optional<User> userOption = userRepository.findById(id);
        User user = userOption.orElseThrow(() -> new EntityNotFoundException(
                "User not found with id: " + id
        ));

        UserResponseDto userResponseDto = UserMapper.userToUserResponseDto(user);
        return userResponseDto;
    }

    @Override
    public UserResponseDto updateUserById(Long id, UserRequestDto userRequestDto) {
        User user = userRepository.findById(id).orElseThrow(
                ()-> new EntityNotFoundException("User not found with id: " + id)
        );
        user.setUsername(userRequestDto.getUsername());
        user.setAge(userRequestDto.getAge());
        User newUser = userRepository.save(user);
        UserResponseDto userResponseDto = UserMapper.userToUserResponseDto(newUser);

        return userResponseDto;
    }

    @Override
    public List<UserResponseDto> getAllUsers() {
        List<User> users = userRepository.findAll();
        List<UserResponseDto> userResponseDtos =
                new ArrayList<>();
        for(User user: users) {
            userResponseDtos.add(UserMapper.userToUserResponseDto(user));
        }
        return userResponseDtos;
    }

}
