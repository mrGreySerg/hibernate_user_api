package org.serg.spring.hibernate_user_api.controller;

import lombok.RequiredArgsConstructor;
import org.serg.spring.hibernate_user_api.dto.PostRequestDto;
import org.serg.spring.hibernate_user_api.dto.PostResponseDto;
import org.serg.spring.hibernate_user_api.dto.UserRequestDto;
import org.serg.spring.hibernate_user_api.dto.UserResponseDto;
import org.serg.spring.hibernate_user_api.service.PostService;
import org.serg.spring.hibernate_user_api.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    private final PostService postService;

    @GetMapping
    public ResponseEntity<List<UserResponseDto>> getAllUsers() {
        List<UserResponseDto> users = userService.getAllUsers();
        return ResponseEntity.status(HttpStatus.OK).body(users);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<UserResponseDto> getUserById(
            @PathVariable Long id
    ) {
        UserResponseDto userResponseDto =
                userService.getUserById(id);
        return ResponseEntity.status(HttpStatus.OK).body(userResponseDto);
    }

    @PostMapping
    public ResponseEntity<UserResponseDto> saveUser(@RequestBody UserRequestDto userRequestDto) {
        UserResponseDto userResponseDto = userService.saveUser(userRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(userResponseDto);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> deleteUserById(@PathVariable Long id) {
        userService.deleteUserById(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping(path = "/{id}/posts")
    public ResponseEntity<PostResponseDto> savePost(
            @PathVariable Long id,
            @RequestBody PostRequestDto postRequestDto) {
        PostResponseDto postResponseDto = postService.savePost(id, postRequestDto);

        return ResponseEntity.status(HttpStatus.CREATED).body(postResponseDto);
    }

    @GetMapping(path = "/{id}/posts")
    public ResponseEntity<List<PostResponseDto>> getPostsByUserId(@PathVariable Long id) {
        List<PostResponseDto> postDtos = postService.getAllPostsByUserId(id);
        return ResponseEntity.ok(postDtos);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<UserResponseDto> updateUser(
            @PathVariable Long id,
            @RequestBody UserRequestDto userRequestDto
    ) {
        UserResponseDto userResponseDto = userService.updateUserById(id, userRequestDto);
        return ResponseEntity.ok(userResponseDto);
    }


}



