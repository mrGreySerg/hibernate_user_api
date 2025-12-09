package org.serg.spring.hibernate_user_api.controller;

import lombok.RequiredArgsConstructor;
import org.serg.spring.hibernate_user_api.dto.PostResponseDto;
import org.serg.spring.hibernate_user_api.service.PostService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/posts")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @GetMapping("/length/{textLength}")
    public ResponseEntity<List<PostResponseDto>> getPostsByTextLengthLessThen(
            @PathVariable int textLength
    ) {
       List<PostResponseDto> posts = postService.getPostsByTextLengthLessThen(textLength);
       return ResponseEntity.ok(posts);
    }

    @GetMapping
    public ResponseEntity<List<PostResponseDto>> getAllPosts() {
        List<PostResponseDto> postResponseDtos =
                postService.getAllPosts();
        return ResponseEntity.status(HttpStatus.OK).body(postResponseDtos);
    }

    @GetMapping(path = "/{postId}")
    public ResponseEntity<PostResponseDto> getPostById(@PathVariable Long postId){
        PostResponseDto postResponseDto =
                postService.getPostById(postId);
        return ResponseEntity.status(HttpStatus.OK).body(postResponseDto);
    }

}
