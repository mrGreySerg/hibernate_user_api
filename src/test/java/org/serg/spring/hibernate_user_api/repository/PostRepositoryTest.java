package org.serg.spring.hibernate_user_api.repository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.serg.spring.hibernate_user_api.entity.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;
import java.util.Optional;

@ActiveProfiles("test")
@DataJpaTest
public class PostRepositoryTest {

    @Autowired
    private PostRepository postRepository;

    @Test
    public void testSaveAndFind() {

        Post myPost = Post.builder()
                .title("Post title")
                .date(LocalDate.parse("2020-11-11"))
                .text("Post text")
                .build();


        Post savedPost = postRepository.save(myPost);
        Optional<Post> actualPost = postRepository.findById(savedPost.getId());

       Assertions.assertTrue(actualPost.isPresent());
       Assertions.assertEquals(savedPost.getTitle(),actualPost.get().getTitle());
       Assertions.assertEquals(savedPost.getText(), actualPost.get().getText());
       Assertions.assertEquals(savedPost.getDate(), actualPost.get().getDate());

    }


}
