package org.serg.spring.hibernate_user_api.util;

import org.serg.spring.hibernate_user_api.dto.PostRequestDto;
import org.serg.spring.hibernate_user_api.dto.PostResponseDto;
import org.serg.spring.hibernate_user_api.entity.Post;

public class PostMapper {

    public static PostRequestDto postToPostRequestDto(Post post) {
        return PostRequestDto.builder()
                .title(post.getTitle())
                .date(post.getDate())
                .text(post.getText())
                .build();
    }

    public static PostResponseDto postToResponseDto(Post post) {
        return PostResponseDto.builder()
                .id(post.getId())
                .title(post.getTitle())
                .date(post.getDate())
                .text(post.getText())
                .userResponseDto(UserMapper.userToUserResponseDto(post.getUser()))
                .build();
    }

    public static Post postRequestDtoToPost(PostRequestDto postRequestDto) {
        return Post.builder()
                .title(postRequestDto.getTitle())
                .date(postRequestDto.getDate())
                .text(postRequestDto.getText())
                .build();
    }

    public static Post postResponseDtoToPost(PostResponseDto postResponseDto) {
        return Post.builder()
                .id(postResponseDto.getId())
                .title(postResponseDto.getTitle())
                .date(postResponseDto.getDate())
                .text(postResponseDto.getText())
                .user(UserMapper.userResponseDtoToUser(postResponseDto.getUserResponseDto()))
                .build();
    }

}
