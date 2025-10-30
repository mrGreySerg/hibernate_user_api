package org.serg.spring.hibernate_user_api.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.serg.spring.hibernate_user_api.dto.PostRequestDto;
import org.serg.spring.hibernate_user_api.dto.PostResponseDto;
import org.serg.spring.hibernate_user_api.entity.Post;
import org.serg.spring.hibernate_user_api.entity.User;
import org.serg.spring.hibernate_user_api.repository.PostRepository;
import org.serg.spring.hibernate_user_api.repository.UserRepository;
import org.serg.spring.hibernate_user_api.util.PostMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;

    @Override
    public PostResponseDto savePost(Long id, PostRequestDto postRequestDto) {

        User user = userRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("User not found with id: " + id)
        );

        Post post = PostMapper.postRequestDtoToPost(postRequestDto);
        post.setUser(user);
        Post saved = postRepository.save(post);

        PostResponseDto postResponseDto = PostMapper.postToResponseDto(saved);

        return postResponseDto;
    }

    @Override
    public List<PostResponseDto> getAllPostsByUserId(Long id) {
        User user = userRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("User not found with id: " + id)
        );

        List<Post> posts = user.getPosts();
        List<PostResponseDto> postsDto = new ArrayList<>();
        for (Post post : posts) {
            postsDto.add(PostMapper.postToResponseDto(post));
        }

        return postsDto;
    }

    @Override
    public List<PostResponseDto> getPostsByTextLengthLessThen(int length) {
        List<Post> posts = postRepository.findPostsByTextLengthLessThan(length);

        List<PostResponseDto> postResponseDtos = new ArrayList<>();
        for (Post post : posts) {
            postResponseDtos.add(PostMapper.postToResponseDto(post));
        }

        return postResponseDtos;
    }


}
