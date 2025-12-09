package org.serg.spring.hibernate_user_api.service;

import org.serg.spring.hibernate_user_api.dto.PostRequestDto;
import org.serg.spring.hibernate_user_api.dto.PostResponseDto;
import org.serg.spring.hibernate_user_api.entity.Post;

import java.util.List;

public interface PostService {

    PostResponseDto savePost(Long id, PostRequestDto postRequestDto);
    List<PostResponseDto> getAllPostsByUserId(Long id);
    List<PostResponseDto> getPostsByTextLengthLessThen(int length);
    List<PostResponseDto> getAllPosts();
    PostResponseDto getPostById(Long id);

}
